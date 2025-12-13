package org.example.strategey;

import org.example.exception.InvalidTokenException;
import org.example.model.Payment;
import org.example.model.Token;

public interface PricingStrategy {

    void calculateAmount(Token token, Payment payment) throws InvalidTokenException;

}