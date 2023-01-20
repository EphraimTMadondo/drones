package com.madondoephraim.drones.service;

import com.madondoephraim.drones.commons.DronesSummary;
import com.madondoephraim.drones.entities.Drone;

public interface DroneService {
    Drone createDrone(Drone droneDto);
    Drone getDrone(String serialNumber);
    DronesSummary getAvailable();
    DronesSummary getAll();
}
