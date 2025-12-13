package org.example.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.model.Token;

@Setter
@Getter
public class IssueTokenResponseDTO {

    private Token token;
    private String errorMessage;
    private String status;

}
