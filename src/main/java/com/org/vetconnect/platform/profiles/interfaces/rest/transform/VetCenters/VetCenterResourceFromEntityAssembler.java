package com.org.vetconnect.platform.profiles.interfaces.rest.transform.VetCenters;

import com.org.vetconnect.platform.profiles.domain.model.aggregates.VetCenter;
import com.org.vetconnect.platform.profiles.domain.model.valueobjects.VetCenterEmail;
import com.org.vetconnect.platform.profiles.domain.model.valueobjects.VetCenterPhone;
import com.org.vetconnect.platform.profiles.interfaces.rest.resources.VetCenters.VetCenterResource;

import java.util.List;

public class VetCenterResourceFromEntityAssembler {

    public static VetCenterResource toResourceFromEntity(VetCenter vetCenter) {

        VetCenterResource.Contact contact = new VetCenterResource.Contact(
                vetCenter.getPhone(),
                vetCenter.getEmail()
        );

        return new VetCenterResource(
                vetCenter.getId(),
                vetCenter.getName(),
                vetCenter.getAddress(),
                vetCenter.getVetCenterImageProfile(),
                vetCenter.getVetCenterDescription(),
                vetCenter.getBusinessHours(),
                contact
        );
    }

}
