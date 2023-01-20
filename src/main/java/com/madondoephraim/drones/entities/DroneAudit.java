package com.madondoephraim.drones.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity(name = "musala_drone_audits")
public class DroneAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="log_file")
    private String filepath;

    private LocalDateTime dateTime;
}
