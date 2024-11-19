package com.org.vetconnect.platform.profiles.interfaces.rest.resources.VetCenters;

import com.org.vetconnect.platform.profiles.domain.model.valueobjects.BusinessHour;

import java.util.List;

public record VetCenterResource(
        Long id,
        String name,
        String address,
        String imageProfile,
        String description,
        List<BusinessHour> businessHours,
        Contact contact
) {
    public static class Contact {
        private final String phone;
        private final String email;


        public Contact(String phone, String email) {
            this.phone = phone;
            this.email = email;

        }

        public String getPhone() {
            return phone;
        }

        public String getEmail() {
            return email;
        }

    }
}
