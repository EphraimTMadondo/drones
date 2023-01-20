package com.madondoephraim.drones.commons;

import com.madondoephraim.drones.entities.Drone;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class DronesSummary {
    private long total;
    private List<Drone> drones;
}
