package com.affiliateLink.profile.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity ;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "profile_publishers")
public class Publisher extends Profile{
    @Column(name = "first_name" , nullable = false , length =100)
    private String firstName ; 
    @Column(name="last_name" , nullable =false , length =100)
    private String lastName ;
    @Enumerated(EnumType.STRING)
    @Column(name="niche" , nullable =false , length=30)
    private Niche niche ;
    
@Column(name = "bio", length = 500)
private String bio;

@Column(name = "country", nullable = false, length = 100)
private String country;

public Publisher(String email, String firstName, String lastName, Niche niche, String bio, String country) {
    super(email);
    this.firstName = firstName;
    this.lastName = lastName;
    this.niche = niche;
    this.bio = bio;
    this.country = country;
}

public Publisher() {
    super();
}

public String getFirstName() {
    return firstName;
}

public String getLastName() {
    return lastName;
}

public Niche getNiche() {
    return niche;
}

public String getBio() {
    return bio;
}

public String getCountry() {
    return country;
}

    
}
