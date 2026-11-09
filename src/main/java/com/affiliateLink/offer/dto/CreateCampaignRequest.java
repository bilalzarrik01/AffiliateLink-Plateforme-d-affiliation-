package com.affiliateLink.offer.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.affiliateLink.offer.entity.PayoutModel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCampaignRequest(
    @NotBlank String name,
    @NotNull LocalDate startDate,
    @NotNull LocalDate endDate,
    @NotNull BigDecimal budget,
    @NotNull PayoutModel payoutModel,
    @NotNull BigDecimal payoutValue
) {}
