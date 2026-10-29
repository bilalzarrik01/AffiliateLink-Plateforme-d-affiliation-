package com.affiliateLink.offer.dto;

import java.time.LocalDateTime;
import java.util.Set;

import com.affiliateLink.profile.Niche;



public record OffreResponse(
        Long id,
        Long advertiserId,
        String title,
        String description,
        String landingUrl,
        Niche niche,
        Set<String> tags,
        LocalDateTime createdAt) {

}
