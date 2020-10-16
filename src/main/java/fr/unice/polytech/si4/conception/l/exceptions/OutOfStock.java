package fr.unice.polytech.si4.conception.l;

/**
 * This exception warning that an order isn't achievable
 */
public class OutOfStock extends Exception {
    public OutOfStock(String errorMessage) {
        super(errorMessage);
    }
}
