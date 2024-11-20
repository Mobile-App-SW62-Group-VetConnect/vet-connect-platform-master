package com.org.vetconnect.platform.iam.interfaces.rest.resources;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public class SignUpResource {

    public String email;
    public String password;
    public List<String> roles;
    public String dni;            // Para CLIENT
    public String name;           // Para CLIENT
    public String phone;          // Para CLIENT
    public String address;        // Para CLIENT

    public String vetCenterRuc;            // Para VETERINARY
    public String vetCenterClinicName;     // Para VETERINARY
    public String vetCenterLicense;        // Para VETERINARY
    public String vetCenterAddress; // Para VETERINARY
    public String vetCenterPhone;   // Para VETERINARY

    // Métodos getter y setter para todos los campos

    public String email() {
        return email;
    }

    public String password() {
        return password;
    }

    public List<String> roles() {
        return roles;
    }

    public String dni() {
        return dni;
    }

    public String name() {
        return name;
    }

    public String phone() {
        return phone;
    }

    public String address() {
        return address;
    }

    public String ruc() {
        return vetCenterRuc;
    }

    public String clinicName() {
        return vetCenterClinicName;
    }

    public String license() {
        return vetCenterLicense;
    }

    public String vetCenterAddress() {
        return vetCenterAddress;
    }

    public String vetCenterPhone() {
        return vetCenterPhone;
    }
}
