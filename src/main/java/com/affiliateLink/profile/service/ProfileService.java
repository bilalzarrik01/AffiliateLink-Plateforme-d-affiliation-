package com.affiliateLink.profile.service;

import org.springframework.stereotype.Service;

import com.affiliateLink.common.exception.InvalidStatusTransitionException;
import com.affiliateLink.common.exception.ProfileNotFoundException;
import com.affiliateLink.profile.entity.Profile;
import com.affiliateLink.profile.entity.ProfileStatus;
import com.affiliateLink.profile.repository.ProfileRepository;

import jakarta.transaction.Transactional;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }
    
@Transactional
    public void updateStatus(Long id, ProfileStatus newStatus) {
        Profile profile = profileRepository.findById(id).orElseThrow(() -> new ProfileNotFoundException(id));
        if (profile.getStatus() == ProfileStatus.PENDING && newStatus == ProfileStatus.ACTIVE) {
            profile.activate();

        } else if (profile.getStatus() == ProfileStatus.ACTIVE && newStatus == ProfileStatus.SUSPENDED) {
            profile.suspend();

        } else if (profile.getStatus() == ProfileStatus.SUSPENDED && newStatus == ProfileStatus.ACTIVE) {
            profile.reactivate();

        } else {
            throw new InvalidStatusTransitionException("Invalid status transition");
        }

    }

}
