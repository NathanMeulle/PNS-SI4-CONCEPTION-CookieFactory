package fr.unice.polytech.si4.conception.l.exceptions;

/**
 * This exception warning that an order isn't cook yet
 */
public class NotContainsIngredient extends Exception {
    public NotContainsIngredient(String message) {super(message);}
}
