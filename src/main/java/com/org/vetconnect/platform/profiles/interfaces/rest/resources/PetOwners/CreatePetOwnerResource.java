package com.org.vetconnect.platform.profiles.interfaces.rest.resources.PetOwners;

public record CreatePetOwnerResource(
        String name,
        String email,
        String dni,
        Long phone,
        String photo
) {
}
