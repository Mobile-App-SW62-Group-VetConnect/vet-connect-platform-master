package com.org.vetconnect.platform.favorites.application.commandservices;

import com.org.vetconnect.platform.favorites.domain.model.aggregates.Favorite;
import com.org.vetconnect.platform.favorites.domain.model.commands.CreateFavoriteCommand;
import com.org.vetconnect.platform.favorites.domain.model.commands.DeleteFavoriteByIdCommand;
import com.org.vetconnect.platform.favorites.domain.model.exceptions.ResourceNotFoundException;
import com.org.vetconnect.platform.favorites.domain.services.FavoriteCommandService;
import com.org.vetconnect.platform.favorites.infrastructure.persistence.jpa.respositories.FavoriteRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.Optional;

@Service
public class FavoriteCommandServiceImpl implements FavoriteCommandService {

    private final FavoriteRepository favoriteRepository;

    public FavoriteCommandServiceImpl(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    @Override
    public Optional<Favorite> handle(CreateFavoriteCommand command) {
        var favorite = new Favorite(command);
        var createdFavorite = favoriteRepository.save(favorite);
        return Optional.of(createdFavorite);

    }

    @Override
    public Optional<Favorite> handle(DeleteFavoriteByIdCommand command) {
        Optional<Favorite> existingFavoriteOpt = favoriteRepository.findById(command.idFavorite());

        if (existingFavoriteOpt.isPresent()) {
            Favorite existingEmployer = existingFavoriteOpt.get();
            favoriteRepository.delete(existingEmployer);
            return Optional.of(existingEmployer);
        } else {
            throw new ResourceNotFoundException("Employer profile with id " + command.idFavorite() + " not found.");
        }
    }

}
