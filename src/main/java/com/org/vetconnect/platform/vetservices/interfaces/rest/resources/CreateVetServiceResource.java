package com.org.vetconnect.platform.vetservices.interfaces.rest.resources;

import com.org.vetconnect.platform.vetservices.domain.model.valueobjects.ServiceCategory;

import java.util.List;

public record CreateVetServiceResource(Long vetId, String name, String description, Double price, Integer duration, ServiceCategory category, List<String> features, Boolean isActive) {
}
