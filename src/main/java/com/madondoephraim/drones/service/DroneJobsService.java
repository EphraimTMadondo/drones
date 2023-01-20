package com.madondoephraim.drones.service;

import com.madondoephraim.drones.commons.DroneActivity;
import com.madondoephraim.drones.commons.GenericReponse;
import com.madondoephraim.drones.commons.LoadDrone;

public interface DroneJobsService {
    GenericReponse loadDrone(LoadDrone job);

    DroneActivity getDroneJob(String serialNumber);

    DroneActivity getAll();
}
