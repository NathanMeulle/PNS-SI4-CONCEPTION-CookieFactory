package fr.unice.polytech.si4.conception.l;

import java.util.*;

/**
 * Stock all the order made in a store
 */

public class OrderHistory {

    private List<Order> listOrder;

    /**
     * An orderHistory is attached to a specific store and it's create when the store is create
     */
    public OrderHistory() {
        this.listOrder = new ArrayList<>();
    }

    /**
     * When you add an order in OrderHistory, its state change to classified
     * @param order
     */
    public void addOrder(Order order){
        listOrder.add(order);
        order.setStateOrder(StateOrder.CLASSIFIED);
    }


    public List<Order> getListOrder() {
        return listOrder;
    }
}
