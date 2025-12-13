package org.example.service;

import lombok.AllArgsConstructor;
import org.example.exception.InvalidVehicleDetailsExcepton;
import org.example.model.*;
import org.example.respository.UserToVehicleRepository;
import org.example.respository.VehicleDetailsRepository;

import java.util.Optional;

@AllArgsConstructor
public class VehicleService {
    
    private VehicleDetailsRepository vehicleDetailsRepository;
    private UserToVehicleRepository userToVehicleRepository;
    
    public void validateVehicle(String vehicleNumber, VehicleType vehicleType) throws InvalidVehicleDetailsExcepton {
        if (vehicleNumber == null || vehicleNumber.isEmpty()) {
            throw new InvalidVehicleDetailsExcepton("Vehicle number cannot be null or empty");
        }
        if (vehicleType == null || vehicleType.name().isEmpty()) {
            throw new InvalidVehicleDetailsExcepton("Vehicle type cannot be empty");
        }
    }
    
    public VehicleDetails fetchVehicleDetails(String vehicleNumber, VehicleType vehicleType) {
        Optional<VehicleDetails> vehicleDetailsOptional = vehicleDetailsRepository.checkIfVehicleExists(vehicleNumber);
        if (vehicleDetailsOptional.isEmpty()) {
            VehicleDetails vehicleDetails = new VehicleDetails(vehicleNumber, vehicleType);
            return vehicleDetailsRepository.saveVehicleDetails(vehicleDetails);
        }
        return vehicleDetailsOptional.get();
    }
    
    public Vehicle fetchVehicle(User user, VehicleDetails vehicleDetails) {
        userToVehicleRepository.saveUserVehicle(user.getId(), vehicleDetails.getId());
        return new Vehicle(user, vehicleDetails);
    }
}