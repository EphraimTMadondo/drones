package com.madondoephraim.drones.persistence;

import com.madondoephraim.drones.entities.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationRepository extends JpaRepository<Medication,Long> {
	List<Medication> findAll();
}
