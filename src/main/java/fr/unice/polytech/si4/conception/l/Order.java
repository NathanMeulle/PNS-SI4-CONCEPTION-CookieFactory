package fr.unice.polytech.si4.conception.l;
/** Represents an Order
 * @author Delmotte Vincent
 */
import java.util.Date;
import java.util.HashMap;


public class Order {
    protected int idOrder;
    protected Date date;
    protected Store store;
    protected int price;
    protected HashMap<Cookie, Integer> cookies;
    protected AnonymousCustomer anonymousCustomer;

    public Order(Store store, AnonymousCustomer anonymousCustomer) {
        this.idOrder = generateIdOrder();
        this.store = store;
        this.date = new Date();
        this.cookies = new HashMap<>();
        this.anonymousCustomer = anonymousCustomer;
    }

    /**
     * Generate a unique id that permits to recognize an order
     * @return id
     */
    private int generateIdOrder() {
        return 1000;
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
        calculatePrice();
    }

    private void calculatePrice() {
        cookies.forEach((cookie,quantity) -> {
            this.price += cookie.getPrice() * quantity;
        });
        Log.add(String.format("La commande id:%d coûte %d€", this.getIdOrder(), this.price));
    }

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

}
