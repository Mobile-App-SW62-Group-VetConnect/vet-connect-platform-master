package com.org.vetconnect.platform.favorites.domain.model.valueobjects;

public record Id(String id) {
    public Id() {this(null);}
    public Id {
        if (id == null) {
            throw new IllegalArgumentException("UserId can't be null");
        }
    }
}
