package com.org.vetconnect.platform.profiles.domain.model.valueobjects;

public record PetOwnerPhone(String petOwnerPhone) {
    public PetOwnerPhone() {
        this("");
    }

    // phone not null and phone length is 9
    public PetOwnerPhone(String petOwnerPhone) {
        if (petOwnerPhone != null && petOwnerPhone.length() == 9) {
            this.petOwnerPhone = petOwnerPhone;
        } else {
            throw new IllegalArgumentException("Phone must be 9 digits");
        }
    }

    public String getPhone() {
        return petOwnerPhone;
    }
}
