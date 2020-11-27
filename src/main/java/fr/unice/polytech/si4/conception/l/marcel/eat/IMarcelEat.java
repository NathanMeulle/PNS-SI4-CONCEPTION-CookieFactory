package fr.unice.polytech.si4.conception.l.marcel.eat;

import fr.unice.polytech.si4.conception.l.order.Order;

public interface IMarcelEat {

    void pickUpByDeliveryMan(Order order);
    void requestDelivery(Order order);
    void pay(Order order, double price);

}
