package com.affiliatelink.offer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.affiliatelink.offer.dto.CampaignResponse;
import com.affiliatelink.offer.dto.CreateCampaignRequest;
import com.affiliatelink.offer.dto.CreateOffreRequest;
import com.affiliatelink.offer.dto.OffreDetailResponse;
import com.affiliatelink.offer.dto.OffreResponse;
import com.affiliatelink.offer.dto.UpdateOffreRequest;
import com.affiliatelink.offer.service.CampaignService;
import com.affiliatelink.offer.service.OffreService;
import com.affiliatelink.profile.Niche;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/offres")
public class OffreController {

    private final OffreService offreService ;
    private final CampaignService campaignService ;

    public OffreController(OffreService offreService,CampaignService campaignService ) {
        this.offreService = offreService;
        this.campaignService = campaignService;
    }

    @PostMapping
    public ResponseEntity<OffreResponse> createOffre(@Valid @RequestBody  CreateOffreRequest req){

       OffreResponse response  =offreService.createOffre(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
  @GetMapping
public ResponseEntity<Page<OffreResponse>> getAllOffres(
        @PageableDefault(size = 10) Pageable pageable,
        @RequestParam(required = false) Long advertiserId,
        @RequestParam(required = false) Niche niche,
        @RequestParam(required = false ) String tag,
    @RequestParam(required = false)String keyword ) {

    Page<OffreResponse> offres =
            offreService.getAllOffres(pageable, advertiserId,niche, tag ,keyword);

    return ResponseEntity.ok(offres);
}

@GetMapping("/{id}")
public ResponseEntity<OffreDetailResponse> getOfferById(@PathVariable  Long id ) {
    OffreDetailResponse response = offreService.getOffreById(id);

      return ResponseEntity.ok(response);
}

    @PutMapping("/{id}")
public ResponseEntity<OffreResponse> updateOffre(
        @PathVariable Long id,
        @Valid @RequestBody UpdateOffreRequest req) {

    OffreResponse response = offreService.updateOffre(id, req);
    return ResponseEntity.ok(response);
}

@PostMapping("/{id}/campaigns")
public ResponseEntity<CampaignResponse> createCampaign(
        @PathVariable Long id,
        @Valid @RequestBody CreateCampaignRequest req) {

    CampaignResponse response = campaignService.createCampaign(id, req);

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
}
}
