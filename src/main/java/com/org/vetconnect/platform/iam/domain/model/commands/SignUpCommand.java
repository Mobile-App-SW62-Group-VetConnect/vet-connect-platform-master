package com.org.vetconnect.platform.iam.domain.model.commands;

import java.util.List;

public record SignUpCommand(
        String username,
        String password,
        List<String> roles,  // Roles del usuario
        String clientDni,          // Para CLIENT (PetOwner)
        String clientName,         // Para CLIENT (PetOwner)
        String clientPhone,        // Para CLIENT (PetOwner)
        String clientAddress,      // Para CLIENT (PetOwner)

        // Para VETERINARY (VetCenter)
        String vetCenterRuc,
        String vetCenterClinicName,
        String vetCenterLicense,
        String vetCenterAddress,
        String vetCenterPhone
) { }