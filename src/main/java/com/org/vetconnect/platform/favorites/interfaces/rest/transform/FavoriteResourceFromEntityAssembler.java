package com.org.vetconnect.platform.favorites.interfaces.rest.transform;

import com.org.vetconnect.platform.favorites.domain.model.aggregates.Favorite;
import com.org.vetconnect.platform.favorites.interfaces.rest.resource.FavoriteResource;

public class FavoriteResourceFromEntityAssembler {
    public static FavoriteResource toResourceFromEntity(Favorite entity) {
        return new FavoriteResource(entity.getIdFavorite(),
                entity.getUserId(),
                entity.getVeterinaryId(),
                entity.getCreatedAt()
        );
    }
}
