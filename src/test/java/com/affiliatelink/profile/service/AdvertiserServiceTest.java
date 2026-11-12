package com.affiliatelink.profile.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.affiliatelink.common.exception.EmailAlreadyExistsException;
import com.affiliatelink.profile.dto.CreateAdvertiserRequest;
import com.affiliatelink.profile.entity.Advertiser;
import com.affiliatelink.profile.entity.ProfileStatus;
import com.affiliatelink.profile.repository.AdvertiserRepository;
import com.affiliatelink.profile.repository.ProfileRepository;

@ExtendWith(MockitoExtension.class)
class AdvertiserServiceTest {

    @Mock
    private AdvertiserRepository advertiserRepository;

    @Mock
    private ProfileRepository profileRepository;

    @InjectMocks
    private AdvertiserService advertiserService;

    @Test
    void shouldSaveNewAdvertiserWithPendingStatus() {
        // Given
        CreateAdvertiserRequest request = new CreateAdvertiserRequest(
                "new@example.com",
                "Example Company",
                "Technology",
                "https://example.com");
        when(profileRepository.existsByEmail(request.email())).thenReturn(false);
        when(advertiserRepository.save(any(Advertiser.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // When
        var response = advertiserService.createAdvertiser(request);

        // Then
        ArgumentCaptor<Advertiser> savedAdvertiser = ArgumentCaptor.forClass(Advertiser.class);
        verify(advertiserRepository).save(savedAdvertiser.capture());
        assertThat(savedAdvertiser.getValue().getEmail()).isEqualTo(request.email());
        assertThat(response.status()).isEqualTo(ProfileStatus.PENDING);
    }

    @Test
    void shouldRejectDuplicateEmailWithoutSavingAdvertiser() {
        // Given
        CreateAdvertiserRequest request = new CreateAdvertiserRequest(
                "existing@example.com",
                "Example Company",
                "Technology",
                "https://example.com");
        when(profileRepository.existsByEmail(request.email())).thenReturn(true);

        // When / Then
        assertThatThrownBy(() -> advertiserService.createAdvertiser(request))
                .isInstanceOf(EmailAlreadyExistsException.class);
        verify(advertiserRepository, never()).save(any(Advertiser.class));
    }
}
