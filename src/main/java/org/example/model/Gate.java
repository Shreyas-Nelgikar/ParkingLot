package org.example.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Gate extends BaseModel {

    private Long gateNumber;
    private GateType gateType;
    private GateStatus gateStatus;
    private Operator operator;

}
