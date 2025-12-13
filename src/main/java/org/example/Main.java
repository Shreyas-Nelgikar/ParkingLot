package org.example;

import org.example.contoller.BillController;
import org.example.contoller.TokenController;
import org.example.dto.GenerateBillRequestDTO;
import org.example.dto.GenerateBillResponseDTO;
import org.example.dto.IssueTokenRequestDTO;
import org.example.dto.IssueTokenResponseDTO;
import org.example.model.PaymentType;
import org.example.model.VehicleType;
import org.example.respository.*;
import org.example.service.*;
import org.example.strategey.RandomSlotAssignmentStrategy;
import org.example.strategey.VehicleTypePricingStrategy;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Initialize repositories
        UserRepository userRepository = new UserRepository();
        VehicleDetailsRepository vehicleDetailsRepository = new VehicleDetailsRepository();
        UserToVehicleRepository userToVehicleRepository = new UserToVehicleRepository();
        GateRepository gateRepository = new GateRepository();
        GateToParkingLotRepository gateToParkingLotRepository = new GateToParkingLotRepository();
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();
        TokenRepository tokenRepository = new TokenRepository();
        PaymentRepository paymentRepository = new PaymentRepository();
        BillRepository billRepository = new BillRepository();
        
        // Initialize services
        UserService userService = new UserService(userRepository);
        VehicleService vehicleService = new VehicleService(vehicleDetailsRepository, userToVehicleRepository);
        GateService gateService = new GateService(gateRepository, gateToParkingLotRepository);
        ParkingLotService parkingLotService = new ParkingLotService(parkingLotRepository);
        RandomSlotAssignmentStrategy slotAssignmentStrategy = new RandomSlotAssignmentStrategy();
        PaymentService paymentService = new PaymentService(new VehicleTypePricingStrategy(), paymentRepository);
        
        // Initialize TokenService
        TokenService tokenService = new TokenService(userService, vehicleService, gateService, parkingLotService, tokenRepository, slotAssignmentStrategy);

        // Initialize BillService
        BillService billService = new BillService(gateService, paymentService, billRepository, parkingLotService, tokenService);
        
        // Initialize TokenController
        TokenController tokenController = new TokenController(tokenService);

        // Initialize BillController
        BillController billController = new BillController(billService);

        System.out.println("Please enter the no of vehicles for which you want to issue a token");
        int size = scanner.nextInt();

        for (int i=0; i<size; i++) {
            System.out.println("Please enter the username");
            String username = scanner.next();

            System.out.println("Please enter the phone number");
            String phoneNumber = scanner.next();

            System.out.println("Please enter the address");
            String address = scanner.next();

            System.out.println("Please enter the vehicle number");
            String vehicleNumber = scanner.next();

            System.out.println("Please enter the vehicle type");
            String vehicleType = scanner.next();

            System.out.println("Please enter the gate number");
            Long gateNumber = scanner.nextLong();

            IssueTokenRequestDTO issueTokenRequestDTO = new IssueTokenRequestDTO(
                    username, Long.parseLong(phoneNumber), address, vehicleNumber,
                    VehicleType.valueOf(vehicleType), gateNumber
            );
            IssueTokenResponseDTO issueTokenResponseDTO = tokenController.issueToken(issueTokenRequestDTO);
            tokenController.printToken(issueTokenResponseDTO.getToken());
            System.out.println(issueTokenResponseDTO.getErrorMessage());
            System.out.println(issueTokenResponseDTO.getStatus());
        }

        for (int i=0; i<size; i++) {
            System.out.println("Please enter the gate number");
            Long gateNumber = scanner.nextLong();

            System.out.println("Please enter the token number");
            Long tokenNumber = scanner.nextLong();

            System.out.println("Please enter the payment type");
            String paymentType = scanner.next();

            GenerateBillRequestDTO generateBillRequestDTO = new GenerateBillRequestDTO(
                    gateNumber, tokenNumber, PaymentType.valueOf(paymentType)
            );
            GenerateBillResponseDTO generateBillResponseDTO = billController.generateBill(generateBillRequestDTO);

            billController.printBill(generateBillResponseDTO.getBill());
            System.out.println(generateBillResponseDTO.getErrorMessage());
            System.out.println(generateBillResponseDTO.getStatus());
        }
    }
}