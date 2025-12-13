package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Bill extends BaseModel {

    private Long billId;
    private Date exitTime;
    private Date billGenerationTime;
    private Gate gate;
    private Long tokenNumber;
    private Payment payment;

}
