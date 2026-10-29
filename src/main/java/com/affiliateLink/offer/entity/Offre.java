
package com.affiliateLink.offer.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import com.affiliateLink.profile.Niche;

@Entity
@Table(name = "offer_offres")
public class Offre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long advertiserId;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String landingUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Niche niche;
    @OneToMany(mappedBy = "offre")
    private List<Campaign> campaigns = new ArrayList<>() ;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
@ManyToMany
@JoinTable(
    name = "offer_offre_tags",
    joinColumns = @JoinColumn(name = "offre_id"),
    inverseJoinColumns = @JoinColumn(name = "tag_id")
)
private Set<Tag> tags = new HashSet<>();


    protected Offre() {
    }

    public Offre(Long advertiserId, String title,
                 String description, String landingUrl,
                 Niche niche) {
        this.advertiserId = advertiserId;
        this.title = title;
        this.description = description;
        this.landingUrl = landingUrl;
        this.niche = niche;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Long getAdvertiserId() {
        return advertiserId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLandingUrl() {
        return landingUrl;
    }

    public Niche getNiche() {
        return niche;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public Set<Tag> getTags() {
    return tags;
}
}
