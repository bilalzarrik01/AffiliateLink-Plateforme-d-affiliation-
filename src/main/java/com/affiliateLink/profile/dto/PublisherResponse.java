package com.affiliatelink.profile.dto;

import java.time.LocalDateTime;

import com.affiliatelink.profile.Niche;
import com.affiliatelink.profile.entity.ProfileStatus;

public record PublisherResponse( Long id ,String email ,String firstName ,String lastName ,Niche niche ,String bio , String country ,ProfileStatus status , LocalDateTime createdAt ) {
    
}
