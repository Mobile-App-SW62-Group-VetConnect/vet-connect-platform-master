package com.org.vetconnect.platform.favorites.domain.services;

import com.org.vetconnect.platform.favorites.domain.model.aggregates.Favorite;
import com.org.vetconnect.platform.favorites.domain.model.queries.GetAllFavoritesQuery;
import com.org.vetconnect.platform.favorites.domain.model.queries.GetFavoriteByIdQuery;
import com.org.vetconnect.platform.favorites.domain.model.queries.GetFavoriteByUserIdQuery;

import java.util.List;
import java.util.Optional;

public interface FavoriteQueryService {
    List<Favorite> handle(GetAllFavoritesQuery query);
    Optional<Favorite> handle(GetFavoriteByIdQuery query);
    Optional<Favorite> handle(GetFavoriteByUserIdQuery query);
}
