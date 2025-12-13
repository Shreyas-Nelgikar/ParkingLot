package org.example.respository;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UserToVehicleRepository {

    private Map<Long, Set<Long>> userToVehicleMap = new HashMap<>();

    public void saveUserVehicle(Long userId, Long vehicleId) {
        if (!userToVehicleMap.containsKey(userId)) {
            userToVehicleMap.put(userId, Set.of(vehicleId));
        } else if (userToVehicleMap.containsKey(userId) && !userToVehicleMap.get(userId).contains(vehicleId)) {
            userToVehicleMap.get(userId).add(vehicleId);
        }
    }
}
