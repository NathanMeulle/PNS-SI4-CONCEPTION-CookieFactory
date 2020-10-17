package fr.unice.polytech.si4.conception.l.exceptions;

/**
 * This exception warning that an order isn't achievable
 */
public class OutOfStockException extends Exception {
    public OutOfStockException(String errorMessage) {
        super(errorMessage);
    }
}
