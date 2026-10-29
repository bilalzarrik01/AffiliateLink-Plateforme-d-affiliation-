package com.affiliateLink.common.exception;

public class AdvertiserNotFoundException extends RuntimeException {
public AdvertiserNotFoundException(String message) {
    super(message);
}
    public AdvertiserNotFoundException(Long id) {
        super("Advertiser not found with id: " + id);
    }
}
