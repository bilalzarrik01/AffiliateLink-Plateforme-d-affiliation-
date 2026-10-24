package com.affiliateLink.profile.controller;

import com.affiliateLink.profile.dto.AdvertiserResponse;
import com.affiliateLink.profile.dto.CreateAdvertiserRequest;
import com.affiliateLink.profile.service.AdvertiserService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/advertisers")
public class AdvertiserController {

    private final AdvertiserService advertiserService;

    public AdvertiserController(AdvertiserService advertiserService) {
        this.advertiserService = advertiserService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AdvertiserResponse createAdvertiser(
            @Valid @RequestBody CreateAdvertiserRequest request) {

        return advertiserService.createAdvertiser(request);
    }

    @GetMapping 
    @ResponseStatus
    public List<AdvertiserResponse> getAllAdvertiser(){
        return advertiserService.findAll();       

    }
    @GetMapping("/{id}")
    public AdvertiserResponse getAdvertiserById(@PathVariable Long id){
        return advertiserService.findById(id);
    }
}
