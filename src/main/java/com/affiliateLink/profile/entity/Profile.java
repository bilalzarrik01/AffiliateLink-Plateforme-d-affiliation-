package com.affiliateLink.profile.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name = "profile_profiles")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "email", nullable = false, unique = true, length = 255)
    private String email;
    @Column(name = "status", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private ProfileStatus status = ProfileStatus.PENDING;
    @Column(name ="created_at" , nullable=false , updatable =false)
    private LocalDateTime createdAt = LocalDateTime.now();

    protected Profile() {
}
protected Profile(String email){
    this.email =email ;
}
public Long getId() {
    return id;
}

public String getEmail() {
    return email;
}

public ProfileStatus getStatus() {
    return status;
}

public LocalDateTime getCreatedAt() {
    return createdAt;
}


   public void activate(){
    if (status != ProfileStatus.PENDING) {
        throw new IllegalStateException("Already activated or suspended");
        
    }
    status = ProfileStatus.ACTIVE ;

   }
   public void suspend(){
    if(status != ProfileStatus.ACTIVE){
        throw new IllegalStateException("the acc must be activated to suspend !");
    }
    status = ProfileStatus.SUSPENDED ;
   }
   public void reactivate(){
    if(status != ProfileStatus.SUSPENDED){
        throw new IllegalStateException("to reactivate the acc should be suspended");

    }
    status = ProfileStatus.ACTIVE ;
   }
}
