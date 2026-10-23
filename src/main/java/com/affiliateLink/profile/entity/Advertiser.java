package com.affiliateLink.profile.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "profile_advertisers")
public class Advertiser extends Profile {

    @Column(name = "company_name", nullable = false, length = 150)
    private String companyName;
    @Column(name = "sector", nullable = false, length = 100)
    private String sector;
    @Column(name = "website", nullable = false , length = 255)
    private String website;


    public Advertiser(String email, String companyName, String sector, String website) {
        super(email);
        this.companyName = companyName;
        this.sector = sector;
        this.website = website;
    }


    public Advertiser(){}


    public String getCompanyName() {
        return companyName;
    }


    public String getSector() {
        return sector;
    }


    public String getWebsite() {
        return website;
    }
    
}
