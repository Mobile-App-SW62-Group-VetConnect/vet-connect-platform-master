package com.org.vetconnect.platform.vetservices.application.internal.queryservices;

import com.org.vetconnect.platform.vetservices.domain.model.aggregates.VetService;
import com.org.vetconnect.platform.vetservices.domain.model.queries.GetAllVetServicesQuery;
import com.org.vetconnect.platform.vetservices.domain.model.queries.GetVetServiceByIdQuery;
import com.org.vetconnect.platform.vetservices.domain.model.queries.GetVetServicesByVetCenterIdQuery;
import com.org.vetconnect.platform.vetservices.domain.services.VetServiceQueryService;
import com.org.vetconnect.platform.vetservices.infrastructure.persistence.jpa.repositories.VetServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VetServiceQueryServiceImpl implements VetServiceQueryService {

    private final VetServiceRepository vetServiceRepository;

    public VetServiceQueryServiceImpl(VetServiceRepository vetServiceRepository) {
        this.vetServiceRepository = vetServiceRepository;
    }

    @Override
    public Optional<VetService> handle(GetVetServiceByIdQuery query) {
        return vetServiceRepository.findById(query.vetServiceId());
    }

    @Override
    public List<VetService> handle(GetAllVetServicesQuery query) {
        return vetServiceRepository.findAll();
    }

    @Override
    public List<VetService> handle(GetVetServicesByVetCenterIdQuery query) {
        return vetServiceRepository.findByVetCenterId(query.vetCenterId());
    }
}
