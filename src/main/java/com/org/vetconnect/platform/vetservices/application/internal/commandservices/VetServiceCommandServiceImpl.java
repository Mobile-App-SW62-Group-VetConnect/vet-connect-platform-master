package com.org.vetconnect.platform.vetservices.application.internal.commandservices;

import com.org.vetconnect.platform.vetservices.domain.model.aggregates.VetService;
import com.org.vetconnect.platform.vetservices.domain.model.commands.CreateVetServiceCommand;
import com.org.vetconnect.platform.vetservices.domain.model.commands.UpdateVetServiceCommand;
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

    @Override
    public Optional<VetService> handle(UpdateVetServiceCommand command) {
        var result = vetServiceRepository.findById(command.id());
        if (result.isEmpty()) throw new IllegalArgumentException("VetService does not exist");
        var vetServiceToUpdate = result.get();
        try {
            var updatedVetService = vetServiceRepository.save(vetServiceToUpdate.updateInformation(command.name(), command.description(), command.price(), command.duration(), command.isActive()));
            return Optional.of(updatedVetService);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating VetService: " + e.getMessage());
        }
    }
}
