package com.org.vetconnect.platform.vetservices.infrastructure.persistence.jpa.repositories;

import com.org.vetconnect.platform.vetservices.domain.model.aggregates.VetService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VetServiceRepository extends JpaRepository<VetService, Long> {
}
