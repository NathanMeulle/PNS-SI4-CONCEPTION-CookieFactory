package fr.unice.polytech.si4.conception.l.order;

/** Represents the state of a command for a special process
 * @author Delmotte Vincent
 */

public enum StateOrder {
    CHOICE,
    SUBMITTED,
    VALIDATED,
    COOKED,
    CLASSIFIED,
    REFUSED
}
