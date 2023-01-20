package com.madondoephraim.drones.persistence;

import com.madondoephraim.drones.entities.DroneJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneJobRepository extends JpaRepository<DroneJob,Long> {
    List<DroneJob> findBySerialNumber(String droneId);
}