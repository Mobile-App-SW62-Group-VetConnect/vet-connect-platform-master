package com.org.vetconnect.platform.vetservices.application.internal.commandservices;

import com.org.vetconnect.platform.vetservices.domain.model.aggregates.VetService;
import com.org.vetconnect.platform.vetservices.domain.model.commands.CreateVetServiceCommand;
import com.org.vetconnect.platform.vetservices.domain.services.VetServiceCommandService;
import com.org.vetconnect.platform.vetservices.infrastructure.persistence.jpa.repositories.VetServiceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VetServiceCommandServiceImpl implements VetServiceCommandService {

    private final VetServiceRepository vetServiceRepository;

    public VetServiceCommandServiceImpl(VetServiceRepository vetServiceRepository) {
        this.vetServiceRepository = vetServiceRepository;
    }

    @Override
    public Optional<VetService> handle(CreateVetServiceCommand command) {
        var VetSercice = new VetService(command);
        vetServiceRepository.save(VetSercice);
        return Optional.of(VetSercice);
    }
}
