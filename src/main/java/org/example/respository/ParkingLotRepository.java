package org.example.respository;

import org.example.model.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ParkingLotRepository {

    private Map<Long, ParkingLot> parkingLotMap = new HashMap<>();

    public  ParkingLotRepository() {
        initializeDummyData();
    }

    private void initializeDummyData() {
        ParkingSlot parkingSlot1 = new ParkingSlot(1L, Status.EMPTY, VehicleType.TWO_WHEELER);
        ParkingSlot parkingSlot2 = new ParkingSlot(2L, Status.EMPTY, VehicleType.FOUR_WHEELER);
        ParkingFloor parkingFloor1 = new ParkingFloor(1L, Arrays.asList(parkingSlot1, parkingSlot2), Status.EMPTY,
                Arrays.asList(VehicleType.TWO_WHEELER, VehicleType.FOUR_WHEELER));

        User user1 = new User("Bot1", 12345L, "xyz");
        Operator operator1 = new Operator(user1, 111L);
        Gate gate1 = new Gate(1L, GateType.ENTRY, GateStatus.OPEN, operator1);
        gate1.setId(1L);

        User user2 = new User("Bot2", 12345L, "xyz");
        Operator operator2 = new Operator(user2, 112L);
        Gate gate2 = new Gate(2L, GateType.EXIT, GateStatus.OPEN, operator2);
        gate2.setId(2L);

        ParkingLot parkingLot1 = new ParkingLot(1L, 20L, Arrays.asList(parkingFloor1), Status.EMPTY,
                Arrays.asList(VehicleType.TWO_WHEELER, VehicleType.FOUR_WHEELER), Arrays.asList(gate1, gate2));
        parkingLotMap.put(parkingLot1.getLotNumber(), parkingLot1);
    }

    public Optional<ParkingLot> checkIfParkingLotExists(Long assignedParkingLot) {
        if (parkingLotMap.containsKey(assignedParkingLot)) {
            return  Optional.of(parkingLotMap.get(assignedParkingLot));
        }
        return  Optional.empty();
    }
}
