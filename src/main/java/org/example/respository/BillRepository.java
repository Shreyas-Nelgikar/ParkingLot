package org.example.respository;

import org.example.model.Bill;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BillRepository {

    private Map<Long, Bill> billMap = new HashMap<>();
    private Long billId = 0L;

    public void save(Bill bill) {
        bill.setId(billId);
        bill.setCreatedAt(new Date());
        bill.setModifiedAt(new Date());
        bill.setBillId(billId++);
        billMap.put(bill.getId(), bill);
    }
}
