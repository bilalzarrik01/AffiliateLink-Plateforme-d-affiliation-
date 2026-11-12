package com.affiliatelink.offer.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.affiliatelink.profile.Niche;

public record OffreDetailResponse(
        Long id,
        Long advertiserId,
        String title,
        String description,
        String landingUrl,
        Niche niche,
        Set<String> tags,
        LocalDateTime createdAt,
        List<CampaignResponse> campaigns
) {
}
