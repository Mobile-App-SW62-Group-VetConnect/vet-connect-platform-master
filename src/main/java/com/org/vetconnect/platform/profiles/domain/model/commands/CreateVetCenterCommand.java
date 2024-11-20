package com.org.vetconnect.platform.profiles.domain.model.commands;

public record CreateVetCenterCommand(
        String name,
        String email,
        String ruc,
        String phone,
        String imageProfile,
        String description
) {
}
