package com.org.vetconnect.platform.vetservices.domain.services;

import com.org.vetconnect.platform.vetservices.domain.model.aggregates.VetService;
import com.org.vetconnect.platform.vetservices.domain.model.queries.GetAllVetServicesQuery;
import com.org.vetconnect.platform.vetservices.domain.model.queries.GetVetServiceByIdQuery;
import com.org.vetconnect.platform.vetservices.domain.model.queries.GetVetServicesByVetCenterIdQuery;

import java.util.List;
import java.util.Optional;

public interface VetServiceQueryService {
    Optional<VetService> handle(GetVetServiceByIdQuery query);
    List<VetService> handle(GetAllVetServicesQuery query);

    List<VetService> handle(GetVetServicesByVetCenterIdQuery query);
}
