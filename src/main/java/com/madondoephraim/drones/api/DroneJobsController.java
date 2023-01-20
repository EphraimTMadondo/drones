package com.madondoephraim.drones.api;

import com.madondoephraim.drones.commons.DroneActivity;
import com.madondoephraim.drones.commons.GenericReponse;
import com.madondoephraim.drones.entities.DroneJob;
import com.madondoephraim.drones.service.DroneJobsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 *
 * @author Ephraim Madodno
 * Drone Jobs Rest API Controller - /drones/activity
 *
 */

@RestController
@RequestMapping(path = {"/api/drones/activity"}, produces = APPLICATION_JSON_VALUE)
@Tag(name = "Jobs")
public class DroneJobsController {

    @Autowired
    private DroneJobsService jobService;


    @PostMapping(value="/load",consumes=APPLICATION_JSON_VALUE)
    @Operation(summary = "Load drone with medication")
    @ApiResponse(responseCode = "201", description = "Drone loaded",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = GenericReponse.class))})
    public ResponseEntity<GenericReponse> loadDrone(@Valid @RequestBody DroneJob dto){
        GenericReponse res = jobService.loadDrone(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @GetMapping("/check/{serialNumber}")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found the drone activity",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = DroneActivity.class))}),
            @ApiResponse(responseCode = "404", description = "Drone not found", content = @Content)})
    public ResponseEntity<DroneActivity> findAvailableDroneJob(@Valid @PathVariable("serialNumber")String serialNumber){
        DroneActivity droneActivity =  jobService.getDroneJob(serialNumber);
        if(droneActivity != null) {
            return ResponseEntity.ok(droneActivity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
