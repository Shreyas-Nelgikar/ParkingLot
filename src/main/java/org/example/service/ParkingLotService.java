package org.example.service;

import lombok.AllArgsConstructor;
import org.example.exception.InvalidParkingLotException;
import org.example.exception.InvalidTokenException;
import org.example.model.*;
import org.example.respository.ParkingLotRepository;

import java.util.Optional;

@AllArgsConstructor
public class ParkingLotService {

    private ParkingLotRepository parkingLotRepository;

    public ParkingLot fetchParkingLot(Long assignedParkingLot) throws InvalidParkingLotException {
        Optional<ParkingLot> parkingLotOptional = parkingLotRepository.checkIfParkingLotExists(assignedParkingLot);
        if (parkingLotOptional.isEmpty()) {
            throw new InvalidParkingLotException("Parking lot does not exist");
        }
        return parkingLotOptional.get();
    }

    public void releaseSlot(Token token) throws InvalidParkingLotException {
        ParkingLot parkingLot = fetchParkingLot(token.getAssignedLotNumber());
        ParkingFloor parkingFloor =
                parkingLot.getParkingFloorList().get(Math.toIntExact(token.getAssignedFloorNumber()) - 1);
        ParkingSlot parkingSlot =
                parkingFloor.getParkingSlotList().get(Math.toIntExact(token.getAssignedSlotNumber()) - 1);
        parkingSlot.setSlotStatus(Status.EMPTY);
    }
}
