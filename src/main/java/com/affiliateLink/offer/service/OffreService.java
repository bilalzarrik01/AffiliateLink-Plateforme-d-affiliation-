package com.affiliateLink.offer.service;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.affiliateLink.offer.dto.CreateOffreRequest;
import com.affiliateLink.offer.dto.OffreDetailResponse;
import com.affiliateLink.offer.dto.OffreResponse;
import com.affiliateLink.offer.dto.UpdateOffreRequest;
import com.affiliateLink.offer.entity.Offre;
import com.affiliateLink.offer.entity.Tag;
import com.affiliateLink.offer.mapper.OffreMapper;
import com.affiliateLink.offer.repository.OffreRepository;
import com.affiliateLink.offer.repository.OffreSpecification;
import com.affiliateLink.profile.Niche;
import com.affiliateLink.profile.ProfileApi;

@Service
public class OffreService {

    private final OffreRepository offreRepository;
    private final ProfileApi profileApi;
    private final TagService tagService;
    private final OffreMapper offreMapper;

    public OffreService(
            OffreRepository offreRepository,
            ProfileApi profileApi,
            TagService tagService,
            OffreMapper offreMapper) {

        this.offreRepository = offreRepository;
        this.profileApi = profileApi;
        this.tagService = tagService;
        this.offreMapper = offreMapper;
    }

    @Transactional
    public OffreResponse createOffre(CreateOffreRequest req) {

        if (!profileApi.isAdvertiserActive(req.advertiserId())) {
            throw new IllegalStateException("Advertiser is not active");
        }

        Offre offre = new Offre(
                req.advertiserId(),
                req.title(),
                req.description(),
                req.landingUrl(),
                req.niche()
        );

        if (req.tags() != null) {
            for (String name : req.tags()) {
                Tag tag = tagService.createOrFind(name);
                offre.getTags().add(tag);
            }
        }

        Offre savedOffre = offreRepository.save(offre);

        return offreMapper.toResponse(savedOffre);
    }

    @Transactional(readOnly = true)
public Page<OffreResponse> getAllOffres(Pageable pageable, Long advertiserId , Niche niche , String tag ,String keyword) {

    Page<Offre> offres = offreRepository.findAll(
            OffreSpecification.hasAdvertiserId(advertiserId).and(OffreSpecification.hasNiche(niche)).and(OffreSpecification.hasTag(tag)).and(OffreSpecification.hasKeyword(keyword)),
            pageable
    );

    return offres.map(offre -> offreMapper.toResponse(offre));
}
@Transactional(readOnly = true)
public OffreDetailResponse getOffreById(Long id) {
    Offre offre = offreRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Offre not found"));

    return offreMapper.toDetailResponse(offre);
}

@Transactional
public OffreResponse updateOffre(Long id, UpdateOffreRequest req) {
    Offre offre = offreRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Offre not found"));

    offre.update(
        req.title(),
        req.description(),
        req.landingUrl(),
        req.niche()
    );

    offre.getTags().clear();

    if (req.tags() != null) {
        for (String name : req.tags()) {
            Tag tag = tagService.createOrFind(name);
            offre.getTags().add(tag);
        }
    }

    return offreMapper.toResponse(offre);
}
}