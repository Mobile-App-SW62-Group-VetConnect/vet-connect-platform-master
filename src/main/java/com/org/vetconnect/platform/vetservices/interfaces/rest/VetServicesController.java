package com.org.vetconnect.platform.vetservices.interfaces.rest;


import com.org.vetconnect.platform.vetservices.domain.model.queries.GetAllVetServicesQuery;
import com.org.vetconnect.platform.vetservices.domain.model.queries.GetVetServiceByIdQuery;
import com.org.vetconnect.platform.vetservices.domain.services.VetServiceCommandService;
import com.org.vetconnect.platform.vetservices.domain.services.VetServiceQueryService;
import com.org.vetconnect.platform.vetservices.interfaces.rest.resources.CreateVetServiceResource;
import com.org.vetconnect.platform.vetservices.interfaces.rest.resources.UpdateVetServiceResource;
import com.org.vetconnect.platform.vetservices.interfaces.rest.resources.VetServiceResource;
import com.org.vetconnect.platform.vetservices.interfaces.rest.transform.CreateVetServiceCommandFromResourceAssembler;
import com.org.vetconnect.platform.vetservices.interfaces.rest.transform.UpdateVetServiceCommandFromResourceAssembler;
import com.org.vetconnect.platform.vetservices.interfaces.rest.transform.VetServiceResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * VetServicesController
 * <p>
 *     This class is the entry point for all the REST endpoints related to the VetService entity.
 * </p>
 */

@RestController
@RequestMapping(value = "/api/v1/vet-services", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Vet Services", description = "Vet Services Management Endpoints")
public class VetServicesController {

    private final VetServiceQueryService vetServiceQueryService;
    private final VetServiceCommandService vetServiceCommandService;

    public VetServicesController(VetServiceQueryService vetServiceQueryService, VetServiceCommandService vetServiceCommandService) {
        this.vetServiceQueryService = vetServiceQueryService;
        this.vetServiceCommandService = vetServiceCommandService;
    }

    @PostMapping
    public ResponseEntity<VetServiceResource> createVetService(@RequestBody CreateVetServiceResource resource) {
        var createVetServiceCommand = CreateVetServiceCommandFromResourceAssembler.toCommandFromResource(resource);
        var vetService = vetServiceCommandService.handle(createVetServiceCommand);
        if (vetService.isEmpty()) return ResponseEntity.badRequest().build();
        var vetServiceResource = VetServiceResourceFromEntityAssembler.toResourceFromEntity(vetService.get());
        return new ResponseEntity<>(vetServiceResource, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<VetServiceResource>> getAllVetServices() {
        var getAllVetServicesQuery = new GetAllVetServicesQuery();
        var VetServices = vetServiceQueryService.handle(getAllVetServicesQuery);
        var vetServiceResources = VetServices.stream()
                .map(VetServiceResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return new ResponseEntity<>(vetServiceResources, HttpStatus.OK);
    }

    @GetMapping("/{vetServiceId}")
    public ResponseEntity<VetServiceResource> getVetServiceById(@PathVariable Long vetServiceId) {
        var getVetServiceByIdQuery = new GetVetServiceByIdQuery(vetServiceId);
        var vetService = vetServiceQueryService.handle(getVetServiceByIdQuery);
        if (vetService.isEmpty()) return ResponseEntity.badRequest().build();
        var vetServiceResource = VetServiceResourceFromEntityAssembler.toResourceFromEntity(vetService.get());
        return ResponseEntity.ok(vetServiceResource);
    }

    @PutMapping("/{vetServiceId}")
    public ResponseEntity<VetServiceResource> updateVetService(@PathVariable Long vetServiceId, @RequestBody UpdateVetServiceResource resource) {
        var updateVetServiceCommand = UpdateVetServiceCommandFromResourceAssembler.toCommandFromResource(resource);
        var vetService = vetServiceCommandService.handle(updateVetServiceCommand);
        if (vetService.isEmpty()) return ResponseEntity.badRequest().build();
        var vetServiceResource = VetServiceResourceFromEntityAssembler.toResourceFromEntity(vetService.get());
        return ResponseEntity.ok(vetServiceResource);
    }

}
