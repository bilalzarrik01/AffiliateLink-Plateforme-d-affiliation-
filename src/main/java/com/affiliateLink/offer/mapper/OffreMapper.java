package com.affiliatelink.offer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.affiliatelink.offer.entity.Campaign;
import com.affiliatelink.offer.entity.Offre;
import com.affiliatelink.offer.entity.Tag;
import com.affiliatelink.offer.dto.CampaignResponse;
import com.affiliatelink.offer.dto.OffreDetailResponse;
import com.affiliatelink.offer.dto.OffreResponse;

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