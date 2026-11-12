package com.affiliatelink.offer.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.affiliatelink.offer.dto.CampaignResponse;
import com.affiliatelink.offer.entity.CampaignStatus;
import com.affiliatelink.offer.service.CampaignService;

@RestController 
@RequestMapping("/api/campaigns")
public class CampaignController {
    private final CampaignService campaignService ;

    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }


@GetMapping
public List<CampaignResponse> getCampaigns(
        @RequestParam(required = false) CampaignStatus status) {

    return campaignService.getCampaigns(status);
}
@PatchMapping("/{id}/activate")
public CampaignResponse activateCampaign(@PathVariable Long id) {
    return campaignService.activateCampaign(id);
}

@PatchMapping("/{id}/pause")
public CampaignResponse pauseCampaign(@PathVariable Long id) {
    return campaignService.pauseCampaign(id);
}

@PatchMapping("/{id}/end")
public CampaignResponse endCampaign(@PathVariable Long id) {
    return campaignService.endCampaign(id);
}
}
