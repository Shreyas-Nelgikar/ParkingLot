package org.example.service;

import lombok.AllArgsConstructor;
import org.example.exception.InvalidGateDetailsException;
import org.example.exception.InvalidParkingLotException;
import org.example.exception.InvalidTokenException;
import org.example.model.*;
import org.example.respository.BillRepository;

import java.util.Date;

@AllArgsConstructor
public class BillService {

    private GateService gateService;
    private PaymentService paymentService;
    private BillRepository billRepository;
    private ParkingLotService parkingLotService;
    private TokenService tokenService;

    public Bill generateBill(Long gateNumber, Long tokenNumber, PaymentType paymentType) throws InvalidGateDetailsException, InvalidTokenException, InvalidParkingLotException {
        Bill bill = new Bill();
        bill.setExitTime(new Date());

        Gate gate = gateService.fetchGateDetails(gateNumber);
        bill.setGate(gate);
        bill.setTokenNumber(tokenNumber);

        Token token = tokenService.fetchToken(tokenNumber);
        Payment payment = paymentService.initiatePayment(paymentType, token);
        bill.setPayment(payment);

        parkingLotService.releaseSlot(token);

        bill.setBillGenerationTime(new Date());
        bill.setExitTime(new Date());
        savedBill(bill);

        return bill;
    }

    private void savedBill(Bill bill) {
        billRepository.save(bill);
    }

    public void printBill(Bill bill) {
        if (bill != null) {
            System.out.println("Bill Id: " + bill.getId());
            System.out.println("Exit Time: " + bill.getExitTime());
            System.out.println("Bill generation Time: " + bill.getBillGenerationTime());
            System.out.println("Payment Id: " + bill.getPayment().getId());
            System.out.println("Payment amount: " + bill.getPayment().getAmount());
        }
    }
}
