package com.affiliatelink.profile.service;

import org.springframework.stereotype.Service;

import com.affiliatelink.common.exception.AdvertiserNotFoundException;
import com.affiliatelink.profile.ProfileApi;
import com.affiliatelink.profile.entity.Advertiser;
import com.affiliatelink.profile.entity.ProfileStatus;
import com.affiliatelink.profile.repository.AdvertiserRepository;

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
