package com.madondoephraim.drones.data.response;

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
    public static DroneBattery from(Drone d) {
        return new DroneBattery(d.getSerialNumber(), d.getDroneModel(), d.getBatteryLife());
    }

}
