package com.madondoephraim.drones.service;

import com.madondoephraim.drones.commons.DroneActivity;
import com.madondoephraim.drones.commons.GenericReponse;
import com.madondoephraim.drones.entities.Drone;
import com.madondoephraim.drones.entities.DroneJob;
import com.madondoephraim.drones.entities.Medication;
import com.madondoephraim.drones.entities.State;
import com.madondoephraim.drones.persistence.DroneJobRepository;
import com.madondoephraim.drones.persistence.DronesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DroneJobsServiceImpl implements DroneJobsService {
    private static final String ERROR = "error";
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
    public GenericReponse loadDrone(DroneJob droneJob) {
        Drone drone = dronesRepo.findBySerialNumber(droneJob.getSerialNumber());

        if(drone == null)
            return parseResponse("Drone does not exist. Check the serial number",ERROR);

        List<Medication> meds = medService.createMedication(droneJob.getPackages());


        DroneJob activity = new DroneJob(drone);

        //Perform checks
        if(drone.getDroneState() != State.LOADING)
            return parseResponse("Drone is fully loaded and no longer available, please try another drone",ERROR);

        if(activity.getWeightLoaded()>drone.getMaximum_weight())
            return parseResponse("The weight loaded is heavier than the drone capacity",ERROR);

        double availableWeight = drone.getLoadedWeight() - drone.getMaximum_weight();

        if(activity.getWeightLoaded()>availableWeight)
            return parseResponse("Weight loaded is greater than available space",ERROR);

        double totalWeight = drone.getMaximum_weight() + activity.getWeightLoaded();

        //Load drone and update status

        drone.setLoadedWeight(totalWeight);
        if(drone.getMaximum_weight() == totalWeight) {
            drone.setDroneState(State.LOADED);
        }
        activity.setState(drone.getDroneState());
        activity.setPackages(meds);
        jobsRepo.save(activity);
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
                .findBySerialNumberAndStateIn(serialnumber, Arrays.asList(State.LOADING, State.LOADED));
        if (!jobs.isEmpty()) {
            return DroneActivity.builder()
                    .total(jobs.size())
                    .jobs(jobs).build();
        }
        return null;
    }
}
