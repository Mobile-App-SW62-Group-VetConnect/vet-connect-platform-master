package com.org.vetconnect.platform.vetservices.application.internal.commandservices;

import com.org.vetconnect.platform.profiles.infrastructure.persistence.jpa.repositories.VetCenterRepository;
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
    private final VetCenterRepository vetCenterRepository;

    public VetServiceCommandServiceImpl(VetServiceRepository vetServiceRepository, VetCenterRepository vetCenterRepository) {
        this.vetServiceRepository = vetServiceRepository;
        this.vetCenterRepository = vetCenterRepository;
    }

    @Override
    public Optional<VetService> handle(CreateVetServiceCommand command) {
        var vetCenter = vetCenterRepository.findById(command.vetId());
        if (vetCenter.isEmpty()) throw new IllegalArgumentException("VetCenter does not exist");
        var VetService = new VetService(vetCenter.get(), command.name(), command.description(), command.price(), command.duration(), command.category(), command.features(), command.isActive());
        vetServiceRepository.save(VetService);
        return Optional.of(VetService);
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
