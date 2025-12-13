package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Vehicle extends BaseModel {

    private User user;
    private VehicleDetails vehicleDetails;

}
