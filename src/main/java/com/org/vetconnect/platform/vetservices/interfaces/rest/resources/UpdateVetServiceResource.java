package com.org.vetconnect.platform.vetservices.interfaces.rest.resources;

public record UpdateVetServiceResource(String name, String description, Double price, Integer duration, Boolean isActive) {
}
