package fr.unice.polytech.si4.conception.l.marcel.eat;

/**
 * If there is no delivery man dispo MarcelEat return a negatif awser to the store
 */
public class NoDeliveryManDispo extends Exception {
    NoDeliveryManDispo(String message) {super(message);}
}
