package fr.unice.polytech.si4.conception.l.order;

import fr.unice.polytech.si4.conception.l.customer.AnonymousCustomer;
import fr.unice.polytech.si4.conception.l.products.Cookie;
import fr.unice.polytech.si4.conception.l.store.Store;

import java.util.Date;
import java.util.Map;

/**
 * This class is the builder to construct the order
 * @author Delmotte Vincent
 * @author Nathan Meulle
 * @patern Builder
 */

public class OrderBuilder {

    private int idOrder;
    private Date date;
    private Store store;
    private Map<Cookie, Integer> cookies;
    private AnonymousCustomer customer;
    private boolean isPaid;
    private int nbCookies;
    private StateOrder stateOrder;
    private Date pickUpTime;


    public OrderBuilder(Store store) {
        if (store == null) {
            throw new IllegalArgumentException("order must be created with a store");
        }
        this.store = store;

        this.isPaid = false;
        this.nbCookies = 0;
    }

    /**
     * return an order create by the builder
     * @return
     */
    /*
    public Order build() {
        return new Order(this);
    }
*/


}
