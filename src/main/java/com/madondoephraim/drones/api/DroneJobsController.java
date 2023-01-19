package com.madondoephraim.drones.api;

import com.madondoephraim.drones.commons.GenericReponse;
import com.madondoephraim.drones.entities.DroneJob;
import com.madondoephraim.drones.service.DroneJobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 *
 * @author Ephraim Madodno
 * Drone Jobs Rest API Controller - /drones/activity
 *
 */

@RestController
@RequestMapping(path = {"/api/drones/activity"}, produces = APPLICATION_JSON_VALUE)
public class DroneJobsController {

    @Autowired
    private DroneJobsService jobService;


    @PostMapping(value="/load",consumes=APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericReponse> loadDrone(@RequestBody DroneJob dto){
        GenericReponse res = jobService.loadDrone(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }



    @GetMapping("/check/{serialNumber}")
    public List<DroneJob> findAvailableDroneJob(@PathVariable("serialNumber")String serialNumber){
        return jobService.getDroneJob(serialNumber);
    }




}
