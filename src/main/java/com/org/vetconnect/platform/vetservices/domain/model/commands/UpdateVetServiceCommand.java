package com.org.vetconnect.platform.vetservices.domain.model.commands;

public record UpdateVetServiceCommand(Long id, String name, String description, Double price, Integer duration, Boolean isActive) {
}
