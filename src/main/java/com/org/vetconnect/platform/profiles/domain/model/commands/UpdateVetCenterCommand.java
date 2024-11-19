package com.org.vetconnect.platform.profiles.domain.model.commands;

import com.org.vetconnect.platform.profiles.domain.model.valueobjects.BusinessHour;

import java.util.List;

public record UpdateVetCenterCommand(
        Long id,
        String name,
        String email,
        String ruc,
        String phone,
        String imageProfile,
        String description,

        String address,
        List<BusinessHour> businessHours
) {
}
