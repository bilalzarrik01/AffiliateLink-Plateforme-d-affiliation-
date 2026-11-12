package com.affiliatelink.profile.dto;

import java.time.LocalDateTime;

import com.affiliatelink.profile.entity.ProfileStatus;

public record AdvertiserResponse(Long id , String email ,String companyName ,String sector ,String website ,ProfileStatus status ,LocalDateTime createdAt) {
    
}
