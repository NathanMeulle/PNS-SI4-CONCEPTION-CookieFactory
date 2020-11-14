package fr.unice.polytech.si4.conception.l.customer;

import fr.unice.polytech.si4.conception.l.exceptions.ErrorPreparingOrder;
import fr.unice.polytech.si4.conception.l.exceptions.NotAlreadyCooked;
import fr.unice.polytech.si4.conception.l.exceptions.NotPaid;
import fr.unice.polytech.si4.conception.l.order.Order;
import fr.unice.polytech.si4.conception.l.products.Cookie;
import fr.unice.polytech.si4.conception.l.store.Store;

public interface CustomerInterface {

    void createOrder(Store store);

    void makeOrder() throws ErrorPreparingOrder;

    void addCookie(Cookie cookie, int quantity);

    void pickUpOrder() throws NotAlreadyCooked, NotPaid;

    double getPrice();

    Order getOrder();
}
