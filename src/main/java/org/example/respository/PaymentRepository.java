package org.example.respository;

import org.example.model.Payment;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PaymentRepository {

    private Map<Long, Payment> paymentsMap = new HashMap<>();
    private Long paymentId = 0L;

    public void save(Payment payment) {
        payment.setId(paymentId);
        payment.setCreatedAt(new Date());
        payment.setModifiedAt(new Date());
        payment.setPaymentId(paymentId++);
        paymentsMap.put(payment.getId(), payment);
    }
}
