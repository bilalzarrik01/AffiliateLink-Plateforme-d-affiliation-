package com.affiliateLink.profile.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.affiliateLink.profile.dto.UpdateProfileStatusRequest;
import com.affiliateLink.profile.service.ProfileService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/admin/profiles")
public class ProfileController {

    private final ProfileService profileService ;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }
    @PatchMapping("/{id}/status")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable Long id, @Valid @RequestBody  UpdateProfileStatusRequest req){
         profileService.updateStatus(id, req.status());
    }


    
}
