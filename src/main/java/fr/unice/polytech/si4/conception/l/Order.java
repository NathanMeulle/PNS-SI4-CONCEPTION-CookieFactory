package fr.unice.polytech.si4.conception.l;
/** Represents an Order
 * @author Delmotte Vincent
 */
import fr.unice.polytech.si4.conception.l.exceptions.ErrorPreparingOrder;
import fr.unice.polytech.si4.conception.l.exceptions.NotAlreadyCooked;
import fr.unice.polytech.si4.conception.l.exceptions.NotPaid;

import java.util.*;


public class Order {
    private int idOrder;
    private Date date;
    private Store store;
    private double price;
    private Map<Cookie, Integer> cookies;
    private AnonymousCustomer anonymousCustomer;
    private boolean isDone;
    private boolean isPaid;
    private int nbCookies;
    private StateOrder stateOrder;
    private Date pickUpTime;

    /**
     * Creates an order with an ID, a date of creation and the state Choice
     */
    public Order() {
        this.idOrder = generateIdOrder();
        this.date = new Date();
        this.cookies = new HashMap<>();
        this.nbCookies = 0;
        this.stateOrder = StateOrder.CHOICE;
        this.isDone = false;
        this.isPaid = false;
        this.pickUpTime = null;
    }

    public void assignCustomer(AnonymousCustomer anonymousCustomer) {
        this.anonymousCustomer = anonymousCustomer;
    }

    /**
     * Generate a unique id that permits to recognize an order
     *
     * @return id
     */
    private int generateIdOrder() {
        return hashCode();
    }

    /**
     * Add a specific cookie to the order with a quantity. If the cookie is already present, increment its quantity
     * Update price of the order
     *
     * @param cookie   Cookie's type
     * @param quantity Cookie's quantity
     */
    public void addCookie(Cookie cookie, int quantity) {
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
    }

    private void calculatePrice() {
        price = 0.0; // on réinitialise le prix et on re parcourt tous les cookies
        cookies.forEach((cookie, quantity) ->
            this.price += cookie.getPrice() * quantity);
        Log.add(String.format("La commande id:%d coûte %f€", this.getIdOrder(), this.price));
    }

    /**
     * When the customer pick up his order, it's put in OrderHistory
     * If order not ready, raise NotAlreadyCookedException
     */
    public void pickedUp() throws NotAlreadyCooked, NotPaid {
        if (!isPaid) {
            throw new NotPaid("You did not pay");
        }
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
            Log.add("Order:" + this.getIdOrder() + " - Validated\n" + "Pick up time: " + pickUpTime.toString());
            this.store.prepareOrder(this);
        } else {
            this.setStateOrder(StateOrder.REFUSED);
            Log.add("Order:" + this.getIdOrder() + " - Refused");
            throw new ErrorPreparingOrder("Erreur lors de la préparation de commande par la cuisine !");
        }
    }

    /**
     * set the pickup time of this order
     * @param time order pickup time
     */
    public void choosePickUpTime(Date time){
        this.pickUpTime = time;
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

    public double getPrice() {
        return price;
    }

    public double getTTCPrice() {
        return price *= getStore().getTax();
    }

    public void setPrice(double price) {
        this.price = price;
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

    public AnonymousCustomer getAnonymousCustomer() {
        return anonymousCustomer;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public boolean getIsPaid() {
        return isPaid;
    }

    public void isDone() {
        this.isDone = true;
    }

    public void isPaid() {
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
                price == order.price &&
                nbCookies == order.nbCookies &&
                date.equals(order.date) &&
                store.equals(order.store) &&
                cookies.equals(order.cookies) &&
                anonymousCustomer.equals(order.anonymousCustomer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrder, date, store, anonymousCustomer);
    }
}
