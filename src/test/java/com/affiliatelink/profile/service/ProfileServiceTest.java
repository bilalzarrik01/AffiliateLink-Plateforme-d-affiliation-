package com.affiliatelink.profile.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.mock;
import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import com.affiliatelink.common.exception.InvalidStatusTransitionException;
import com.affiliatelink.common.exception.ProfileNotFoundException;
import com.affiliatelink.profile.ProfileSuspended;
import com.affiliatelink.profile.entity.Advertiser;
import com.affiliatelink.profile.entity.Profile;
import com.affiliatelink.profile.entity.ProfileStatus;
import com.affiliatelink.profile.repository.ProfileRepository;

@ExtendWith(MockitoExtension.class)
class ProfileServiceTest {

    @Mock
    private ProfileRepository profileRepository;

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    @InjectMocks
    private ProfileService profileService;

    @Test
    void shouldCreateAdvertiserWithPendingStatus() {
        Advertiser advertiser = new Advertiser(
                "test@gmail.com",
                "Test Company",
                "Technology",
                "https://test.com"
        );

        assertEquals(ProfileStatus.PENDING, advertiser.getStatus());
    }

   
@Test
void shouldActivatePendingProfile() {
    Advertiser advertiser = new Advertiser(
            "active@gmail.com",
            "Test Company",
            "Technology",
            "https://test.com"
    );

    when(profileRepository.findById(1L))
            .thenReturn(Optional.of(advertiser));

    profileService.updateStatus(1L, ProfileStatus.ACTIVE);

    assertEquals(ProfileStatus.ACTIVE, advertiser.getStatus());

    verify(profileRepository).findById(1L);
    verify(profileRepository, never()).save(any());

    verify(applicationEventPublisher, never())
            .publishEvent(any(ProfileSuspended.class));
}


    @Test
    void shouldPublishProfileSuspendedWhenSuspendingActiveProfile() {
        Profile profile = mock(Profile.class);
        when(profile.getStatus()).thenReturn(ProfileStatus.ACTIVE);
        when(profile.getId()).thenReturn(3L);
        when(profileRepository.findById(3L)).thenReturn(Optional.of(profile));

        profileService.updateStatus(3L, ProfileStatus.SUSPENDED);

        verify(profile).suspend();
        verify(applicationEventPublisher).publishEvent(new ProfileSuspended(3L));
    }

    @Test
    void shouldRejectSuspendingPendingProfile() {
        Advertiser advertiser = new Advertiser(
                "suspended@gmail.com",
                "Test Company",
                "Technology",
                "https://test.com"
        );

        when(profileRepository.findById(2L))
                .thenReturn(Optional.of(advertiser));

        assertThrows(
                InvalidStatusTransitionException.class,
                () -> profileService.updateStatus(2L, ProfileStatus.SUSPENDED)
        );

        verify(profileRepository, never()).save(advertiser);
    }

    @Test
    void shouldThrowExceptionWhenProfileDoesNotExist() {
        when(profileRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                ProfileNotFoundException.class,
                () -> profileService.updateStatus(99L, ProfileStatus.ACTIVE)
        );

        verify(profileRepository, never()).save(any());
    }
}
