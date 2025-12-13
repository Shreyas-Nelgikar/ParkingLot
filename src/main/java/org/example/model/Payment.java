package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Payment extends BaseModel {

    private Long paymentId;
    private PaymentType paymentType;
    private PaymentStatus paymentStatus;
    private Date paymentDate;
    private Double amount;

}