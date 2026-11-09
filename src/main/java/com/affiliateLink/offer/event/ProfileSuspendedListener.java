package com.affiliateLink.offer.event;

import java.util.List;

import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.affiliateLink.profile.ProfileSuspended;
import com.affiliateLink.offer.entity.Campaign;
import com.affiliateLink.offer.entity.CampaignStatus;
import com.affiliateLink.offer.repository.CampaignRepository;

@Component
public class ProfileSuspendedListener {

    private final CampaignRepository campaignRepository;

    public ProfileSuspendedListener(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @ApplicationModuleListener
    @Transactional
    public void on(ProfileSuspended event) {

        List<Campaign> campaigns = campaignRepository
                .findByOffre_AdvertiserIdAndStatus(
                        event.profileId(),
                        CampaignStatus.ACTIVE
                );

        for (Campaign campaign : campaigns) {
            campaign.pause();
            campaignRepository.save(campaign);
        }
    }
}