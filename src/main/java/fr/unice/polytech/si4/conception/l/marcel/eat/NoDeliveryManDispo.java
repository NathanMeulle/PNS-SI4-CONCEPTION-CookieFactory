package fr.unice.polytech.si4.conception.l.marcel.eat;

import fr.unice.polytech.si4.conception.l.exceptions.NotAlreadyCooked;

/**
 * If there is no delivery man dispo MarcelEat return a negatif awser to the store
 */
public class NoDeliveryManDispo extends Exception {
    NoDeliveryManDispo(String message) {super(message);}
}
