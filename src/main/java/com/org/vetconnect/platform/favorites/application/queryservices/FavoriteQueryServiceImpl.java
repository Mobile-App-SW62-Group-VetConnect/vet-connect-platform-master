package com.org.vetconnect.platform.favorites.application.queryservices;

import com.org.vetconnect.platform.favorites.domain.model.aggregates.Favorite;
import com.org.vetconnect.platform.favorites.domain.model.queries.GetAllFavoritesQuery;
import com.org.vetconnect.platform.favorites.domain.model.queries.GetFavoriteByIdQuery;
import com.org.vetconnect.platform.favorites.domain.model.queries.GetFavoriteByUserIdQuery;
import com.org.vetconnect.platform.favorites.domain.services.FavoriteQueryService;
import com.org.vetconnect.platform.favorites.infrastructure.persistence.jpa.respositories.FavoriteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteQueryServiceImpl implements FavoriteQueryService {

    private final FavoriteRepository favoriteRepository;

    public FavoriteQueryServiceImpl(final FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    @Override
    public List<Favorite> handle(GetAllFavoritesQuery query) {
        return favoriteRepository.findAll();
    }

    @Override
    public Optional<Favorite> handle(GetFavoriteByIdQuery query) {
        return favoriteRepository.findById(query.idFavorite());
    }

    @Override
    public Optional<Favorite> handle(GetFavoriteByUserIdQuery query) {
        return favoriteRepository.findById(query.userId());
    }

}
