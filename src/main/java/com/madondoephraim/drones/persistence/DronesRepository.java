package com.madondoephraim.drones.persistence;

import com.madondoephraim.drones.entities.Drone;
import com.madondoephraim.drones.entities.State;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 *
 * @author Ephraim Madodno
 * Drones Data Access Repository
 *
 */
public interface DronesRepository extends CrudRepository<Drone,String> {

        Drone findBySerialNumber(String serialNumber);
        List<Drone> findAll();

        List<Drone> findByDroneState(State state);
}
