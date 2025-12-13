package org.example.respository;

import org.example.model.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GateRepository {
    
    private Map<Long, Gate> gateMap = new HashMap<>();
    
    public GateRepository() {
        initializeDummyData();
    }
    
    private void initializeDummyData() {
        User user1 = new User("Bot1", 12345L, "xyz");
        Operator operator1 = new Operator(user1, 111L);
        Gate gate1 = new Gate(1L, GateType.ENTRY, GateStatus.OPEN, operator1);
        gate1.setId(1L);
        gateMap.put(gate1.getId(), gate1);

        User user2 = new User("Bot2", 12345L, "xyz");
        Operator operator2 = new Operator(user2, 112L);
        Gate gate2 = new Gate(2L, GateType.EXIT, GateStatus.OPEN, operator2);
        gate2.setId(2L);
        gateMap.put(gate2.getId(), gate2);
    }

    public Optional<Gate> checkIfGateExists(Long gateNumber) {
        if (gateMap.containsKey(gateNumber)) {
            return  Optional.of(gateMap.get(gateNumber));
        }
        return Optional.empty();
    }
}