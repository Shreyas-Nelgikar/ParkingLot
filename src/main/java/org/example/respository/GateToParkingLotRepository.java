package org.example.respository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GateToParkingLotRepository {

    private Map<Long, Long> gateToParkingLotMap = new HashMap<>();

    public GateToParkingLotRepository() {
        initializeDummyData();
    }

    private void initializeDummyData() {
        gateToParkingLotMap.put(1L, 1L);
        gateToParkingLotMap.put(2L, 1L);
    }

    public Optional<Long> checkIfParkingSlotExists(Long gateId) {
        if (gateToParkingLotMap.containsKey(gateId)) {
            return Optional.of(gateToParkingLotMap.get(gateId));
        }
        return Optional.empty();
    }
}
