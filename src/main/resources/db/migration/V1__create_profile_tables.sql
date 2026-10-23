
CREATE TABLE profile_profiles (
    id BIGINT NOT NULL AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL,
    status VARCHAR(20) NOT NULL,
    created_at DATETIME(6) NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT uk_profile_email UNIQUE (email)
);

CREATE TABLE profile_publishers (
    id BIGINT NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    niche VARCHAR(30) NOT NULL,
    bio VARCHAR(500),
    country VARCHAR(100) NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_publisher_profile
        FOREIGN KEY (id)
        REFERENCES profile_profiles(id)
);

CREATE TABLE profile_advertisers (
    id BIGINT NOT NULL,
    company_name VARCHAR(150) NOT NULL,
    sector VARCHAR(100) NOT NULL,
    website VARCHAR(255) NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_advertiser_profile
        FOREIGN KEY (id)
        REFERENCES profile_profiles(id)
);

