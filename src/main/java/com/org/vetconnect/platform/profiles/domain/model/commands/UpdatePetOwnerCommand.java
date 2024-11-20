package com.org.vetconnect.platform.profiles.domain.model.commands;

public record UpdatePetOwnerCommand(
        Long id,
        String name,
        String email,
        String dni,
        String phone,
        String photo
) {
}
