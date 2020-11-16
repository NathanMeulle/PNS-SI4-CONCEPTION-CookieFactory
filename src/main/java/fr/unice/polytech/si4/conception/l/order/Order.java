package fr.unice.polytech.si4.conception.l.order;
/** Represents an Order
 * @author Delmotte Vincent
 */


import fr.unice.polytech.si4.conception.l.Log;
import fr.unice.polytech.si4.conception.l.SystemInfo;
import fr.unice.polytech.si4.conception.l.customer.AnonymousCustomer;
import fr.unice.polytech.si4.conception.l.exceptions.ErrorPreparingOrder;
import fr.unice.polytech.si4.conception.l.exceptions.NotAlreadyCooked;
import fr.unice.polytech.si4.conception.l.exceptions.NotPaid;
import fr.unice.polytech.si4.conception.l.exceptions.WrongPickUpTimeException;
import fr.unice.polytech.si4.conception.l.products.Cookie;
import fr.unice.polytech.si4.conception.l.store.Store;

import java.util.*;


public class Order {

    private Date date;
    private Store store;
    private Map<Cookie, Integer> cookies;
    private int nbCookies;
    private int idOrder;
    private AnonymousCustomer customer;
    private boolean isPaid;
    private StateOrder stateOrder;
    private Date pickUpTime;
    private double priceHT;
    private double priceTTC;


    /**
     * Creates an order with an ID, a date of creation and the state Choice
     */
    private Order(OrderBuilder builder) {
        this.date = builder.date;
        this.store = builder.store;
        this.cookies = builder.cookies;
        this.nbCookies = builder.nbCookies;
        this.idOrder = builder.idOrder;
        this.customer = builder.customer;
        this.isPaid = builder.isPaid;
        this.pickUpTime = builder.pickUpTime;
    }




    /**
     * When the customer pick up his order, it's put in OrderHistory
     * If order not ready, raise NotAlreadyCookedException
     */
    public void pickedUp() throws NotAlreadyCooked, NotPaid, WrongPickUpTimeException {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        if (!isPaid) {
            throw new NotPaid("You did not pay");
        }

        if (date.compareTo(pickUpTime) < 0)
            throw new WrongPickUpTimeException("You're are way too early");

        if (this.getStateOrder().equals(StateOrder.COOKED)) {
            this.store.addToOrderHistory(this);
            Log.add("La commande " + this.idOrder + "a été retiré et est maintenant archivée.");
        } else {
            Log.add("La commande " + this.idOrder + "a tenté d'être retiré mais n'est pas encore prête");
            throw new NotAlreadyCooked("Your order isn't ready yet");
        }
    }

    /**
     * When the customer finish the selection and confirme his order
     * Check if kitchen can do this order
     * If true => order state is Validated
     * Else order state is Refused
     */
    public void submit() throws ErrorPreparingOrder {
        this.setStateOrder(StateOrder.SUBMITTED);
        if (this.isAchievable()) {
            this.setStateOrder(StateOrder.VALIDATED);
            Log.add("Order:" + this.getIdOrder() + " - Validated\n" + "Created at:" + date + "\nPick up time: " + pickUpTime.toString());
            this.store.prepareOrder(this);
        } else {
            this.setStateOrder(StateOrder.REFUSED);
            Log.add("Order:" + this.getIdOrder() + " - Refused");
            throw new ErrorPreparingOrder("Erreur lors de la préparation de commande par la cuisine !");
        }
    }


