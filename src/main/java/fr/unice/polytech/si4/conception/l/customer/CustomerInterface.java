package fr.unice.polytech.si4.conception.l.customer;

import fr.unice.polytech.si4.conception.l.exceptions.ErrorPreparingOrder;
import fr.unice.polytech.si4.conception.l.exceptions.NotAlreadyCooked;
import fr.unice.polytech.si4.conception.l.exceptions.NotPaid;
import fr.unice.polytech.si4.conception.l.exceptions.WrongPickUpTimeException;
import fr.unice.polytech.si4.conception.l.order.Order;
import fr.unice.polytech.si4.conception.l.products.Cookie;
import fr.unice.polytech.si4.conception.l.store.Store;

import java.util.Date;


public interface CustomerInterface {

    void createOrder(Store store);

    void submitOrder() throws ErrorPreparingOrder;

    void addCookie(Cookie cookie, int quantity);

    void pickUpOrder(Date date) throws NotAlreadyCooked, NotPaid, WrongPickUpTimeException;

    double getPrice();

    Order getOrder();
}
