package com.affiliateLink.offer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.affiliateLink.offer.dto.CreateOffreRequest;
import com.affiliateLink.offer.dto.OffreResponse;
import com.affiliateLink.offer.service.OffreService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/offres")
public class OffreController {

    private final OffreService offreService ;

    public OffreController(OffreService offreService) {
        this.offreService = offreService;
    }

    @PostMapping
    public ResponseEntity<OffreResponse> createOffre(@Valid @RequestBody  CreateOffreRequest req){

       OffreResponse response  =offreService.createOffre(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
}
