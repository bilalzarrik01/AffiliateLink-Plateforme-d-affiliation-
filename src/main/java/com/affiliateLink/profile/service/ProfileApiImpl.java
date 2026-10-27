package com.affiliateLink.profile.service;

import org.springframework.stereotype.Service;

import com.affiliateLink.profile.ProfileApi;
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
    return   advertiserRepository.findById(advertiserId).map(a->a.getStatus() == ProfileStatus.ACTIVE).orElse(false);
        
    }
    
}
