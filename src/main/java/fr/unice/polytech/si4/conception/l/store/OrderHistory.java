package fr.unice.polytech.si4.conception.l.store;

import fr.unice.polytech.si4.conception.l.order.Order;
import fr.unice.polytech.si4.conception.l.order.StateOrder;
import fr.unice.polytech.si4.conception.l.products.Cookie;

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
     *
     * @param order
     */
    public void addOrder(Order order) {
        listOrder.add(order);
        order.setStateOrder(StateOrder.CLASSIFIED);
    }

    /**
     * Récupère les commandes sur les 30 derniers jours
     *
     * @return une liste de commandes
     */
    public List<Order> getRecentOrders() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -30);
        Date date = calendar.getTime();

        List<Order> recentOrders = new ArrayList<>();
        for (Order order : listOrder) {
            if (order.getDate().compareTo(date) >= 0) { // date de commande >= date Actuelle - 30jours
                // la date de commande est effectuée il y a moins de 30 jours
                recentOrders.add(order);
            }
        }
        return recentOrders;
    }


    public Map<Cookie, Integer> countStoreCookie(List<Order> orders) {
        Map<Cookie, Integer> totalCookie = new HashMap<>();
        for (Order order : orders) {
            for (Cookie c : order.getCookies().keySet()) {
                if (totalCookie.containsKey(c)) {
                    int updatedQuantity = totalCookie.get(c) + order.getCookies().get(c);
                    totalCookie.replace(c, updatedQuantity);
                } else {
                    totalCookie.put(c, order.getCookies().get(c));
                }

            }
        }
        return totalCookie;
    }

    private Cookie getBestCookie(Map<Cookie, Integer> totalCookie){
        Cookie bestCookie = null;
        for (Map.Entry<Cookie, Integer> entry : totalCookie.entrySet()) {
            if (bestCookie == null || entry.getValue() >= totalCookie.get(bestCookie)) {
                bestCookie = changeBestOf(totalCookie, bestCookie, entry);
            }
        }
        return bestCookie;
    }

    private Cookie changeBestOf(Map<Cookie, Integer> totalCookie, Cookie bestCookie, Map.Entry<Cookie, Integer> entry) {
        if (entry.getValue().equals(totalCookie.get(bestCookie))) {
            double tmp = (bestCookie == null)?0: bestCookie.getPrice();
            bestCookie = (entry.getKey().getPrice() < tmp) ? entry.getKey() : bestCookie; // en cas d'égalité, on renvoie le cookie le moins cher
        } else bestCookie = entry.getKey();
        return bestCookie;
    }

    public Cookie getBestCookieStore(){
        return getBestCookie(countStoreCookie(getRecentOrders()));
    }


}
