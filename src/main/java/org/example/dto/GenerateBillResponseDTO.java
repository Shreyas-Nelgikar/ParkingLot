package org.example.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.model.Bill;

@Setter
@Getter
public class GenerateBillResponseDTO {

    private Bill bill;
    private String status;
    private String errorMessage;

}
