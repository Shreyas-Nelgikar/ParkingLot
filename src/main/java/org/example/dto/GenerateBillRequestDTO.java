package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.model.PaymentType;

@AllArgsConstructor
@Getter
public class GenerateBillRequestDTO {

    private Long gateNumber;
    private Long tokenNumber;
    private PaymentType paymentType;

}
