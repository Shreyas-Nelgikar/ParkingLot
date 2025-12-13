package org.example.service;

import lombok.AllArgsConstructor;
import org.example.exception.*;
import org.example.model.*;
import org.example.respository.*;
import org.example.strategey.SlotAssignmentStrategy;

import java.util.Date;
import java.util.Optional;

@AllArgsConstructor
public class TokenService {

    private UserService userService;
    private VehicleService vehicleService;
    private GateService gateService;
    private ParkingLotService parkingLotService;
    private TokenRepository tokenRepository;
    private SlotAssignmentStrategy slotAssignmentStrategy;

    public Token issueToken(String username, Long phoneNumber, String address,
            String vehicleNumber, VehicleType vehicleType, Long gateNumber) throws InvalidUserDetailsException, InvalidVehicleDetailsExcepton, InvalidGateDetailsException, InvalidParkingLotException {

        Token token = new Token();
        token.setEntryTime(new Date());
        validateInputs(username, phoneNumber, address, vehicleNumber, vehicleType, gateNumber);

        User user = userService.fetchUser(username, phoneNumber, address);
        VehicleDetails vehicleDetails = vehicleService.fetchVehicleDetails(vehicleNumber, vehicleType);
        Vehicle vehicle = vehicleService.fetchVehicle(user, vehicleDetails);
        token.setVehicle(vehicle);

        Gate gate = gateService.fetchGateDetails(gateNumber);
        token.setGate(gate);

        Long assignedParkingLot = gateService.fetchParkingSlotByGate(gate.getId());
        ParkingLot parkingLot = parkingLotService.fetchParkingLot(assignedParkingLot);
        token.setAssignedLotNumber(assignedParkingLot);

        slotAssignmentStrategy.assignSlot(parkingLot, token);

        Token savedToken = saveToken(token);
        savedToken.setTokenNumber(savedToken.getId() + 1000);

        return savedToken;
    }

    private void validateInputs(String username, Long phoneNumber, String address, String vehicleNumber,
            VehicleType vehicleType, Long gateNumber) throws InvalidUserDetailsException, InvalidVehicleDetailsExcepton, InvalidGateDetailsException {
        userService.validateUser(username, phoneNumber, address);
        vehicleService.validateVehicle(vehicleNumber, vehicleType);
        gateService.validateGate(gateNumber);
    }

    private Token saveToken(Token token) {
        return tokenRepository.saveToken(token);
    }

    public Token fetchToken(Long tokenNumber) throws InvalidTokenException {
        Optional<Token> tokenOptional = tokenRepository.fetchToken(tokenNumber - 1000);
        if (tokenOptional.isEmpty()) {
            throw new InvalidTokenException("Please enter a valid token number");
        }
        return tokenOptional.get();
    }

    public void printToken(Token token) {
        if (token != null) {
            System.out.println("Token Number: " + token.getTokenNumber());
            System.out.println("Vehicle Number: " + token.getVehicle().getVehicleDetails().getVehicleNumber());
            System.out.println("Vehicle Type: " + token.getVehicle().getVehicleDetails().getVehicleType());
            System.out.println("Entry Time: " + token.getEntryTime());
            System.out.println("Spot Number: " + token.getAssignedSlotNumber());
            System.out.println("Floor Number: " + token.getAssignedFloorNumber());
        }
    }
}
