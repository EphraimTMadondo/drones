package com.madondoephraim.drones.service;

import com.madondoephraim.drones.commons.GenericReponse;
import com.madondoephraim.drones.entities.DroneJob;

import java.util.List;

public interface DroneJobsService {
    GenericReponse loadDrone(DroneJob job);

    List<DroneJob> getDroneJob(String serialNumber);
}
