package com.org.vetconnect.platform.profiles.interfaces.rest.resources.PetOwners;

public record PetOwnerResource(
        Long id,
        String name,
        String email,
        String dni,
        String phone,
        String photo
) {
}
