package com.madondoephraim.drones.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 *
 * @author Ephraim Madodno
 * Entity class to record Medication Packages Information
 *
 */
@SuppressWarnings("ALL")
@Data
@Entity(name = "musala_medications")
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonIgnore
    private Long id;

    @Column(name = "medication_name")
    @JsonProperty(required = true)
    @NotEmpty
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9.\\-\\/_ ]*$", message="Only numbers, letters, underscore and dash characters are allowed")
    private String name;

    @Column(name ="medication_weight")
    @Max(value=500, message="Weight too heavy for drone")
    private Double weight;

    @Column(name="medication_code")
    @JsonProperty(required = true)
    @NotEmpty
    @NotBlank
    @Pattern(regexp = "^[A-Z0-9.\\-\\/_ ]*$", message="Only numbers, capital letters, underscore and dash characters are allowed")
    private String code;

    @Column(name ="picture_url")
    private String image;
}
