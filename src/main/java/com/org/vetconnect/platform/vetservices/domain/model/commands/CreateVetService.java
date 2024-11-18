package com.org.vetconnect.platform.vetservices.domain.model.commands;

import com.org.vetconnect.platform.vetservices.domain.model.valueobjects.ServiceCategory;

import java.util.List;

public record CreateVetService(String name, String description, Double price, Integer duration, ServiceCategory category, List<String> features, Boolean isActive) {
}
