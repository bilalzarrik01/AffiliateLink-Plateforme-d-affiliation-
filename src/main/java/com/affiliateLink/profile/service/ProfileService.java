package com.affiliatelink.profile.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.affiliatelink.common.exception.InvalidStatusTransitionException;
import com.affiliatelink.common.exception.ProfileNotFoundException;
import com.affiliatelink.profile.ProfileSuspended;
import com.affiliatelink.profile.entity.Profile;
import com.affiliatelink.profile.entity.ProfileStatus;
import com.affiliatelink.profile.repository.ProfileRepository;

import jakarta.transaction.Transactional;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final ApplicationEventPublisher applicationEventPublisher ;


    
public ProfileService(ProfileRepository profileRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.profileRepository = profileRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }



@Transactional
    public void updateStatus(Long id, ProfileStatus newStatus) {
        Profile profile = profileRepository.findById(id).orElseThrow(() -> new ProfileNotFoundException(id));
        if (profile.getStatus() == ProfileStatus.PENDING && newStatus == ProfileStatus.ACTIVE) {
            profile.activate();

        } else if (profile.getStatus() == ProfileStatus.ACTIVE && newStatus == ProfileStatus.SUSPENDED) {
            profile.suspend();
            applicationEventPublisher.publishEvent(new ProfileSuspended(profile.getId()));

        } else if (profile.getStatus() == ProfileStatus.SUSPENDED && newStatus == ProfileStatus.ACTIVE) {
            profile.reactivate();

        } else {
            throw new InvalidStatusTransitionException("Invalid status transition");
        }

    }

}
