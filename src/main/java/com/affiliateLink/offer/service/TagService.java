package com.affiliateLink.offer.service;

import java.util.Locale;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.affiliateLink.offer.entity.Tag;
import com.affiliateLink.offer.repository.TagRepository;

@Service
public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tag createOrFind(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Tag name is required");
        }

        String normalizedName = name.trim().toLowerCase(Locale.ROOT);
        Optional<Tag> existingTag = tagRepository.findByName(normalizedName);
        if (existingTag.isPresent()) {
            return existingTag.get();

        }
        Tag tag = new Tag(normalizedName);
        return tagRepository.save(tag);

    }

}
