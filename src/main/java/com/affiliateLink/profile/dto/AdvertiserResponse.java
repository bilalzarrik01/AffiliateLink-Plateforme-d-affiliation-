package com.affiliateLink.profile.dto;

import java.time.LocalDateTime;

import com.affiliateLink.profile.entity.ProfileStatus;

public record AdvertiserResponse(Long id , String email ,String companyName ,String sector ,String website ,ProfileStatus status ,LocalDateTime createdAt) {
    
}
