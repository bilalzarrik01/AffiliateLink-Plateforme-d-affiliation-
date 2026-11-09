
package com.affiliateLink.offer.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.affiliateLink.offer.dto.CreateCampaignRequest;
import com.affiliateLink.offer.dto.CampaignResponse;
import com.affiliateLink.offer.entity.Campaign;
import com.affiliateLink.offer.entity.Offre;
import com.affiliateLink.offer.mapper.OffreMapper;
import com.affiliateLink.offer.repository.CampaignRepository;
import com.affiliateLink.offer.repository.OffreRepository;
import com.affiliateLink.profile.ProfileApi;
import java.util.List;
import java.util.NoSuchElementException;

import com.affiliateLink.offer.entity.CampaignStatus;

@Service
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final OffreRepository offreRepository;
    private final OffreMapper offreMapper;
    private final ProfileApi profileApi;

    public CampaignService(
            CampaignRepository campaignRepository,
            OffreRepository offreRepository,
            OffreMapper offreMapper,
            ProfileApi profileApi) {

        this.campaignRepository = campaignRepository;
        this.offreRepository = offreRepository;
        this.offreMapper = offreMapper;
        this.profileApi = profileApi;
    }

    @Transactional
    public CampaignResponse createCampaign(Long offreId, CreateCampaignRequest req) {

        Offre offre = offreRepository.findById(offreId)
                .orElseThrow(() -> new RuntimeException("Offre not found"));

        if (!profileApi.isAdvertiserActive(offre.getAdvertiserId())) {
            throw new IllegalStateException("Advertiser is not active");
        }

        Campaign campaign = new Campaign(
                req.name(),
                req.startDate(),
                req.endDate(),
                req.budget(),
                req.payoutModel(),
                req.payoutValue(),
                offre);

        Campaign savedCampaign = campaignRepository.save(campaign);

        return offreMapper.toCampaignResponse(savedCampaign);
    }

    @Transactional(readOnly = true)

    public List<CampaignResponse> getCampaigns(CampaignStatus status) {
        List<Campaign> campaigns;
        if (status == null) {
            campaigns = campaignRepository.findAll();

        } else {
            campaigns = campaignRepository.findByStatus(status);
        }
        return campaigns.stream().map(offreMapper::toCampaignResponse).toList();

    }

    @Transactional
public CampaignResponse activateCampaign(Long id) {
    Campaign campaign = campaignRepository.findById(id)
            .orElseThrow(() ->new NoSuchElementException("Offre not found"));

    boolean alreadyActive = campaignRepository
            .existsByOffre_IdAndStatusAndIdNot(
                    campaign.getOffre().getId(),
                    CampaignStatus.ACTIVE,
                    id);

    if (alreadyActive) {
        throw new IllegalStateException(
                "Another active campaign already exists for this offer");
    }

    campaign.activate();

    return offreMapper.toCampaignResponse(campaignRepository.save(campaign));
}
    @Transactional
    public CampaignResponse pauseCampaign(Long id) {
        Campaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Campaign not found") );

        campaign.pause();

        return offreMapper.toCampaignResponse(campaignRepository.save(campaign));
    }

    @Transactional
    public CampaignResponse endCampaign(Long id) {
        Campaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campaign not found"));

        campaign.end();

        return offreMapper.toCampaignResponse(campaignRepository.save(campaign));
    }

}
