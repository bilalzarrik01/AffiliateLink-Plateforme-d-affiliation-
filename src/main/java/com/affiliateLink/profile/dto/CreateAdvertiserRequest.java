package com.affiliateLink.profile.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateAdvertiserRequest(@NotBlank @Email String email,@NotBlank String companyName ,@NotBlank String sector ,@NotBlank String website ) {
    
}
