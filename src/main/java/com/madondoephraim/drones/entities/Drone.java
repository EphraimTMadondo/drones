package com.madondoephraim.drones.entities;

import jakarta.persistence.*;
import lombok.Data;

/**
 *
 * @author Ephraim Madodno
 * Entity class to record Drone Information
 *
 */
@Data
@Entity(name = "musala_drones")
public class Drone {
    @Id
    @Column(name="serial_number", length = 100)
    private String serialNumber;

    @Column(name="drone_model")
    @Enumerated(value = EnumType.STRING)
    private Model droneModel;

    @Column(name="maximum_weight")
    private Double maximum_weight;

    @Column(name="battery_capacity")
    private Integer batteryLife;

    @Column(name = "drone_state")
    @Enumerated(value = EnumType.STRING)
    private State droneState;

    @Column(name = "loaded_weight")
    private Double loadedWeight;
}
