package com.affiliatelink.offer.dto;

import java.time.LocalDateTime;
import java.util.Set;

import com.affiliatelink.profile.Niche;



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
