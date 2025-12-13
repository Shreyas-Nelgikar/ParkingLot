package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ParkingSlot {

    private Long slotNumber;
    private Status slotStatus;
    private VehicleType allowedVehicleType;

}
