package org.example.strategey;

import org.example.exception.InvalidParkingLotException;
import org.example.model.ParkingLot;
import org.example.model.Token;

public interface SlotAssignmentStrategy {

    void assignSlot(ParkingLot parkingLot, Token token) throws InvalidParkingLotException;

}
