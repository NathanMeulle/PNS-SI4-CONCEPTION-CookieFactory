package fr.unice.polytech.si4.conception.l.exceptions;

/**
 * This exception warning if the customer tries to remove an ingredient that was not in the recipe
 */
public class NotAlreadyCooked extends Exception {
    public NotAlreadyCooked(String message) {super(message);}
}
