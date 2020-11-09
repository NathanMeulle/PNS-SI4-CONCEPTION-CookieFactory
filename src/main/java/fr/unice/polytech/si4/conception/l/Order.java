package fr.unice.polytech.si4.conception.l;
/** Represents an Order
 * @author Delmotte Vincent
 */
import fr.unice.polytech.si4.conception.l.exceptions.ErrorPreparingOrder;
import fr.unice.polytech.si4.conception.l.exceptions.NotAlreadyCooked;

import java.util.Date;
import java.util.HashMap;
import java.util.Objects;


public class Order {
    private int idOrder;
    private Date date;
    private Store store;
    private int price;
    private HashMap<Cookie, Integer> cookies;
    private AnonymousCustomer anonymousCustomer;
    private boolean isDone = false;
    private int nbCookies;
    private StateOrder stateOrder;

    /**
     * Creates an order with an ID, a date of creation and the state Choice
     */
    public Order() {
        this.idOrder = generateIdOrder();
        this.date = new Date();
        this.cookies = new HashMap<>();
        this.nbCookies = 0;
        this.stateOrder = StateOrder.Choice;
    }

    public void assignCustomer(AnonymousCustomer anonymousCustomer){
        this.anonymousCustomer = anonymousCustomer;
    }

    /**
     * Generate a unique id that permits to recognize an order
     * @return id
     */
    private int generateIdOrder() {
        return hashCode();
    }

    /**
     * Add a specific cookie to the order with a quantity. If the cookie is already present, increment its quantity
     * Update price of the order
     * @param cookie Cookie's type
     * @param quantity Cookie's quantity
     */
    public void addCookie(Cookie cookie, int quantity) {
        if (cookies.containsKey(cookie)) {
            int updatedQuantity = cookies.get(cookie) + quantity;
            cookies.replace(cookie, updatedQuantity);
            Log.add(String.format("Ajout de cookie : %s - quantité : %d",cookie.getName(), updatedQuantity));
        }
        else {
            cookies.put(cookie, quantity);
            Log.add(String.format("Ajout de cookie : %s - quantité : %d",cookie.getName(), quantity));
        }
        this.nbCookies += quantity;
        this.calculatePrice();
    }

    private void calculatePrice() {
        cookies.forEach((cookie,quantity) -> {
            this.price += cookie.getPrice() * quantity;
        });
        Log.add(String.format("La commande id:%d coûte %d€", this.getIdOrder(), this.price));
    }

    /**
     * When the customer pick up his order, it's put in OrderHistory
     * If order not ready, raise NotAlreadyCookedException
     */
    public void pickedUp() throws NotAlreadyCooked {
        if (this.getStateOrder().equals(StateOrder.Cooked)) {
            this.store.addToOrderHistory(this);
            Log.add("La commande " + this.idOrder + "a été retiré et est maintenant archivée.");
        }
        else {
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
        this.setStateOrder(StateOrder.Submitted);
        if (this.isAchievable()) {
            this.setStateOrder(StateOrder.Validated);
            Log.add("Order:"+ this.getIdOrder() +" - Validated");
            this.store.prepareOrder(this);
        }
        else {
            this.setStateOrder(StateOrder.Refused);
            Log.add("Order:"+ this.getIdOrder() +" - Refused");
            throw new ErrorPreparingOrder(String.format("Erreur lors de la préparation de commande par la cuisine !"));
        }
    }


    /** ********************************************************************************
     *                               GETTERS / SETTERS
     *  ********************************************************************************
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public HashMap<Cookie, Integer> getCookies() {
        return cookies;
    }

    public void setCookies(HashMap<Cookie, Integer> cookies) {
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

    public boolean getIsDone(){
        return isDone;
    }

    public void isDone(){
        this.isDone = true;
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
