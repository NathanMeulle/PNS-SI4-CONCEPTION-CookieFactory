package fr.unice.polytech.si4.conception.l;

import fr.unice.polytech.si4.conception.l.exceptions.ErrorPreparingOrder;
import fr.unice.polytech.si4.conception.l.exceptions.NotAlreadyCooked;
import fr.unice.polytech.si4.conception.l.exceptions.NotPaid;

public interface CustomerInterface {

    void createOrder(Store store);

    void makeOrder() throws ErrorPreparingOrder;

    void addCookie(Cookie cookie, int quantity);

    void pickUpOrder() throws NotAlreadyCooked, NotPaid;

    double getPrice();

    Order getOrder();
}
