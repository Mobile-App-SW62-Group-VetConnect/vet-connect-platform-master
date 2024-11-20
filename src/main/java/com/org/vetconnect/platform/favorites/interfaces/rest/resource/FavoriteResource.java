package com.org.vetconnect.platform.favorites.interfaces.rest.resource;

import java.util.Date;

public record FavoriteResource(Long id, Long userId, Long veterinaryId, Date createdAt) {
}
