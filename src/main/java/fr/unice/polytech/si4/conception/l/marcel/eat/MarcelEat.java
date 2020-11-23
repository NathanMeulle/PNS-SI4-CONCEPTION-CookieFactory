package fr.unice.polytech.si4.conception.l.marcel.eat;

import fr.unice.polytech.si4.conception.l.order.Order;
import fr.unice.polytech.si4.conception.l.store.Store;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the MarcelEat service
 */

public class MarcelEat {

    public static List<DeliveryMan> deliveryMans = new ArrayList<>();
    public static List<Order> orderToPay = new ArrayList<>();

    MarcelEat() {
        //empty
    }

    /**
     * Create the delivery man team
     */
    public void initialization() {
        for (int i = 0; i < 2; i++) {
            deliveryMans.add(new DeliveryMan(0,0));
        }
    }


    /**
     * Request for a delivery
     * Check if a delivery man is up to delivery
     * assign the order to this delivery man
     * @param order
     * @throws NoDeliveryManDispo
     */
    public void requestDelivery(Order order) throws NoDeliveryManDispo {
        for (DeliveryMan deliveryMan : deliveryMans) {
            if (deliveryMan.isDispo()) {
                deliveryMan.assignOrder(order);
                return;
            }
        }
        throw new NoDeliveryManDispo("There is no delivery man dispo");
    }

    /**
     * When a store pay Marcel Eat, it removes the order to the list of none paid orders
     * @param order
     */
    public void pay(Order order) {
        for (Order o : orderToPay) {
            if (o.equals(order)) {
                orderToPay.remove(order);
            }
        }
    }
}

