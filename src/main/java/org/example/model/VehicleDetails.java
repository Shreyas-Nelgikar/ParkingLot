package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class VehicleDetails extends BaseModel {

    private String vehicleNumber;
    private VehicleType vehicleType;

}
