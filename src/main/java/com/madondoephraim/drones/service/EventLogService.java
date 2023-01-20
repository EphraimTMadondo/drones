package com.madondoephraim.drones.service;

import com.madondoephraim.drones.commons.EventLogSummary;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface EventLogService {
    ResponseEntity<Object> downloadFile(long log) throws IOException;

    EventLogSummary getAll();
}
