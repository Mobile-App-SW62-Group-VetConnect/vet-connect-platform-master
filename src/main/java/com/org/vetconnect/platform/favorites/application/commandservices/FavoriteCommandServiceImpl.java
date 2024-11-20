package com.org.vetconnect.platform.favorites.application.commandservices;

import com.org.vetconnect.platform.favorites.domain.model.aggregates.Favorite;
import com.org.vetconnect.platform.favorites.domain.model.commands.CreateFavoriteCommand;
import com.org.vetconnect.platform.favorites.domain.model.commands.DeleteFavoriteByIdCommand;
import com.org.vetconnect.platform.favorites.domain.model.exceptions.ResourceNotFoundException;
import com.org.vetconnect.platform.favorites.domain.services.FavoriteCommandService;
import com.org.vetconnect.platform.favorites.infrastructure.persistence.jpa.respositories.FavoriteRepository;
import com.org.vetconnect.platform.profiles.infrastructure.persistence.jpa.repositories.PetOwnerRepository;
import com.org.vetconnect.platform.profiles.infrastructure.persistence.jpa.repositories.VetCenterRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.Optional;

@Service
public class FavoriteCommandServiceImpl implements FavoriteCommandService {

    private final FavoriteRepository favoriteRepository;
    private final PetOwnerRepository petOwnerRepository;
    private final VetCenterRepository vetCenterRepository;


    public FavoriteCommandServiceImpl(FavoriteRepository favoriteRepository, PetOwnerRepository petOwnerRepository, VetCenterRepository vetCenterRepository) {
        this.favoriteRepository = favoriteRepository;
        this.petOwnerRepository = petOwnerRepository;
        this.vetCenterRepository = vetCenterRepository;
    }

    @Override
    public Optional<Favorite> handle(CreateFavoriteCommand command) {
        var petOwner = petOwnerRepository.findById(command.userId());
        if(petOwner.isEmpty()){
            throw new ResourceNotFoundException("Pet Owner with id " + command.userId() + " not found.");
        }
        var vetCenter = vetCenterRepository.findById(command.veterinaryId());
        if(vetCenter.isEmpty()){
            throw new ResourceNotFoundException("Vet Center with id " + command.veterinaryId() + " not found.");
        }
        var favorite = new Favorite(petOwner.get(), vetCenter.get());
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
