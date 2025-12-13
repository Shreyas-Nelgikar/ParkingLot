package org.example.strategey;

import lombok.AllArgsConstructor;
import org.example.exception.InvalidTokenException;
import org.example.model.Payment;
import org.example.model.Token;
import org.example.model.VehicleType;
import org.example.service.TokenService;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class VehicleTypePricingStrategy implements PricingStrategy {

    private Map<String, Double> priceByVehicleType = new HashMap<>();

    public VehicleTypePricingStrategy() {
        priceByVehicleType.put(VehicleType.TWO_WHEELER.name(), 10.0);
        priceByVehicleType.put(VehicleType.FOUR_WHEELER.name(), 20.0);
    }

    @Override
    public void calculateAmount(Token token, Payment payment) throws InvalidTokenException {
        VehicleType vehicleType = token.getVehicle().getVehicleDetails().getVehicleType();
        Double amount = priceByVehicleType.get(vehicleType.name());
        payment.setAmount(amount);
    }
}
