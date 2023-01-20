package com.madondoephraim.drones.api;

import com.madondoephraim.drones.commons.DroneBattery;
import com.madondoephraim.drones.commons.DronesSummary;
import com.madondoephraim.drones.entities.Drone;
import com.madondoephraim.drones.service.DroneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 *
 * @author Ephraim Madodno
 * Drones Rest API Controller - /api/drones
 *
 */
@RestController
@RequestMapping(path = "/api/drones")
public class DronesController {

    private final DroneService droneService;
    @Autowired
    public DronesController(DroneService droneService) {
        this.droneService = droneService;
    }

    @GetMapping(path = "/check-battery/{serialNumber}")
    @Operation(summary = "Get a drone with its battery life using serial number")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found the drone",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = DroneBattery.class))}),
            @ApiResponse(responseCode = "404", description = "Drone not found", content = @Content)})
    public ResponseEntity<DroneBattery> getDroneBattery(@Valid @PathVariable("serialNumber")String serialNumber){
        Drone drone = droneService.getDrone(serialNumber);
        if(drone != null) {
            return ResponseEntity.ok(new DroneBattery(drone));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    @Operation(summary = "Get a summary of drones")
    @ApiResponse(responseCode = "201", description = "Drones found",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = DronesSummary.class))})
    public ResponseEntity<DronesSummary> findAll() {
        List<Drone> drones =  droneService.getAll();
        if(!drones.isEmpty()) {
            DronesSummary dronesSummary = DronesSummary.builder()
                    .total(drones.size())
                    .drones(drones).build();
            return ResponseEntity.ok(dronesSummary);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/available")
    @Operation(summary = "Get a list of drones available for loading")
    @ApiResponse(responseCode = "201", description = "Drones found",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = DronesSummary.class))})
    public ResponseEntity<DronesSummary> findAvailable() {
        List<Drone> drones =  droneService.getAvailable();
        if(!drones.isEmpty()) {
            DronesSummary dronesSummary = DronesSummary.builder()
                    .total(drones.size())
                    .drones(drones).build();
            return ResponseEntity.ok(dronesSummary);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value="/register",consumes=APPLICATION_JSON_VALUE)
    @Operation(summary = "Register a new drone")
    @ApiResponse(responseCode = "201", description = "New drone created",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Drone.class))})
    public ResponseEntity<Drone> registerDrone(@Valid @RequestBody Drone drone){
        Drone result = droneService.createDrone(drone);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
