package com.affiliateLink.profile.dto;

import java.time.LocalDateTime;

import com.affiliateLink.profile.entity.Niche;
import com.affiliateLink.profile.entity.ProfileStatus;

public record PublisherResponse( Long id ,String email ,String firstName ,String lastName ,Niche niche ,String bio , String country ,ProfileStatus status , LocalDateTime createdAt ) {
    
}
