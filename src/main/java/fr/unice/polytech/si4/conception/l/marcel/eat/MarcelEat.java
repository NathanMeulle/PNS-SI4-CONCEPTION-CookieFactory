package fr.unice.polytech.si4.conception.l.marcel.eat;

import fr.unice.polytech.si4.conception.l.Log;
import fr.unice.polytech.si4.conception.l.order.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the MarcelEat service
 */

public class MarcelEat {

    protected static List<DeliveryMan> deliveryMans = new ArrayList<>();
    protected static List<Order> orderToPay = new ArrayList<>();
    public static double bank;

    MarcelEat() {
        //empty
    }

    public static void pickUpByDeliveryMan(Order order) {
        Log.add("Order : "+ order.getIdOrder() + " has been pick up by a delivery man");
    }

    /**
     * Create the delivery man team
     */
    public static void initialization() {
        for (int i = 0; i < 2; i++) {
            deliveryMans.add(new DeliveryMan());
        }
        bank = 0;
    }


    /**
     * Request for a delivery
     * Check if a delivery man is up to delivery
     * assign the order to this delivery man
     * @param order the order
     * @throws NoDeliveryManDispo if no one is Dispo
     */
    public static void requestDelivery(Order order) throws NoDeliveryManDispo {
        for (DeliveryMan deliveryMan : deliveryMans) {
            if (deliveryMan.isDispo()) {
                deliveryMan.assignOrder(order);
                orderToPay.add(order);
                return;
            }
        }
        throw new NoDeliveryManDispo("There is no delivery man dispo");
    }

    /**
     * When a store pay Marcel Eat, it removes the order to the list of none paid orders
     * @param order the order
     */
    public static void pay(Order order, double price) {
        for (Order o : orderToPay) {
            if (o.equals(order)) {
                orderToPay.remove(order);
                bank += price;
            }
        }
    }

}

