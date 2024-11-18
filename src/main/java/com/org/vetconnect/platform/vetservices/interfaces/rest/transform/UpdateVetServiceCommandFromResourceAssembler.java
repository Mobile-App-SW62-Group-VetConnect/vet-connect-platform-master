package com.org.vetconnect.platform.vetservices.interfaces.rest.transform;

import com.org.vetconnect.platform.vetservices.domain.model.commands.UpdateVetServiceCommand;
import com.org.vetconnect.platform.vetservices.interfaces.rest.resources.UpdateVetServiceResource;

public class UpdateVetServiceCommandFromResourceAssembler {
    public static UpdateVetServiceCommand toCommandFromResource(UpdateVetServiceResource resource, Long id) {
        return new UpdateVetServiceCommand(id, resource.name(), resource.description(), resource.price(), resource.duration(), resource.isActive());
    }
}
