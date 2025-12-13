package org.example.contoller;

import lombok.AllArgsConstructor;
import org.example.dto.GenerateBillRequestDTO;
import org.example.dto.GenerateBillResponseDTO;
import org.example.exception.InvalidGateDetailsException;
import org.example.exception.InvalidParkingLotException;
import org.example.exception.InvalidTokenException;
import org.example.model.Bill;
import org.example.service.BillService;

@AllArgsConstructor
public class BillController {

    private BillService billService;

    public GenerateBillResponseDTO generateBill(GenerateBillRequestDTO generateBillRequestDTO) {
        GenerateBillResponseDTO generateBillResponseDTO = new GenerateBillResponseDTO();

        try {
            Bill bill = billService.generateBill(
                    generateBillRequestDTO.getGateNumber(),
                    generateBillRequestDTO.getTokenNumber(),
                    generateBillRequestDTO.getPaymentType()
            );
            generateBillResponseDTO.setBill(bill);
            generateBillResponseDTO.setStatus("SUCCESS");
        } catch (Exception | InvalidGateDetailsException | InvalidTokenException | InvalidParkingLotException e) {
            generateBillResponseDTO.setErrorMessage(e.getMessage());
            generateBillResponseDTO.setStatus("FAILURE");
        }

        return  generateBillResponseDTO;
    }

    public void printBill(Bill bill) {
        billService.printBill(bill);
    }
}
