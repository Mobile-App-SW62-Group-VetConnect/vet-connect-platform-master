package com.org.vetconnect.platform.vetservices.interfaces.rest.resources;

public record UpdateVetServiceResource(Long id, String name, String description, Double price, Integer duration, Boolean isActive) {
}
