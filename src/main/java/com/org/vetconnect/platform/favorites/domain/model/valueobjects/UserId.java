package com.org.vetconnect.platform.favorites.domain.model.valueobjects;

import com.fasterxml.jackson.annotation.JsonValue;

public record UserId(String userId) {
    public UserId() {this(null);}
    public UserId {
        if (userId == null) {
            throw new IllegalArgumentException("UserId can't be null");
        }
    }
    @JsonValue
    public String getUserId() {
        return userId;
    }
}
