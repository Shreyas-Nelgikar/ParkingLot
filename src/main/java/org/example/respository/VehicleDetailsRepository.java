package org.example.respository;

import org.example.model.VehicleDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class VehicleDetailsRepository {

    private Long vehicleID = 0L;
    private Map<String, VehicleDetails> vehicleDetailsMap = new HashMap<>();

    public Optional<VehicleDetails> checkIfVehicleExists(String vehicleNumber) {
        if (vehicleDetailsMap.containsKey(vehicleNumber)) {
            return Optional.of(vehicleDetailsMap.get(vehicleNumber));
        }

        return Optional.empty();
    }

    public VehicleDetails saveVehicleDetails(VehicleDetails vehicleDetails) {
        vehicleDetails.setId(vehicleID++);
        vehicleDetails.setCreatedAt(new Date());
        vehicleDetails.setModifiedAt(new Date());
        vehicleDetailsMap.put(vehicleDetails.getVehicleNumber(), vehicleDetails);
        return vehicleDetailsMap.get(vehicleDetails.getVehicleNumber());
    }
}
