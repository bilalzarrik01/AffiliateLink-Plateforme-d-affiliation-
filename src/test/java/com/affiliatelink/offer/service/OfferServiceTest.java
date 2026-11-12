package com.affiliatelink.offer.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.affiliatelink.offer.repository.CampaignRepository;
import com.affiliatelink.offer.repository.OffreRepository;
import com.affiliatelink.offer.mapper.OffreMapper;
import com.affiliatelink.profile.ProfileApi;
import com.affiliatelink.offer.entity.Campaign;
import com.affiliatelink.offer.entity.CampaignStatus;
import com.affiliatelink.offer.entity.Offre;

@ExtendWith(MockitoExtension.class)
class OfferServiceTest {

    @Mock
    private CampaignRepository campaignRepository;

    @Mock
    private OffreRepository offreRepository;

    @Mock
    private OffreMapper offreMapper;

    @Mock
    private ProfileApi profileApi;

    @InjectMocks
    private CampaignService campaignService;

    @Test
    void shouldRejectCampaignWhenAdvertiserIsInactive() {

        Offre offre = mock(Offre.class);

        when(offreRepository.findById(1L))
                .thenReturn(Optional.of(offre));

        when(offre.getAdvertiserId()).thenReturn(10L);

        when(profileApi.isAdvertiserActive(10L))
                .thenReturn(false);

        assertThrows(
                IllegalStateException.class,
                () -> campaignService.createCampaign(1L, null)
        );

        verify(campaignRepository, never()).save(any());
    }

    @Test
void shouldActivateDraftCampaign() {

    Campaign campaign = mock(Campaign.class);
    Offre offre = mock(Offre.class);

    when(campaignRepository.findById(1L))
            .thenReturn(Optional.of(campaign));

    when(campaign.getOffre()).thenReturn(offre);
    when(offre.getId()).thenReturn(10L);

    when(campaignRepository.existsByOffre_IdAndStatusAndIdNot(
            10L, CampaignStatus.ACTIVE, 1L))
            .thenReturn(false);

    when(campaignRepository.save(campaign))
            .thenReturn(campaign);

    campaignService.activateCampaign(1L);

    verify(campaign).activate();
    verify(campaignRepository).save(campaign);
}
}