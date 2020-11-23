package fr.unice.polytech.si4.conception.l.marcel.eat;

import fr.unice.polytech.si4.conception.l.order.Order;

import java.util.Objects;

/**
 * This is a representation of delivery man
 */

public class DeliveryMan {

    private double lattitude;
    private double longitude;
    private boolean dispo;
    private double customerLattitude;
    private double customerLongitude;
    private int id;


    DeliveryMan(double lattitude, double longitude) {
        this.id = hashCode();
        this.lattitude = lattitude;
        this.longitude = longitude;
        this.dispo = true;
    }

    /**
     * Mock an assignment of a order to a delivery man
     * All order get longitude == 15 and lattitude == 10
     * @param order
     */
    public void assignOrder(Order order) {
        this.longitude = 15;
        this.lattitude = 10;
    }

    /**
     * @return true if no order assign yet
     * @return false if an order is already assign to this delivery man
     */
    public boolean isDispo() {
        return this.dispo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryMan that = (DeliveryMan) o;
        return Double.compare(that.lattitude, lattitude) == 0 &&
                Double.compare(that.longitude, longitude) == 0 &&
                dispo == that.dispo &&
                id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lattitude, longitude, dispo, id);
    }
}
