package com.madondoephraim.drones.service;

import com.madondoephraim.drones.entities.Drone;
import com.madondoephraim.drones.entities.State;
import com.madondoephraim.drones.persistence.DronesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DroneServiceImpl implements DroneService {
    private DronesRepository dronesRepository;
    @Autowired
    public DroneServiceImpl(DronesRepository dronesRepository) {
        this.dronesRepository = dronesRepository;
    }

    @Override
    public Drone createDrone(Drone drone) {
        return dronesRepository.save(drone);
    }

    @Override
    public Drone getDrone(String serialNumber) {
        return dronesRepository.findBySerialNumber(serialNumber);
    }

    @Override
    public List<Drone> getAll() {
        return dronesRepository.findAll();
    }

    @Override
    public List<Drone> getAvailable() {
        return dronesRepository.findByDroneState(State.IDLE);
    }
}
