package org.example.contoller;

import lombok.AllArgsConstructor;
import org.example.dto.IssueTokenRequestDTO;
import org.example.dto.IssueTokenResponseDTO;
import org.example.exception.InvalidGateDetailsException;
import org.example.exception.InvalidParkingLotException;
import org.example.exception.InvalidUserDetailsException;
import org.example.exception.InvalidVehicleDetailsExcepton;
import org.example.model.Token;
import org.example.service.TokenService;

@AllArgsConstructor
public class TokenController {

    TokenService tokenService;

    public IssueTokenResponseDTO issueToken(IssueTokenRequestDTO issueTokenRequestDTO) {
        IssueTokenResponseDTO issueTokenResponseDTO = new IssueTokenResponseDTO();

        try {
            Token token = tokenService.issueToken(
                    issueTokenRequestDTO.getUsername(),
                    issueTokenRequestDTO.getPhoneNumber(),
                    issueTokenRequestDTO.getAddress(),
                    issueTokenRequestDTO.getVehicleNumber(),
                    issueTokenRequestDTO.getVehicleType(),
                    issueTokenRequestDTO.getGateNumber()
            );
            issueTokenResponseDTO.setToken(token);
            issueTokenResponseDTO.setStatus("SUCCESS");
        } catch (Exception | InvalidUserDetailsException | InvalidVehicleDetailsExcepton | InvalidGateDetailsException |
                 InvalidParkingLotException e) {
            issueTokenResponseDTO.setErrorMessage(e.getMessage());
            issueTokenResponseDTO.setStatus("FAILURE");
        }

        return issueTokenResponseDTO;
    }

    public void printToken(Token token) {
        tokenService.printToken(token);
    }

}
