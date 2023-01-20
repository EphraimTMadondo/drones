package com.madondoephraim.drones.history;

import com.madondoephraim.drones.entities.Drone;
import com.madondoephraim.drones.entities.DroneAudit;
import com.madondoephraim.drones.persistence.DroneAuditRepository;
import com.madondoephraim.drones.persistence.DronesRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Ephraim Madodno
 * File Based Event Logs
 *
 */
@Component
@Log4j2
public class EventLogger {
    @Value("${history.events.logs.base-directory}")
    private String base_directory;
    @Value("${history.events.logs.file-format}")
    private String file_format;

    @Value("${history.events.logs.line-format}")
    private String line_format;

    @Autowired
    private DroneAuditRepository droneAuditRepository;
    private DronesRepository dronesRepository;
    @Autowired
    public EventLogger(DronesRepository dronesRepository) {
        this.dronesRepository = dronesRepository;
    }

    @Scheduled(fixedRateString = "${history.events.logs.interval}", initialDelay = 1000 * 60 )
    public void logBatteryLevels() throws IOException {
        LocalDateTime localDateTime = LocalDateTime.now();
        String logfile = String.format(file_format, localDateTime);
        List<Drone> drones = dronesRepository.findAll();
        File basedirectory = new File(base_directory);
        if (!basedirectory.exists()) {
            boolean createddir = basedirectory.mkdir();
            if (!createddir) {
                return;
            }
        }
        File eventlogfile = new File(base_directory + logfile);
        if (!eventlogfile.exists()) {
            boolean createdfile = eventlogfile.createNewFile();
            if (!createdfile) {
                return;
            }
        }
        try (
                BufferedWriter bufferedWriter =
                        new BufferedWriter(new FileWriter(eventlogfile))
                ) {
            long i = 0;
            for (Drone drone : drones) {
                String eventlog = String.format(line_format, i++, drone.getSerialNumber(), drone.getDroneModel(), drone.getDroneState(), drone.getBatteryLife());
                bufferedWriter.write(eventlog);
                bufferedWriter.newLine();
            }
            DroneAudit droneAudit = new DroneAudit();
            droneAudit.setFilepath(logfile);
            droneAudit.setDateTime(localDateTime);
            droneAuditRepository.save(droneAudit);
        }
    }
}
