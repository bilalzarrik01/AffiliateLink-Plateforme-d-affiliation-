package com.affiliateLink.offer.service;

import org.springframework.stereotype.Service;


import com.affiliateLink.offer.dto.CreateOffreRequest;
import com.affiliateLink.offer.dto.OffreResponse;
import com.affiliateLink.offer.entity.Offre;
import com.affiliateLink.offer.entity.Tag;
import com.affiliateLink.offer.mapper.OffreMapper;
import com.affiliateLink.offer.repository.OffreRepository;
import com.affiliateLink.profile.ProfileApi;


import jakarta.transaction.Transactional;

@Service
public class OffreService {

    private final OffreRepository offreRepository;
    private final ProfileApi profileApi;
    private final TagService tagService;
    private final OffreMapper offreMapper;

    public OffreService(OffreRepository offreRepository, ProfileApi profileApi, TagService tagService,
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
                req.niche());
        if (req.tags() != null) {
            for (String name : req.tags()) {
                Tag tag = tagService.createOrFind(name);
                offre.getTags().add(tag);
            }
        }
        Offre savedOffre = offreRepository.save(offre);
        return offreMapper.toResponse(savedOffre);
    }
}
