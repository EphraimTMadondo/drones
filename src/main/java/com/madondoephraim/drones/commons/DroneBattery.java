package com.madondoephraim.drones.commons;

import com.madondoephraim.drones.entities.Drone;
import com.madondoephraim.drones.entities.Model;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DroneBattery {
    private String serialNumber;
    private Model model;
    private int batteryLife;
    public DroneBattery(Drone d) {
        this.serialNumber = d.getSerialNumber();
        this.model = d.getDroneModel();
        this.batteryLife = d.getBatteryLife();
    }

}
