package fr.unice.polytech.si4.conception.l.customer;

import fr.unice.polytech.si4.conception.l.exceptions.*;
import fr.unice.polytech.si4.conception.l.marcel.eat.NoDeliveryManDispo;
import fr.unice.polytech.si4.conception.l.order.Order;
import fr.unice.polytech.si4.conception.l.products.Cookie;
import fr.unice.polytech.si4.conception.l.products.composition.Ingredient;
import fr.unice.polytech.si4.conception.l.store.Store;

import java.util.Date;


public interface CustomerInterface {

    void createOrder(Store store);

    void submitOrder() throws ErrorPreparingOrder, NoDeliveryManDispo;

    void addCookie(Cookie cookie, int quantity);

    Cookie addIngredientToCookie(Cookie cookie, Ingredient ingredient) throws InvalidNumberIngredient, InvalidTypeIngredient;

    Cookie removeIngredientToCookie(Cookie c, Ingredient ingredient) throws InvalidNumberIngredient, InvalidTypeIngredient;

    void pickUpOrder(Date date) throws NotAlreadyCooked, NotPaid, WrongPickUpTimeException;

    double getPrice();

    Order getOrder();
}
