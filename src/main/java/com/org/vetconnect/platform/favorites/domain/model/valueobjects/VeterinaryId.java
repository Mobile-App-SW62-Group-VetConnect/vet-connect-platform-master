package com.org.vetconnect.platform.favorites.domain.model.valueobjects;

import com.fasterxml.jackson.annotation.JsonValue;

public record VeterinaryId(String veterinaryId) {
    public VeterinaryId() {this(null);}
    public VeterinaryId {
        if (veterinaryId == null) {
            throw new IllegalArgumentException("VeterinaryId can't be null");
        }
    }
    @JsonValue
    public String getveterinaryId() {
        return veterinaryId;
    }
}
