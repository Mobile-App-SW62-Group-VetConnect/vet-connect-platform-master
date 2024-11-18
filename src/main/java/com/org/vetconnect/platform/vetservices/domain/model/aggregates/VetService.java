package com.org.vetconnect.platform.vetservices.domain.model.aggregates;

import com.org.vetconnect.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.org.vetconnect.platform.vetservices.domain.model.valueobjects.ServiceCategory;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

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

    @NotBlank
    private Double price;

    @NotBlank
    private Integer duration;

    @NotBlank
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
