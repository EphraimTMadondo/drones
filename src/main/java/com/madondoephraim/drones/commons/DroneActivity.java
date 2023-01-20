package com.madondoephraim.drones.commons;

import com.madondoephraim.drones.entities.DroneJob;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DroneActivity {
    private long total;
    private List<DroneJob> jobs;
}
