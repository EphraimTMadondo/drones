package com.madondoephraim.drones.service.drones;

import com.madondoephraim.drones.entities.Drone;

import java.util.List;

public interface DroneService {
    public Drone createDrone(Drone drone);
    public Drone getDrone(String serialNumber);
    public List<Drone> getAll();
    public List<Drone> getAvailable();
}
