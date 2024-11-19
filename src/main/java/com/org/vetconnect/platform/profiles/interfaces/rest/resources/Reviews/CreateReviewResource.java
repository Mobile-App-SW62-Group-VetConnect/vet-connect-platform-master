package com.org.vetconnect.platform.profiles.interfaces.rest.resources.Reviews;

// record que recibe los datos necesarios para crear una reseña
public record CreateReviewResource(
        Long vetCenterId,
        Long petOwnerId,
        Integer rating,
        String comments
) {
}
