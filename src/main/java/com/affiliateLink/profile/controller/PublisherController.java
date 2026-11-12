
package com.affiliatelink.profile.controller;

import org.springframework.web.bind.annotation.RestController;

import com.affiliatelink.profile.dto.CreatePublisherRequest;
import com.affiliatelink.profile.dto.PublisherResponse;
import com.affiliatelink.profile.service.PublisherService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/api/publishers")
public class PublisherController {
    
    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PublisherResponse createPublisher(@Valid @RequestBody CreatePublisherRequest request) {
        return publisherService.createPublisher(request);

    }

    @GetMapping
    @ResponseStatus
    public List<PublisherResponse> getAllPublishers() {
        return publisherService.getAllPublishers();
    }

    @GetMapping("/{id}")
    public PublisherResponse getPublisherById(@PathVariable Long id) {
        return publisherService.getPublisherById(id);
    }

}
