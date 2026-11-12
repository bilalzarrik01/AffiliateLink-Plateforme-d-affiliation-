package com.affiliatelink.offer.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.data.domain.PageRequest;

import com.affiliatelink.offer.entity.Offre;
import com.affiliatelink.profile.Niche;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
@TestPropertySource(properties = "spring.docker.compose.enabled=false")
class OffreRepositoryIT {

    @Container
    private static final MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.4");

    @Autowired
    private OffreRepository offreRepository;

    @DynamicPropertySource
    static void configureDataSource(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
    }

    @Test
    void shouldReturnOffersMatchingNiche() {
        // Given
        offreRepository.save(new Offre(1L, "Tech Offer", "Tech details", "https://tech.example", Niche.TECH));
        offreRepository.save(new Offre(2L, "Food Offer", "Food details", "https://food.example", Niche.FOOD));

        // When
        var result = offreRepository.findAll(
                OffreSpecification.hasNiche(Niche.TECH),
                PageRequest.of(0, 10));

        // Then
        assertThat(result.getContent()).extracting(Offre::getTitle).containsExactly("Tech Offer");
    }

    @Test
    void shouldReturnNoOffersWhenNicheDoesNotMatch() {
        // Given
        offreRepository.save(new Offre(1L, "Tech Offer", "Tech details", "https://tech.example", Niche.TECH));
        offreRepository.save(new Offre(2L, "Food Offer", "Food details", "https://food.example", Niche.FOOD));

        // When
        var result = offreRepository.findAll(
                OffreSpecification.hasNiche(Niche.BEAUTY),
                PageRequest.of(0, 10));

        // Then
        assertThat(result.getContent()).isEmpty();
    }
}
