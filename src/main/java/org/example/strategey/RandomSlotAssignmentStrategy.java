package org.example.strategey;

import org.example.exception.InvalidParkingLotException;
import org.example.model.*;

import java.util.List;
import java.util.stream.Collectors;

public class RandomSlotAssignmentStrategy implements SlotAssignmentStrategy {

    @Override
    public void assignSlot(ParkingLot parkingLot, Token token) throws InvalidParkingLotException {
        if (!Status.EMPTY.name().equals(parkingLot.getLotStatus().name())) {
            throw new InvalidParkingLotException("Parking Lot is not empty");
        }

        parkingLot.getParkingFloorList().stream()
                .filter(parkingFloor -> Status.EMPTY.name().equals(parkingFloor.getFloorStatus().name()))
                .flatMap(parkingFloor -> parkingFloor.getParkingSlotList().stream()
                        .filter(parkingSlot -> Status.EMPTY.name().equals(parkingSlot.getSlotStatus().name())
                                && parkingSlot.getAllowedVehicleType().name().equals(
                                        token.getVehicle().getVehicleDetails().getVehicleType().name()
                                ))
                        .map(parkingSlot -> new Object() {
                            ParkingFloor floor = parkingFloor;
                            ParkingSlot slot = parkingSlot;
                        })
                ).findFirst()
                .ifPresentOrElse(pair -> {
                    token.setAssignedSlotNumber(pair.slot.getSlotNumber());
                    token.setAssignedFloorNumber(pair.floor.getFloorNumber());
                    pair.slot.setSlotStatus(Status.OCCUPIED);
                }, () -> {
                    throw new RuntimeException(new InvalidParkingLotException("No empty slots available"));
                });
    }
}
