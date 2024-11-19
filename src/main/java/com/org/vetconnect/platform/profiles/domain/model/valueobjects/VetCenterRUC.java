package com.org.vetconnect.platform.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record VetCenterRUC(String vetCenterRUC) {
    public VetCenterRUC() {
        this("");
    }

    public VetCenterRUC {
        if (vetCenterRUC.isEmpty()) {
            throw new IllegalArgumentException("RUC cannot be null");
        }
        if (vetCenterRUC.length() != 11) {
            throw new IllegalArgumentException("RUC must have 11 digits");
        }
    }


}
