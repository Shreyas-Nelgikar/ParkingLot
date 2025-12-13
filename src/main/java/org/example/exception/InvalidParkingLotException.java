package org.example.exception;

public class InvalidParkingLotException extends Throwable {
    public InvalidParkingLotException(String message) {
        super(message);
    }
}
