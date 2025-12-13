package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.model.VehicleType;

@AllArgsConstructor
@Getter
public class IssueTokenRequestDTO {

    private String username;
    private Long phoneNumber;
    private String address;
    private String vehicleNumber;
    private VehicleType vehicleType;
    private Long gateNumber;

}
