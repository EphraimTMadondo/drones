package com.madondoephraim.drones.service;

import com.madondoephraim.drones.commons.DroneActivity;
import com.madondoephraim.drones.commons.GenericReponse;
import com.madondoephraim.drones.commons.LoadDrone;
import com.madondoephraim.drones.entities.Drone;
import com.madondoephraim.drones.entities.DroneJob;
import com.madondoephraim.drones.entities.Medication;
import com.madondoephraim.drones.entities.State;
import com.madondoephraim.drones.persistence.DroneJobRepository;
import com.madondoephraim.drones.persistence.DronesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class DroneJobsServiceImpl implements DroneJobsService {
    private static final String ERROR = "error";
    @Autowired
    private DroneJobRepository jobsRepo;
    @Autowired
    private DronesRepository dronesRepo;
    @Autowired
    private MedicationService medService;

    @Autowired
    public DroneJobsServiceImpl(MedicationService medService) {
        this.medService = medService;
    }

    @Override
    @Transactional
    public GenericReponse loadDrone(LoadDrone loadDrone) {
        Drone drone = dronesRepo.findBySerialNumber(loadDrone.getSerialNumber());

        if(drone == null)
            return parseResponse("Drone does not exist. Check the serial number",ERROR);

        if(!Arrays.asList(State.LOADING, State.IDLE).contains(drone.getDroneState()))
            return parseResponse("Drone is fully loaded and no longer available, please try another drone",ERROR);

        if(loadDrone.getTotalWeight()>drone.getMaximum_weight())
            return parseResponse("The weight loaded is heavier than the drone capacity",ERROR);

        double availableWeight =  drone.getMaximum_weight() - drone.getLoadedWeight();

        if(loadDrone.getTotalWeight()>availableWeight)
            return parseResponse("Weight loaded is greater than available space",ERROR);

        double totalWeight = drone.getLoadedWeight() + loadDrone.getTotalWeight();

        //Load drone and update status
        List<Medication> meds = medService.createMedication(loadDrone.getMedications());

        DroneJob droneJob = new DroneJob();
        droneJob.setSerialNumber(drone.getSerialNumber());
        droneJob.setWeightLoaded(loadDrone.getTotalWeight());
        droneJob.setAddress(loadDrone.getDeliveryAddress());
        droneJob.setPackages(meds);
        droneJob.setQuantity(loadDrone.getQuantity());

        drone.setLoadedWeight(totalWeight);
        if(drone.getMaximum_weight() == totalWeight) {
            drone.setDroneState(State.LOADED);
        } else {
            drone.setDroneState(State.LOADING);
        }
        jobsRepo.save(droneJob);
        dronesRepo.save(drone);
        return parseResponse(drone.getSerialNumber(),"OK");
    }

    private GenericReponse parseResponse(String res, String status) {
        GenericReponse resp = new GenericReponse();
        resp.setData(res);
        resp.setStatus(status);
        return resp;
    }

    /**
     * This function will load the activities of a given drone using serial number if the drone is in
     * LOADING OR LOADED state.
     */

    @Override
    public DroneActivity getDroneJob(String serialnumber) {
        List<DroneJob> jobs = jobsRepo
                .findBySerialNumber(serialnumber);
        if (!jobs.isEmpty()) {
            return DroneActivity.builder()
                    .total(jobs.size())
                    .jobs(jobs).build();
        }
        return null;
    }

    @Override
    public DroneActivity getAll() {
        List<DroneJob> jobs = jobsRepo
                .findAll();
        if (!jobs.isEmpty()) {
            return DroneActivity.builder()
                    .total(jobs.size())
                    .jobs(jobs).build();
        }
        return null;
    }
}
