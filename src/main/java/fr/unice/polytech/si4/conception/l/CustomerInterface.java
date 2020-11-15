package fr.unice.polytech.si4.conception.l;

import fr.unice.polytech.si4.conception.l.exceptions.ErrorPreparingOrder;
import fr.unice.polytech.si4.conception.l.exceptions.NotAlreadyCooked;
import fr.unice.polytech.si4.conception.l.exceptions.NotPaid;
import fr.unice.polytech.si4.conception.l.exceptions.WrongPickUpTimeException;

public interface CustomerInterface {

    void createOrder(Store store);

    void makeOrder() throws ErrorPreparingOrder;

    void addCookie(Cookie cookie, int quantity);

    void pickUpOrder() throws NotAlreadyCooked, NotPaid, WrongPickUpTimeException;

    double getPrice();

    Order getOrder();
}
