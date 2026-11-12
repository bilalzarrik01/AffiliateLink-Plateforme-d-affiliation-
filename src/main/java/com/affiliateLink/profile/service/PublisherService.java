package com.affiliatelink.profile.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.affiliatelink.profile.repository.ProfileRepository;
import com.affiliatelink.profile.repository.PublisherRepository;
import com.affiliatelink.common.exception.EmailAlreadyExistsException;
import com.affiliatelink.common.exception.PublisherNotFoundException;
import com.affiliatelink.profile.dto.*;
import com.affiliatelink.profile.entity.Publisher;

@Service
public class PublisherService {
    private final PublisherRepository publisherRepository;
    private final ProfileRepository profileRepository;

    public PublisherService(PublisherRepository publisherRepository, ProfileRepository profileRepository) {
        this.publisherRepository = publisherRepository;
        this.profileRepository = profileRepository;
    }

    public PublisherResponse createPublisher(CreatePublisherRequest request) {
        if (profileRepository.existsByEmail(request.email())) {
            throw new EmailAlreadyExistsException("Email already exists");

        }
        Publisher publisher = new Publisher(request.email(), request.firstName(), request.lastName(), request.niche(),
                request.bio(), request.country());

        Publisher savedPublisher = publisherRepository.save(publisher);
        return new PublisherResponse(
                savedPublisher.getId(),
                savedPublisher.getEmail(),
                savedPublisher.getFirstName(),
                savedPublisher.getLastName(),
                savedPublisher.getNiche(),
                savedPublisher.getBio(),
                savedPublisher.getCountry(),
                savedPublisher.getStatus(),
                savedPublisher.getCreatedAt()

        );

    }

    public List<PublisherResponse> getAllPublishers() {
        return publisherRepository.findAll()
                .stream()
                .map(p -> new PublisherResponse(
                        p.getId(),
                        p.getEmail(),
                        p.getFirstName(),
                        p.getLastName(),
                        p.getNiche(),
                        p.getBio(),
                        p.getCountry(),
                        p.getStatus(),
                        p.getCreatedAt()))
                .toList();
    }

    public PublisherResponse getPublisherById(Long id) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new PublisherNotFoundException(id));

        return new PublisherResponse(
                publisher.getId(),
                publisher.getEmail(),
                publisher.getFirstName(),
                publisher.getLastName(),
                publisher.getNiche(),
                publisher.getBio(),
                publisher.getCountry(),
                publisher.getStatus(),
                publisher.getCreatedAt());
    }

}
