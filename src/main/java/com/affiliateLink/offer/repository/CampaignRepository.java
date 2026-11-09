package com.affiliateLink.offer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.affiliateLink.offer.entity.Campaign;
import com.affiliateLink.offer.entity.CampaignStatus;


public interface CampaignRepository extends JpaRepository<Campaign , Long> {
    
    List<Campaign> findByStatus(CampaignStatus status);

    boolean existsByOffre_IdAndStatusAndIdNot(
        Long offreId,
        CampaignStatus status,
        Long campaignId

    );
    List<Campaign> findByOffre_AdvertiserIdAndStatus(
        Long advertiserId,
        CampaignStatus status
);
}
