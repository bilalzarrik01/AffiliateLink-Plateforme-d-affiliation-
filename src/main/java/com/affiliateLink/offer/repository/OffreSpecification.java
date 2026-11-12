package com.affiliatelink.offer.repository;

import org.springframework.data.jpa.domain.Specification;

import com.affiliatelink.offer.entity.Offre;
import com.affiliatelink.profile.Niche;

public class OffreSpecification {

     public static Specification<Offre> hasNiche(Niche niche) {
        return (root, query, cb) -> {
            if (niche == null) {
                return null;
            }

            return cb.equal(root.get("niche"), niche);
        };
    }

    public static Specification<Offre> hasAdvertiserId(Long advertiserId) {
    return (root, query, cb) -> {
        if (advertiserId == null) {
            return null;
        }

        return cb.equal(root.get("advertiserId"), advertiserId);
    };
}
public static Specification<Offre> hasTag(String tag) {
    return (root, query, cb) -> {
        if (tag == null || tag.isBlank()) {
            return null;
        }

        return cb.equal(
                root.join("tags").get("name"),
                tag.trim().toLowerCase()
        );
    };
}
public static Specification<Offre> hasKeyword(String keyword) {
    return (root, query, cb) -> {
        if (keyword == null || keyword.isBlank()) {
            return null;
        }

        String search = "%" + keyword.toLowerCase() + "%";

        return cb.or(
                cb.like(cb.lower(root.get("title")), search),
                cb.like(cb.lower(root.get("description")), search)
        );
    };
}
    
}
