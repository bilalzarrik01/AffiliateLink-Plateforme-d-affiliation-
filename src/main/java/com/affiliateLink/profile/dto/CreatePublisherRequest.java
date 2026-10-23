package com.affiliateLink.profile.dto;

import com.affiliateLink.profile.entity.Niche;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreatePublisherRequest(@NotBlank @Email  String email , @NotBlank String firstName, @NotBlank String lastName , @NotNull Niche niche , String bio ,@NotBlank String country) {
    

    
}
