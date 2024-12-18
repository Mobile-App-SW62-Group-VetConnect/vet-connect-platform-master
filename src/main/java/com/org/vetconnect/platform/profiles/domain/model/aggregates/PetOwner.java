package com.org.vetconnect.platform.profiles.domain.model.aggregates;

import com.org.vetconnect.platform.iam.domain.model.aggregates.User;
import com.org.vetconnect.platform.profiles.domain.model.valueobjects.PetOwnerDNI;
import com.org.vetconnect.platform.profiles.domain.model.valueobjects.PetOwnerEmail;
import com.org.vetconnect.platform.profiles.domain.model.valueobjects.PetOwnerName;
import com.org.vetconnect.platform.profiles.domain.model.valueobjects.PetOwnerPhone;
import com.org.vetconnect.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class PetOwner extends AuditableAbstractAggregateRoot<PetOwner> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @Setter
    private PetOwnerName petOwnerName;

    @Embedded
    @Setter
    private PetOwnerEmail petOwnerEmail;


    @Embedded
    private PetOwnerDNI petOwnerDNI;

    @Embedded
    @Setter
    private PetOwnerPhone petOwnerPhone;

    @Getter
    @Setter
    private String petOwnerPhoto;

    @Setter
    @Getter
    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    public PetOwner(String name, String email, String dni, String phone, String photo){
        this.petOwnerName = new PetOwnerName(name);
        this.petOwnerEmail = new PetOwnerEmail(email);
        this.petOwnerDNI = new PetOwnerDNI(dni);
        this.petOwnerPhone = new PetOwnerPhone(phone);
        this.petOwnerPhoto = photo;
    }

    public PetOwner(){

    }

    public String getName(){
        return this.petOwnerName.getFullName();
    }

    public String getDNI(){
        return this.petOwnerDNI.getDNI();
    }

    public String getEmail(){
        return this.petOwnerEmail.email();
    }

    public String getPhone(){
        return this.petOwnerPhone.getPhone();
    }

    public String getPhoto(){
        return this.petOwnerPhoto;
    }

    // setters para actualizar los valores de los atributos
    public void setName(String name){
        this.petOwnerName = new PetOwnerName(name);
    }

    public void setEmail(String email){
        this.petOwnerEmail = new PetOwnerEmail(email);
    }

    public void setPhone(String phone){
        this.petOwnerPhone = new PetOwnerPhone(phone);
    }

    public void setDNI(String dni){
        this.petOwnerDNI = new PetOwnerDNI(dni);
    }

    public void setPhoto(String photo){
        this.petOwnerPhoto = photo;
    }


    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

}
