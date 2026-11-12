
package com.affiliatelink.profile.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.affiliatelink.common.exception.AdvertiserNotFoundException;
import com.affiliatelink.common.exception.EmailAlreadyExistsException;
import com.affiliatelink.profile.dto.AdvertiserResponse;
import com.affiliatelink.profile.dto.CreateAdvertiserRequest;
import com.affiliatelink.profile.entity.Advertiser;
import com.affiliatelink.profile.repository.AdvertiserRepository;
import com.affiliatelink.profile.repository.ProfileRepository;

@Service
public class AdvertiserService {

    private final AdvertiserRepository advertiserRepository;
    private final ProfileRepository profileRepository;

    public AdvertiserService(
            AdvertiserRepository advertiserRepository,
            ProfileRepository profileRepository) {

        this.advertiserRepository = advertiserRepository;
        this.profileRepository = profileRepository;
    }

    public AdvertiserResponse createAdvertiser(CreateAdvertiserRequest request) {

        if (profileRepository.existsByEmail(request.email())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        Advertiser advertiser = new Advertiser(
                request.email(),
                request.companyName(),
                request.sector(),
                request.website());

        Advertiser saved = advertiserRepository.save(advertiser);

        return new AdvertiserResponse(
                saved.getId(),
                saved.getEmail(),
                saved.getCompanyName(),
                saved.getSector(),
                saved.getWebsite(),
                saved.getStatus(),
                saved.getCreatedAt());
    }

  public List<AdvertiserResponse> findAll(){
    return advertiserRepository.findAll().stream().map(a->new AdvertiserResponse(
        a.getId(),
        a.getEmail(),
        a.getCompanyName(),
        a.getSector(),
        a.getWebsite(),
        a.getStatus(),
        a.getCreatedAt()
    )).toList();


  }
  public AdvertiserResponse findById(Long id){
    Advertiser advertiser = advertiserRepository.findById(id).orElseThrow(()-> new AdvertiserNotFoundException(id));
    return new AdvertiserResponse(
        advertiser.getId(),
        advertiser.getEmail(),
        advertiser.getCompanyName(),
        advertiser.getSector(),
        advertiser.getWebsite(),
        advertiser.getStatus(),
        advertiser.getCreatedAt()

    );
  }

}
