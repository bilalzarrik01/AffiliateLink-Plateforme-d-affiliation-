package com.affiliatelink.offer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.affiliatelink.offer.entity.Offre;



public interface OffreRepository extends JpaRepository<Offre, Long>,
        JpaSpecificationExecutor<Offre> {

               

}
