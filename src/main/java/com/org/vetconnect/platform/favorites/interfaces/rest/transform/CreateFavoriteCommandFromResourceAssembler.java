package com.org.vetconnect.platform.favorites.interfaces.rest.transform;

import com.org.vetconnect.platform.favorites.domain.model.commands.CreateFavoriteCommand;
import com.org.vetconnect.platform.favorites.interfaces.rest.resource.CreateFavoriteResource;

public class CreateFavoriteCommandFromResourceAssembler {
    public static CreateFavoriteCommand toCommandFromResource(CreateFavoriteResource resource) {
        return new CreateFavoriteCommand(resource.userId(), resource.veterinaryId());
    }
}
