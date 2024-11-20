package com.org.vetconnect.platform.favorites.domain.services;

import com.org.vetconnect.platform.favorites.domain.model.aggregates.Favorite;
import com.org.vetconnect.platform.favorites.domain.model.commands.CreateFavoriteCommand;
import com.org.vetconnect.platform.favorites.domain.model.commands.DeleteFavoriteByIdCommand;

import java.util.Optional;

public interface FavoriteCommandService {
    Optional<Favorite> handle(CreateFavoriteCommand command);
    Optional<Favorite> handle(DeleteFavoriteByIdCommand command);
}
