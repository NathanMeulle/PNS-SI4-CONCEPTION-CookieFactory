package fr.unice.polytech.si4.conception.l.exceptions;

/**
 * This exception warning that an order isn't cook yet
 */
public class NotAlreadyCooked extends Exception {
    public NotAlreadyCooked(String message) {super(message);}
}
