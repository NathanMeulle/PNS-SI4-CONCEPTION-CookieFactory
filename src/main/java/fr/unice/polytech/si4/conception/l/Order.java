package fr.unice.polytech.si4.conception.l;
/** Represents an Order
 * @author Delmotte Vincent
 */
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

    public Order() {
        this.idOrder = generateIdOrder();
        this.date = new Date();
        this.cookies = new HashMap<>();
        this.nbCookies = 0;
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
        calculatePrice();
    }

    private void calculatePrice() {
        cookies.forEach((cookie,quantity) -> {
            this.price += cookie.getPrice() * quantity;
        });
        Log.add(String.format("La commande id:%d coûte %d€", this.getIdOrder(), this.price));
    }

    public void validOrder() {
        if(isAchievable()){
            this.store.prepareOrder(this);
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
