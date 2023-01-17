package com.madondoephraim.drones.api;

import com.madondoephraim.drones.data.response.DroneBattery;
import com.madondoephraim.drones.entities.Drone;
import com.madondoephraim.drones.service.drones.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 *
 * @author Ephraim Madodno
 * Drones Rest API Controller - /drones
 *
 */
@RestController
@RequestMapping(path = "/api/drones")
public class DronesController {

    private DroneService droneService;
    @Autowired
    public DronesController(DroneService droneService) {
        this.droneService = droneService;
    }

    @GetMapping(path = "/check-battery/{serialNumber}")
    public ResponseEntity<DroneBattery> getDroneBattery(@PathVariable("serialNumber")String serialNumber){

        Drone dto = droneService.getDrone(serialNumber);
        if(dto!=null) {
            return ResponseEntity.ok(DroneBattery.from(dto));
        }
        else
            return ResponseEntity.notFound().build();

    }

    @GetMapping("/all")
    public List<Drone> findAll() {
        return droneService.getAll();
    }

    @GetMapping("/available")
    public List<Drone> findAvailable() {
        return droneService.getAvailable();
    }

    @PostMapping(value="/register",consumes=APPLICATION_JSON_VALUE)
    public ResponseEntity<Drone> registerDrone(Drone drone){
        Drone result = droneService.createDrone(drone);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
