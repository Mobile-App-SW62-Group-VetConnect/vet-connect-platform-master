package com.org.vetconnect.platform.vetservices.domain.model.aggregates;

import com.org.vetconnect.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.org.vetconnect.platform.vetservices.domain.model.commands.CreateVetServiceCommand;
import com.org.vetconnect.platform.vetservices.domain.model.valueobjects.ServiceCategory;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class VetService extends AuditableAbstractAggregateRoot<VetService> {
    /*
    *   id                  int
    *   idvet               int //Consultar como se haria la relacion con la tabla de veterinarios
    *   serviceName         string
    *   serviceDescription  string
    *   servicePrice        double
    *   serviceDuration     int
    *   serviceCategory     enum
    *   features []         list
    *   isActive            bool
    *
    * */


    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(max = 255)
    private String description;

    private Double price;

    private Integer duration;

    private ServiceCategory category;

    private List<String> features;

    private Boolean isActive;

    public VetService(){
        this.name = "";
        this.description = "";
        this.price = 0.0;
        this.duration = 0;
        this.category = ServiceCategory.Cirugia;
        this.features = List.of();
        this.isActive = false;
    }

    public VetService(String name, String description, Double price, Integer duration, ServiceCategory category, List<String> features, Boolean isActive){
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.category = category;
        this.features = features;
        this.isActive = isActive;
    }

    public VetService(CreateVetServiceCommand command){
        this.name = command.name();
        this.description = command.description();
        this.price = command.price();
        this.duration = command.duration();
        this.category = command.category();
        this.features = command.features();
        this.isActive = command.isActive();
    }

    /**
     * Updates the course information.
     * @param name The new title.
     * @param description The new description.
     * @param price The new price.
     * @param duration The new duration.
     * @param isActive The new status.
     * @return The updated course.
     */
    public VetService updateInformation(String name, String description, Double price, Integer duration, Boolean isActive){
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.isActive = isActive;
        return this;
    }

}
