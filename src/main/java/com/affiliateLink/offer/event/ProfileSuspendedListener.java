package com.affiliatelink.offer.event;

import java.util.List;

import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;


import com.affiliatelink.profile.ProfileSuspended;
import com.affiliatelink.offer.entity.Campaign;
import com.affiliatelink.offer.entity.CampaignStatus;
import com.affiliatelink.offer.repository.CampaignRepository;

@Component
public class ProfileSuspendedListener {

    private final CampaignRepository campaignRepository;

    public ProfileSuspendedListener(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @ApplicationModuleListener
   
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