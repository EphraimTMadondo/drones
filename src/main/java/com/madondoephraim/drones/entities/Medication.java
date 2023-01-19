package com.madondoephraim.drones.entities;

import jakarta.persistence.*;
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
    private Long id;

    @Column(name = "medication_name")
    private String name;

    @Column(name ="medication_weight")
    private Double weight;

    @Column(name="medication_code")
    private String code;

    @Column(name ="picture_url")
    private String image;
}
