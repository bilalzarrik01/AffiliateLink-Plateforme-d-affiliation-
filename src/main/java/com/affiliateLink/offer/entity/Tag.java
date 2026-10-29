package com.affiliateLink.offer.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Table;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;


@Entity
@Table(name = "offer_tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<Offre> offres = new HashSet<>();

    protected Tag(){
        
    };
    public Tag(String name) {
        this.name = name;
    }
  
    public String getName() {
        return name;
    }
 
}

