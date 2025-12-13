package org.example.service;

import lombok.AllArgsConstructor;
import org.example.exception.InvalidGateDetailsException;
import org.example.model.Gate;
import org.example.respository.GateRepository;
import org.example.respository.GateToParkingLotRepository;

import java.util.Optional;

@AllArgsConstructor
public class GateService {
    
    private GateRepository gateRepository;
    private GateToParkingLotRepository gateToParkingLotRepository;
    
    public void validateGate(Long gateNumber) throws InvalidGateDetailsException {
        if (gateNumber == null) {
            throw new InvalidGateDetailsException("Gate number must be positive");
        }
    }
    
    public Gate fetchGateDetails(Long gateNumber) throws InvalidGateDetailsException {
        Optional<Gate> gateOptional = gateRepository.checkIfGateExists(gateNumber);
        if (gateOptional.isEmpty()) {
            throw new InvalidGateDetailsException("Gate does not exist");
        }
        return gateOptional.get();
    }
    
    public Long fetchParkingSlotByGate(Long id) throws InvalidGateDetailsException {
        Optional<Long> parkingSlotOptional = gateToParkingLotRepository.checkIfParkingSlotExists(id);
        if (parkingSlotOptional.isEmpty()) {
            throw new InvalidGateDetailsException("Gate is not mapped to any parking lot");
        }
        return parkingSlotOptional.get();
    }
}