CREATE TABLE offer_offres (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    advertiser_id BIGINT NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    landing_url VARCHAR(255) NOT NULL,
    niche VARCHAR(50) NOT NULL,
    created_at DATETIME(6) NOT NULL
);


CREATE TABLE offer_campaigns (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    budget DECIMAL(15,2) NOT NULL,
    payout_model VARCHAR(50) NOT NULL,
    payout_value DECIMAL(15,2) NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'DRAFT',
    offre_id BIGINT NOT NULL,

    CONSTRAINT fk_campaign_offre
        FOREIGN KEY (offre_id)
        REFERENCES offer_offres(id)
);


CREATE TABLE offer_tags (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

-- 4. Table de jointure Offre - Tag
CREATE TABLE offer_offre_tags (
    offre_id BIGINT NOT NULL,
    tag_id BIGINT NOT NULL,

    PRIMARY KEY (offre_id, tag_id),

    CONSTRAINT fk_offre_tags_offre
        FOREIGN KEY (offre_id)
        REFERENCES offer_offres(id),

    CONSTRAINT fk_offre_tags_tag
        FOREIGN KEY (tag_id)
        REFERENCES offer_tags(id)
);
