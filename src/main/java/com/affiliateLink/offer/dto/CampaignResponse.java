package com.affiliatelink.offer.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.affiliatelink.offer.entity.CampaignStatus;
import com.affiliatelink.offer.entity.PayoutModel;

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
