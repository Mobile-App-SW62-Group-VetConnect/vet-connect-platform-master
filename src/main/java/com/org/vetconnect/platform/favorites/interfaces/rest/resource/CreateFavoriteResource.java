package com.org.vetconnect.platform.favorites.interfaces.rest.resource;

import java.util.Date;

public record CreateFavoriteResource(String idFavorite, String userId, String veterinaryId, Date createdAt) {
}
