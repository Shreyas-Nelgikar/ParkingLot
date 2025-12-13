package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Token extends BaseModel {

    private Long tokenNumber;
    private Long assignedLotNumber;
    private Long assignedFloorNumber;
    private Long assignedSlotNumber;
    private Vehicle vehicle;
    private Gate gate;
    private Date entryTime;

}
