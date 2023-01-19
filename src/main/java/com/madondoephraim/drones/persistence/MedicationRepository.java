package com.madondoephraim.drones.persistence;

import com.madondoephraim.drones.entities.Medication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationRepository extends CrudRepository<Medication,Long> {
	List<Medication> findAll();
}
