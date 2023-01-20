package com.madondoephraim.drones.service;

import com.madondoephraim.drones.commons.EventLogSummary;
import com.madondoephraim.drones.commons.GenericReponse;
import com.madondoephraim.drones.entities.DroneAudit;
import com.madondoephraim.drones.persistence.DroneAuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class EventLogServiceImpl implements EventLogService {
    @Value("${history.events.logs.base-directory}")
    private String base_directory;
    DroneAuditRepository droneAuditRepository;
    @Autowired
    public EventLogServiceImpl(DroneAuditRepository droneAuditRepository) {
        this.droneAuditRepository = droneAuditRepository;
    }

    public ResponseEntity<Object> downloadFile(long log) throws IOException {
        Optional<DroneAudit> eventlog = droneAuditRepository.findById(log);
        if (!eventlog.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(parseResponse(String.valueOf(log),"ERROR"));
        }
        String filename = eventlog.get().getFilepath();
        File file = new File(base_directory + filename);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity.ok().headers(headers).contentLength(
                file.length()).contentType(MediaType.parseMediaType("application/txt")).body(resource);
    }

    private GenericReponse parseResponse(String res, String status) {
        GenericReponse resp = new GenericReponse();
        resp.setData(res);
        resp.setStatus(status);
        return resp;
    }

    @Override
    public EventLogSummary getAll() {
        List<DroneAudit> logs = droneAuditRepository.findAll();
        if (!logs.isEmpty()) {
            return EventLogSummary.builder()
                    .total(logs.size())
                    .history(logs).build();
        }
        return null;
    }
}
