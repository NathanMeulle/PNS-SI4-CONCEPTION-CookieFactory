package fr.unice.polytech.si4.conception.l.exceptions;

/**
 * This exception warning that an order will be cancel
 */
public class ErrorPreparingOrder extends Exception{
    public ErrorPreparingOrder(String message){
        super(message);
    }
}
