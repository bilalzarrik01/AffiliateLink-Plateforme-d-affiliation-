package com.affiliateLink.offer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.affiliateLink.offer.entity.Campaign;

public interface CampaignRepository extends JpaRepository<Campaign , Long> {
    
}
