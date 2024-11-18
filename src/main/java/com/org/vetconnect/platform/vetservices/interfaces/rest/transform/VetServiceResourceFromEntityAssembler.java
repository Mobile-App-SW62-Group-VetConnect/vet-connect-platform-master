package com.org.vetconnect.platform.vetservices.interfaces.rest.transform;

import com.org.vetconnect.platform.vetservices.domain.model.aggregates.VetService;
import com.org.vetconnect.platform.vetservices.interfaces.rest.resources.VetServiceResource;

public class VetServiceResourceFromEntityAssembler {
    public static VetServiceResource toResourceFromEntity(VetService entity) {
        return new VetServiceResource(entity.getName(), entity.getDescription(), entity.getPrice(), entity.getDuration(), entity.getCategory(), entity.getFeatures(), entity.getIsActive());
    }
}
