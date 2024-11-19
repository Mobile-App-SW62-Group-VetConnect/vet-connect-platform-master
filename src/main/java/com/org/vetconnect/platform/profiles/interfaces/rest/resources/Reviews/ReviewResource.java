package com.org.vetconnect.platform.profiles.interfaces.rest.resources.Reviews;

// devuelve los datos de una reseña en las respuestas del controlador
public record ReviewResource(
        Long id,
        Long userId,
        String userName,
        Long vetCenterId,
        String comments,
        int rating
) {
}
