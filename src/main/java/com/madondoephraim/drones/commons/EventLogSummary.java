package com.madondoephraim.drones.commons;

import com.madondoephraim.drones.entities.DroneAudit;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EventLogSummary {
    private long total;
    private List<DroneAudit> history;
}
