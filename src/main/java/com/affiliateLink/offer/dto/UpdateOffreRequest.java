package com.affiliatelink.offer.dto;

import java.util.Set;

import com.affiliatelink.profile.Niche;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateOffreRequest(
    @NotBlank String title,
    String description,
    @NotBlank String landingUrl,
    @NotNull Niche niche,
    Set<String> tags
) {}