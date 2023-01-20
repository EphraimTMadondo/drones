package com.madondoephraim.drones.persistence;

import com.madondoephraim.drones.entities.DroneAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneAuditRepository extends JpaRepository<DroneAudit,Long> {
    List<DroneAudit> findAll();
}
