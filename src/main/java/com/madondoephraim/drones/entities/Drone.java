package com.madondoephraim.drones.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @NotNull(message = "Drone serial umber must not be empty")
    @Size(min=3,max=100,message="Drone serial number must not be greater than {value} characters")
    @JsonProperty(required = true)
    @NotEmpty
    @NotBlank
    private String serialNumber;

    @Column(name="drone_model")
    @Enumerated(value = EnumType.STRING)
    @JsonProperty(required = true)
    @NotNull(message="Drone model must not be empty")
    private Model droneModel;

    @Column(name="maximum_weight")
    @DecimalMax(value = "1000", message =" No Drone can carry more than {value} grams")
    private Double maximum_weight;

    @Column(name="battery_capacity")
    @JsonProperty(required = true)
    @NotNull(message="Battery life must not be empty or null")
    private Integer batteryLife;

    @Column(name = "drone_state")
    @Enumerated(value = EnumType.STRING)
    private State droneState;

    @Column(name = "loaded_weight")
    private Double loadedWeight;
}