    /**
     * *******************************************************************************
     * GETTERS / SETTERS
     * ********************************************************************************
     */

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public Date getDate() {
        return date;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public double getPriceHT() {
        return priceHT;
    }

    public double getPriceTTC() {
        return priceTTC;
    }

    public void setPriceHT(double priceHT) {
        this.priceHT = priceHT;
    }

    public Map<Cookie, Integer> getCookies() {
        return cookies;
    }

    public void setCookies(Map<Cookie, Integer> cookies) {
        this.cookies = cookies;
    }

    public StateOrder getStateOrder() {
        return stateOrder;
    }

    public void setStateOrder(StateOrder stateOrder) {
        this.stateOrder = stateOrder;
    }

    public AnonymousCustomer getCustomer() {
        return customer;
    }

    public boolean getIsPaid() {
        return isPaid;
    }

    public void paid() {
        this.isPaid = true;
    }

    public boolean isAchievable() {
        return this.store.achievableCookie(cookies);
    }

    public int getNbCookies() {
        return this.nbCookies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return idOrder == order.idOrder &&
                priceHT == order.priceHT &&
                nbCookies == order.nbCookies &&
                date.equals(order.date) &&
                store.equals(order.store) &&
                cookies.equals(order.cookies) &&
                customer.equals(order.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrder, date, store, customer);
    }


    /**
     * This class is the builder to construct the order
     * @author Delmotte Vincent
     * @author Nathan Meulle
     * @patern Builder
     */

    public static class OrderBuilder {

        private Date date;
        private Store store;
        private Map<Cookie, Integer> cookies;
        private int nbCookies;
        private int idOrder;
        private AnonymousCustomer customer;
        private boolean isPaid;
        private StateOrder stateOrder;
        private Date pickUpTime;
        private double priceHT;
        private double priceTTC;



        public OrderBuilder(Store store) {
            if (store == null) {
                throw new IllegalArgumentException("order must be created with a store");
            }
            this.date = new Date();
            this.store = store;
            this.cookies = new HashMap<>();
            this.nbCookies = 0;
            this.idOrder = generateIdOrder();
            this.isPaid = false;
            this.stateOrder = StateOrder.CHOICE;
            this.pickUpTime = new Date();
        }


        /**
         * Add a specific cookie to the order with a quantity. If the cookie is already present, increment its quantity
         * Update price of the order
         *
         * @param cookie   Cookie's type
         * @param quantity Cookie's quantity
         */
        public OrderBuilder addCookie(Cookie cookie, int quantity) {
            if (cookies.containsKey(cookie)) {
                int updatedQuantity = cookies.get(cookie) + quantity;
                cookies.replace(cookie, updatedQuantity);
                Log.add(String.format("Ajout de cookie : %s - quantité : %d", cookie.getName(), updatedQuantity));
            } else {
                cookies.put(cookie, quantity);
                Log.add(String.format("Ajout de cookie : %s - quantité : %d", cookie.getName(), quantity));
            }
            this.nbCookies += quantity;
            calculatePrice();
            return this;
        }

        /**
         * Calculate price of an order
         * Apply 10% in all cookies equal to the best of national or best of store cookie
         */
        public void calculatePrice() {
            SystemInfo systemInfo = SystemInfo.getInstance();
            priceHT = 0.0; // on réinitialise le prix et on re parcourt tous les cookies
            cookies.forEach((cookie, quantity) -> {
                        if (cookie.equals(systemInfo.getBestCookieNational()) || cookie.equals(this.store.getBestCookie())) {
                            this.priceHT += cookie.getPrice() * quantity * 0.9;
                        } else {
                            this.priceHT += cookie.getPrice() * quantity;
                        }

                    }
            );
            this.priceTTC = priceHT * store.getTax();
            Log.add(String.format("La commande id:%d coûte %f€ HT", idOrder, this.priceTTC));
        }

        /**
         * Assign a customer or an anonymous customer
         * @param customer
         */
        public OrderBuilder assignCustomer(AnonymousCustomer customer) {
            this.customer = customer;
            return this;
        }

        /**
         * set the pickup time of this order
         * @param time order pickup time
         */
        public OrderBuilder choosePickUpTime(Date time){
            this.pickUpTime = time;
            return this;
        }

        /**
         * Generate a unique id that permits to recognize an order
         * @return id
         */
        private int generateIdOrder() {
            return hashCode();
        }

        public void applyDiscount() {
            this.priceTTC *= 0.9;
        }

        /**
         * return an order create by the builder
         * @return
         */
        public Order build() {
            return new Order(this);
        }

        public Map<Cookie, Integer> getCookies() {
            return cookies;
        }

        public int getNbCookies() {
            return this.nbCookies;
        }
        public double getPriceTTC() {
            return priceTTC;
        }


    }

}


