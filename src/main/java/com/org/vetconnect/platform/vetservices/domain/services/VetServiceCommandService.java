package com.org.vetconnect.platform.vetservices.domain.services;

import com.org.vetconnect.platform.vetservices.domain.model.aggregates.VetService;
import com.org.vetconnect.platform.vetservices.domain.model.commands.CreateVetServiceCommand;
import com.org.vetconnect.platform.vetservices.domain.model.commands.UpdateVetServiceCommand;

import java.util.Optional;

public interface VetServiceCommandService {
    Optional<VetService> handle(CreateVetServiceCommand command);
    Optional<VetService> handle(UpdateVetServiceCommand command);
}
