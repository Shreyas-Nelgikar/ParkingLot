package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class ParkingFloor extends BaseModel {

    private Long floorNumber;
    private List<ParkingSlot> parkingSlotList = new ArrayList<>();
    private Status floorStatus;
    private List<VehicleType> allowedVehicleTypeList = new ArrayList<>();

}
