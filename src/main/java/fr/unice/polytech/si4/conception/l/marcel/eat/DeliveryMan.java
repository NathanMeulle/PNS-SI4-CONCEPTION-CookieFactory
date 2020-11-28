package fr.unice.polytech.si4.conception.l.marcel.eat;

import fr.unice.polytech.si4.conception.l.order.Order;

import java.util.Objects;

/**
 * This is a representation of delivery man
 */

public class DeliveryMan {

    private boolean dispo;
    private int id;
    private Order order;


    DeliveryMan() {
        this.id = hashCode();
        this.dispo = true;
    }

    /**
     * Mock an assignment of a order to a delivery man
     * All order get longitude == 15 and lattitude == 10
     * @param order the order
     */
    public void assignOrder(Order order) {
        this.order = order;
    }

    /**
     * @return true if no order assign yet
     *         false if an order is already assign to this delivery man
     */
    public boolean isDispo() {
        return this.dispo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeliveryMan)) return false;
        DeliveryMan that = (DeliveryMan) o;
        return isDispo() == that.isDispo() &&
                id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isDispo(), id);
    }
}
