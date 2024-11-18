package com.org.vetconnect.platform.favorites.interfaces.rest;

import com.org.vetconnect.platform.favorites.domain.model.aggregates.Favorite;
import com.org.vetconnect.platform.favorites.domain.model.commands.DeleteFavoriteByIdCommand;
import com.org.vetconnect.platform.favorites.domain.model.exceptions.ResourceNotFoundException;
import com.org.vetconnect.platform.favorites.domain.model.queries.GetAllFavoritesQuery;
import com.org.vetconnect.platform.favorites.domain.model.queries.GetFavoriteByIdQuery;
import com.org.vetconnect.platform.favorites.domain.model.queries.GetFavoriteByUserIdQuery;
import com.org.vetconnect.platform.favorites.domain.services.FavoriteCommandService;
import com.org.vetconnect.platform.favorites.domain.services.FavoriteQueryService;
import com.org.vetconnect.platform.favorites.interfaces.rest.resource.CreateFavoriteResource;
import com.org.vetconnect.platform.favorites.interfaces.rest.resource.FavoriteResource;
import com.org.vetconnect.platform.favorites.interfaces.rest.transform.CreateFavoriteCommandFromResourceAssembler;
import com.org.vetconnect.platform.favorites.interfaces.rest.transform.FavoriteResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/api/v1/favorites", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Favorites", description = "Favorites Management Endpoints")

public class FavoritesController {

    private final FavoriteCommandService favoriteCommandService;
    private final FavoriteQueryService favoriteQueryService;

    public FavoritesController(FavoriteCommandService favoriteCommandService, FavoriteQueryService favoriteQueryService) {
        this.favoriteCommandService = favoriteCommandService;
        this.favoriteQueryService = favoriteQueryService;
    }

    @PostMapping
    public ResponseEntity<FavoriteResource> createFavorite(@RequestBody CreateFavoriteResource resource){
        var createFavoriteCommand = CreateFavoriteCommandFromResourceAssembler.toCommandFromResource(resource);
        var favorite = favoriteCommandService.handle(createFavoriteCommand);
        if(favorite.isEmpty()) return ResponseEntity.badRequest().build();
        var FavoriteResource = FavoriteResourceFromEntityAssembler.toResourceFromEntity(favorite.get());
        return new ResponseEntity<>(FavoriteResource, HttpStatus.CREATED);
    }

    @GetMapping("/by-id/{idFavorite}")
    public ResponseEntity<FavoriteResource> getFavoriteById(@PathVariable String idFavorite){
        var getFavoriteByIdQuery = new GetFavoriteByIdQuery(idFavorite);
        var favorite = favoriteQueryService.handle(getFavoriteByIdQuery);
        if(favorite.isEmpty()) return ResponseEntity.badRequest().build();
        var favoriteResource = FavoriteResourceFromEntityAssembler.toResourceFromEntity(favorite.get());
        return ResponseEntity.ok(favoriteResource);
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<FavoriteResource> getFavoriteByUserId(@PathVariable String userId){
        var getFavoriteByUserIdQuery = new GetFavoriteByUserIdQuery(userId);
        var favorite = favoriteQueryService.handle(getFavoriteByUserIdQuery);
        if(favorite.isEmpty()) return ResponseEntity.badRequest().build();
        var favoriteResource = FavoriteResourceFromEntityAssembler.toResourceFromEntity(favorite.get());
        return ResponseEntity.ok(favoriteResource);
    }

    @GetMapping
    public ResponseEntity<List<FavoriteResource>> getAllFavorites() {
        var getAllFavoritesQuery = new GetAllFavoritesQuery();
        var favorite = favoriteQueryService.handle(getAllFavoritesQuery);

        if (favorite.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        var favoriteResources = favorite.stream().map(FavoriteResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());

        return ResponseEntity.ok(favoriteResources);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFavorite(@PathVariable String id) {
        try {
            Optional<Favorite> deletedFavorite = favoriteCommandService.handle(new DeleteFavoriteByIdCommand(id));
            return deletedFavorite.isPresent() ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
