package com.affiliateLink.offer.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.affiliateLink.offer.entity.CampaignStatus;
import com.affiliateLink.offer.entity.PayoutModel;

public record CampaignResponse(
        Long id,
        String name,
        LocalDate startDate,
        LocalDate endDate,
        BigDecimal budget,
        PayoutModel payoutModel,
        BigDecimal payoutValue,
        CampaignStatus status
) {
}
