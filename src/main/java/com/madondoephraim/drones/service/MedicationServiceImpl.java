package com.madondoephraim.drones.service;

import com.madondoephraim.drones.entities.Medication;
import com.madondoephraim.drones.persistence.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicationServiceImpl implements MedicationService{
	private MedicationRepository medRepo;
	@Autowired
	public MedicationServiceImpl(MedicationRepository medRepo) {
		this.medRepo = medRepo;
	}

	@Override
	public List<Medication> createMedication(List<Medication> medications) {
		List<Medication> result = new ArrayList<>();
		medRepo.saveAll(medications).iterator().forEachRemaining(result::add);
		return result;

	}

}
