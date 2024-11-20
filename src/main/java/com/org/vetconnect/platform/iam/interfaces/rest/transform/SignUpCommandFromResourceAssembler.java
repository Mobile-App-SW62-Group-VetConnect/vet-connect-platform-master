package com.org.vetconnect.platform.iam.interfaces.rest.transform;

import com.org.vetconnect.platform.iam.domain.model.commands.SignUpCommand;
import com.org.vetconnect.platform.iam.interfaces.rest.resources.SignUpResource;

public class SignUpCommandFromResourceAssembler {

    public static SignUpCommand toCommandFromResource(SignUpResource resource) {

        // Aquí necesitamos extraer los campos de resource y pasarlos al constructor de SignUpCommand
        return new SignUpCommand(
                resource.email(),               // username
                resource.password(),            // password
                resource.roles(),               // roles
                resource.dni(),                 // dni (si es CLIENT)
                resource.name(),                // name (si es CLIENT)
                resource.phone(),               // phone (si es CLIENT)
                resource.address(),             // address (si es CLIENT)
                resource.ruc(),                 // ruc (si es VETERINARY)
                resource.clinicName(),          // clinicName (si es VETERINARY)
                resource.license(),             // license (si es VETERINARY)
                resource.vetCenterAddress(),    // vetCenterAddress (si es VETERINARY)
                resource.vetCenterPhone()       // vetCenterPhone (si es VETERINARY)
        );
    }
}
