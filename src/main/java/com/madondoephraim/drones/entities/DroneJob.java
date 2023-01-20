package com.madondoephraim.drones.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotNull(message = "Drone serial umber must not be empty")
    @Size(min=3,max=100,message="Drone serial number must not be greater than {value} characters")
    @JsonProperty(required = true)
    @NotEmpty
    @NotBlank
    private String serialNumber;

    @Column(name="activity_state")
    @JsonProperty(required = false)
    @Enumerated(value = EnumType.STRING)
    private State state;

    @Column(name="loaded_packages")
    @OneToMany
    @JsonProperty(required = true)
    @NotEmpty(message="Add medications before loading to drone")
    private List<Medication> packages;

    @Column(name="quantity_loaded")
    @JsonProperty(required = true)
    @NotNull(message="Quantity must not be empty")
    private Integer quantity;

    @Column(name="max_weight_loaded")
    @JsonProperty(required = true)
    @NotNull(message="Total weight must not be empty")
    private Double weightLoaded;

    @Column(name="destinationm_address",columnDefinition="TEXT")
    private String address;

    public DroneJob(Drone drone) {
        this.serialNumber = drone.getSerialNumber();
    }
}
