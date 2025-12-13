package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@Getter
public class ParkingLot extends BaseModel {

    private Long lotNumber;
    private Long capacity;
    private List<ParkingFloor> parkingFloorList = new ArrayList<>();
    private Status lotStatus;
    private List<VehicleType> allowedVehicleTypeList = new ArrayList<>();
    private List<Gate> gatesList = new ArrayList<>();

}
