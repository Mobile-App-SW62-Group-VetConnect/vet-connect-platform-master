package com.org.vetconnect.platform.favorites.interfaces.rest.resource;

import com.org.vetconnect.platform.favorites.domain.model.valueobjects.IdFavorite;
import com.org.vetconnect.platform.favorites.domain.model.valueobjects.UserId;
import com.org.vetconnect.platform.favorites.domain.model.valueobjects.VeterinaryId;

import java.util.Date;

public record FavoriteResource(IdFavorite idFavorite, UserId userId, VeterinaryId veterinaryId, Date createdAt) {
}
