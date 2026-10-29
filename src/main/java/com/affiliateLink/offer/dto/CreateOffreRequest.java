package com.affiliateLink.offer.dto;

import java.util.Set;

import com.affiliateLink.profile.Niche;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateOffreRequest( @NotNull Long advertiserId ,
     @NotBlank String title ,
      String description,
      @NotBlank  String landingUrl , 
      @NotNull  Niche niche ,
         Set<String> tags
        ) {
    
}
