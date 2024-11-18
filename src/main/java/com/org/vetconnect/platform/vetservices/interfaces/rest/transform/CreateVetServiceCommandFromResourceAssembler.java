package com.org.vetconnect.platform.vetservices.interfaces.rest.transform;

import com.org.vetconnect.platform.vetservices.domain.model.commands.CreateVetServiceCommand;
import com.org.vetconnect.platform.vetservices.interfaces.rest.resources.CreateVetServiceResource;

public class CreateVetServiceCommandFromResourceAssembler {
    public static CreateVetServiceCommand toCommandFromResource(CreateVetServiceResource resource) {
        return new CreateVetServiceCommand(resource.name(), resource.description(), resource.price(), resource.duration(), resource.category(), resource.features(), resource.isActive());
    }
}
