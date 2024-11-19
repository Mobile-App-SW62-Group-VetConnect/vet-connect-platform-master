package com.org.vetconnect.platform.profiles.interfaces.rest.resources.Reviews;

import java.util.Date;

// devuelve los datos de una reseña en las respuestas del controlador
public record ReviewResource(
        Long id,
        Long veterinaryId,
        Long userId,
        String userName,
        Integer rating,
        String comment,
        Date createdAt,
        Date updatedAt

) {
}
