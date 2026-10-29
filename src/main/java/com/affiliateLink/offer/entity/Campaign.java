
package com.affiliateLink.offer.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "offer_campaigns")
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal budget;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PayoutModel payoutModel;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal payoutValue;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CampaignStatus status = CampaignStatus.DRAFT;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offre_id", nullable = false)
    private Offre offre;

    protected Campaign() {
    }

    public Campaign(
            String name,
            LocalDate startDate,
            LocalDate endDate,
            BigDecimal budget,
            PayoutModel payoutModel,
            BigDecimal payoutValue,
            Offre offre) {

        // Validation du nom
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Campaign name is required");
        }

        // Validation des dates
        if (startDate == null || endDate == null
                || !endDate.isAfter(startDate)) {
            throw new IllegalArgumentException(
                    "End date must be after start date");
        }

        if (budget == null
                || budget.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Budget must be positive");
        }

        if (payoutModel == null || payoutValue == null) {
            throw new IllegalArgumentException(
                    "Payout model and value are required");
        }

        if (payoutModel == PayoutModel.CPA_PERCENT) {

            if (payoutValue.compareTo(BigDecimal.ZERO) < 0
                    || payoutValue.compareTo(new BigDecimal("100")) > 0) {
                throw new IllegalArgumentException(
                        "Payout percentage must be between 0 and 100");
            }

        } else {

            if (payoutValue.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException(
                        "Payout value must be positive");
            }
        }

        if (offre == null) {
            throw new IllegalArgumentException("Offre is required");
        }

        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
        this.payoutModel = payoutModel;
        this.payoutValue = payoutValue;
        this.offre = offre;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public PayoutModel getPayoutModel() {
        return payoutModel;
    }

    public BigDecimal getPayoutValue() {
        return payoutValue;
    }

    public CampaignStatus getStatus() {
        return status;
    }

    public Offre getOffre() {
        return offre;
    }

    public void activate() {
        if (status != CampaignStatus.DRAFT && status != CampaignStatus.PAUSED) {
            throw new IllegalStateException("Campaign cannot be activated from this status");
        }
        if (endDate.isBefore(LocalDate.now())) {
    throw new IllegalStateException("Cannot activate an expired campaign");
}
        this.status = CampaignStatus.ACTIVE;
        

    }
    public void pause(){
        if (status != CampaignStatus.ACTIVE) {

                throw new IllegalStateException("Cannot pause this  campaign");

        } 
        this.status= CampaignStatus.PAUSED ;
    }

    public void end(){
        if (status !=CampaignStatus.ACTIVE && status != CampaignStatus.PAUSED ) {
        throw new IllegalStateException("Cannot end campaign from this status");
        }
        this.status = CampaignStatus.ENDED ;
    }
}
