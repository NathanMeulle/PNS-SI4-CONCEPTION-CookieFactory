package fr.unice.polytech.si4.conception.l;

import java.util.ArrayList;

public class OrderHistory {

    private ArrayList<Order> listOrder;

    public OrderHistory() {}

    public void addOrder(Order order){
        listOrder.add(order);
    }

    public void generateStats() {}
    
}
