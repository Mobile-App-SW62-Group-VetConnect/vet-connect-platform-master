package com.org.vetconnect.platform.favorites.domain.model.valueobjects;

import com.fasterxml.jackson.annotation.JsonValue;

public record IdFavorite(String idFavorite) {
    public IdFavorite() {this(null);}
    public IdFavorite {
        if (idFavorite == null) {
            throw new IllegalArgumentException("UserId can't be null");
        }
    }
    @JsonValue
    public String getidFavorite() {
        return idFavorite;
    }
}
