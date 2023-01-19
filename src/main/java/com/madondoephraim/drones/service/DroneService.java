package com.madondoephraim.drones.service;

import com.madondoephraim.drones.entities.Drone;

import java.util.List;

public interface DroneService {
    Drone createDrone(Drone droneDto);
    Drone getDrone(String serialNumber);
    List<Drone> getAvailable();
    List<Drone> getAll();
}
