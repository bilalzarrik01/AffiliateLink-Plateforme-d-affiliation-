package com.affiliateLink.offer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.affiliateLink.offer.entity.Campaign;
import com.affiliateLink.offer.entity.Offre;
import com.affiliateLink.offer.entity.Tag;
import com.affiliateLink.offer.dto.CampaignResponse;
import com.affiliateLink.offer.dto.OffreDetailResponse;
import com.affiliateLink.offer.dto.OffreResponse;

@Mapper(componentModel = "spring")
public interface OffreMapper {

    @Mapping(target = "tags", source = "tags")
    OffreResponse toResponse(Offre offre);

    default String map(Tag tag) {

        if (tag == null) {
            return null;
        }

        return tag.getName();
    }
    
    CampaignResponse toCampaignResponse(Campaign campaign);
    
   @Mapping(target = "campaigns", source = "campaigns")
OffreDetailResponse toDetailResponse(Offre offre);
    
}