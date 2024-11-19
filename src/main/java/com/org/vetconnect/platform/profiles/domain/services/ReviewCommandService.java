package com.org.vetconnect.platform.profiles.domain.services;

import com.org.vetconnect.platform.profiles.domain.model.commands.CreateReviewCommand;
import com.org.vetconnect.platform.profiles.domain.model.commands.DeleteReviewCommand;
import com.org.vetconnect.platform.profiles.domain.model.entities.Review;

public interface ReviewCommandService {
    Review handle(CreateReviewCommand command); // solo devuelve el id de la review creada
    void handle(DeleteReviewCommand command); // solo elimina la review (no devuelve nada
}
