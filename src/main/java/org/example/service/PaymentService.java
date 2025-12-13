package org.example.service;

import lombok.AllArgsConstructor;
import org.example.exception.InvalidTokenException;
import org.example.model.Payment;
import org.example.model.PaymentStatus;
import org.example.model.PaymentType;
import org.example.model.Token;
import org.example.respository.PaymentRepository;
import org.example.strategey.PricingStrategy;

import java.util.Date;

@AllArgsConstructor
public class PaymentService {

    private PricingStrategy pricingStrategy;
    private PaymentRepository paymentRepository;

    public Payment initiatePayment(PaymentType paymentType, Token token) throws InvalidTokenException {
        Payment payment = new Payment();
        payment.setPaymentStatus(PaymentStatus.IN_PROGRESS);
        payment.setPaymentType(paymentType);

        pricingStrategy.calculateAmount(token, payment);

        makePayment(payment);
        return payment;
    }

    private void makePayment(Payment payment) {
        payment.setPaymentDate(new Date());
        payment.setPaymentStatus(PaymentStatus.SUCCESS);
        paymentRepository.save(payment);
    }
}
