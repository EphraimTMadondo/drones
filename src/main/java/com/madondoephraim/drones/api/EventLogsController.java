package com.madondoephraim.drones.api;

import com.madondoephraim.drones.commons.EventLogSummary;
import com.madondoephraim.drones.service.EventLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = {"/api/drones/logs"})
@Tag(name = "History/Event Logs")
public class EventLogsController {
    EventLogService eventLogService;
    @Autowired
    public EventLogsController(EventLogService eventLogService) {
        this.eventLogService = eventLogService;
    }
    @GetMapping
    @Operation(summary = "Get a summary of history/event logs")
    @ApiResponse(responseCode = "201", description = "EventLogSummary found",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = EventLogSummary.class))})
    public ResponseEntity<EventLogSummary> findAll() {
        EventLogSummary eventLogSummary =  eventLogService.getAll();
        if(eventLogSummary != null) {
            return ResponseEntity.ok(eventLogSummary);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping(value = "/download/{log}")
    @Operation(summary = "Download history/event logs")
    public ResponseEntity<Object> downloadFile(@PathVariable long log) throws IOException {
        return eventLogService.downloadFile(log);
    }
}
