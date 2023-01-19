package com.madondoephraim.drones.persistence;

import com.madondoephraim.drones.entities.DroneJob;
import com.madondoephraim.drones.entities.State;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneJobRepository extends CrudRepository<DroneJob,Long> {

    List<DroneJob> findByDroneIdAndStateIn(String droneId, List<State> states);
}