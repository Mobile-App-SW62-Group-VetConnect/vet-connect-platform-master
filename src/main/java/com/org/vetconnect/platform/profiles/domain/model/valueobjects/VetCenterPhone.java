package com.org.vetconnect.platform.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record VetCenterPhone(String vetCenterPhone) {

        // phone not null and phone length is 9
        public VetCenterPhone(String vetCenterPhone) {
            if (!vetCenterPhone.isEmpty() && vetCenterPhone.toString().length() == 9) {
                this.vetCenterPhone = vetCenterPhone;
            } else {
                throw new IllegalArgumentException("Phone must be 9 digits");
            }
        }

        public String getVetCenterPhone() {
            return vetCenterPhone;
        }
}
