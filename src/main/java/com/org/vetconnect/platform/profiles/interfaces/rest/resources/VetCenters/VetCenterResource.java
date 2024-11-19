package com.org.vetconnect.platform.profiles.interfaces.rest.resources.VetCenters;

public record VetCenterResource(
        Long id,
        String name,
        String email,
        String ruc,
        String phone,
        String imageProfile,
        String description
) {
}
