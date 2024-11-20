package com.org.vetconnect.platform.favorites.domain.model.aggregates;

import com.org.vetconnect.platform.profiles.domain.model.aggregates.PetOwner;
import com.org.vetconnect.platform.profiles.domain.model.aggregates.VetCenter;
import com.org.vetconnect.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Favorite extends AuditableAbstractAggregateRoot<Favorite> {


    @ManyToOne
    @JoinColumn(name = "pet_owner_id")
    private PetOwner petOwner;

    @ManyToOne
    @JoinColumn(name = "vet_center_id")
    private VetCenter vetCenter;

    public Favorite() {
    }

    public Favorite(PetOwner petOwner, VetCenter vetCenter) {
        this.petOwner = petOwner;
        this.vetCenter = vetCenter;
    }

}
