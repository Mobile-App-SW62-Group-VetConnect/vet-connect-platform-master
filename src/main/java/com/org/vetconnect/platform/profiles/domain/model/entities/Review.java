package com.org.vetconnect.platform.profiles.domain.model.entities;

import com.org.vetconnect.platform.profiles.domain.model.aggregates.PetOwner;
import com.org.vetconnect.platform.profiles.domain.model.aggregates.VetCenter;
import com.org.vetconnect.platform.profiles.domain.model.commands.CreateReviewCommand;
import com.org.vetconnect.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Entity
public class Review extends AuditableModel {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // un vet center puede tener varias reviews
    // ESTO DEBE SER CAMBIADO POR USERID
    @ManyToOne
    @Getter
    @JoinColumn(name="pet_owner_id", nullable = false)
    private PetOwner petOwner;

    // una review solo puede pertenecer a un pet owner
    @ManyToOne
    @Getter
    @JoinColumn(name="vet_center_id", nullable = false)
    private VetCenter vetCenter;

    @Getter
    private String comments;

    @Getter
    @Min(1)
    @Max(5)
    private int rating;


    public Review(){
    }

    public Review(CreateReviewCommand command){
        this.comments = command.comments();
        this.rating = command.rating();
    }

    public Review(PetOwner petOwner, VetCenter vetCenter, String comments, int rating) {
        this.petOwner = petOwner;
        this.vetCenter = vetCenter;
        this.comments = comments;
        this.rating = rating;
    }
}
