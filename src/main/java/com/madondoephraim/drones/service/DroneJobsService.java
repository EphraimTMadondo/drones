package com.madondoephraim.drones.service;

import com.madondoephraim.drones.commons.DroneActivity;
import com.madondoephraim.drones.commons.GenericReponse;
import com.madondoephraim.drones.entities.DroneJob;

public interface DroneJobsService {
    GenericReponse loadDrone(DroneJob job);

    DroneActivity getDroneJob(String serialNumber);
}
