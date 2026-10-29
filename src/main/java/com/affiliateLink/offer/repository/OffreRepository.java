package com.affiliateLink.offer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.affiliateLink.offer.entity.Offre;

public interface OffreRepository extends  JpaRepository<Offre , Long>{
    
}
