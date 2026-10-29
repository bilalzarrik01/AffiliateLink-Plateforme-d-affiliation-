package com.affiliateLink.profile.service;

import org.springframework.stereotype.Service;

import com.affiliateLink.common.exception.AdvertiserNotFoundException;
import com.affiliateLink.profile.ProfileApi;
import com.affiliateLink.profile.entity.Advertiser;
import com.affiliateLink.profile.entity.ProfileStatus;
import com.affiliateLink.profile.repository.AdvertiserRepository;

@Service 
public class ProfileApiImpl implements ProfileApi {

    private final AdvertiserRepository advertiserRepository ;

    public ProfileApiImpl(AdvertiserRepository advertiserRepository) {
        this.advertiserRepository = advertiserRepository;
    }

 @Override
public boolean isAdvertiserActive(Long advertiserId) {

    Advertiser advertiser = advertiserRepository.findById(advertiserId)
        .orElseThrow(() -> new AdvertiserNotFoundException(
            "Advertiser not found: " + advertiserId
        ));

    return advertiser.getStatus() == ProfileStatus.ACTIVE;
}
    
}
