package com.madondoephraim.drones.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *
 * @author Ephraim Madodno
 * Entity class to record Drone Jobs Information
 *
 */
@Data
@NoArgsConstructor
@Entity(name = "musala_jobs")
public class DroneJob {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @Column(name="drone_serialnumber")
    private String serialNumber;

    @Column(name="loaded_packages")
    @OneToMany
    private List<Medication> packages;

    @Column(name="quantity_loaded")
    private Integer quantity;

    @Column(name="max_weight_loaded")
    private Double weightLoaded;

    @Column(name="destinationm_address",columnDefinition="TEXT")
    private String address;
}
