package com.madondoephraim.drones.commons;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.madondoephraim.drones.entities.Medication;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class LoadDrone {
    @NotNull(message = "Drone serial umber must not be empty")
    @Size(min=3,max=100,message="Drone serial number must not be greater than {value} characters")
    @JsonProperty(required = true)
    @NotEmpty
    @NotBlank
    private String serialNumber;

    @JsonProperty(required = true)
    @NotNull(message="Quantity must not be empty")
    private Integer quantity;

    @JsonProperty(required = true)
    @NotNull(message="Total weight must not be empty")
    private Double totalWeight;

    @JsonProperty(required = true)
    private String deliveryAddress;

    @JsonProperty(required = true)
    @NotEmpty(message="Add medications before loading to drone")
    private List<Medication> medications;

}
