package fr.unice.polytech.si4.conception.l;
/** Represents an Order
 * @author Delmotte Vincent
 */
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Order {
    protected int idOrder;
    protected Date date;
    protected Store store;
    protected int price;
    protected int nbOrder;
    protected HashMap<Cookie, Integer> cookies;

    public Order(Store store) {
        this.idOrder = generateIdOrder();
        this.store = store;
        this.date = new Date();
        this.cookies = new HashMap<>();
    }

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
        }
        else {
            cookies.put(cookie, quantity);
        }
        calculatePrice();
    }

    public void calculatePrice() {
        cookies.forEach((cookie,quantity) -> {
            this.price += cookie.getPrice() * quantity;
        });
    }
}
