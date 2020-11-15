package fr.unice.polytech.si4.conception.l;

import fr.unice.polytech.si4.conception.l.customer.Customer;
import fr.unice.polytech.si4.conception.l.exceptions.AlreadyCreatedException;
import fr.unice.polytech.si4.conception.l.order.Order;
import fr.unice.polytech.si4.conception.l.products.Cookie;
import fr.unice.polytech.si4.conception.l.store.Store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author nathan
 * Class containing our company, regrouping our stores, cookies, customers and managers
 * @Patern Singleton
 */
public class SystemInfo {

    private List<Customer> customers;
    private List<Cookie> cookies;
    private List<Store> stores;
    private Cookie bestOfNational;

    private SystemInfo() {
        //Constructeur privé car SINGLETON
        this.cookies = new ArrayList<>();
        this.stores = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.bestOfNational = null;
    }

    private static final SystemInfo INSTANCE = new SystemInfo();

    public static SystemInfo getInstance() {
        return INSTANCE;
    }

    public void resetSystemInfo() {
        this.stores.clear();
        this.customers.clear();
        this.cookies.clear();

    }

    /**
     * allows the creation of a new store
     *
     * @param store a store object
     */
    public void addStore(Store store) throws AlreadyCreatedException {
        if (stores.contains(store)) {
            throw new AlreadyCreatedException("Store already exists");
        } else {
            stores.add(store);
        }
    }

    public Store getStoreByAddress(String address) {
        for (Store s : stores) {
            if (s.getAddress().equals(address))
                return s;
        }
        return null;
    }

    public void addCookie(Cookie cookie) throws AlreadyCreatedException {
        if (cookies.contains(cookie)) {
            throw new AlreadyCreatedException("Cookie already exists");
        } else {
            cookies.add(cookie);
        }
    }

    /**
     * create and add a customer to the customer list. If it already exists, returns an exception.
     *
     * @param name        : name of the customer
     * @param phoneNumber : customer's phone number
     * @param mail        : customer's e-mail
     * @throws AlreadyCreatedException : exception to indicate that the customer already exists in the customer list.
     */
    public void subscription(String name, String phoneNumber, String mail) throws AlreadyCreatedException {
        Customer customer = new Customer(name, phoneNumber, mail);
        addCustomer(customer);
    }

    /**
     * Adds a customer to the customer list. If it already exists, returns an exception.
     *
     * @param customer : the client to be added to the custormers list.
     * @throws AlreadyCreatedException : exception to indicate that the customer already exists in the customer list.
     */
    public void addCustomer(Customer customer) throws AlreadyCreatedException {
        if (customers.contains(customer)) {
            throw new AlreadyCreatedException("Customer already exists");
        } else {
            customers.add(customer);
        }
    }

    /**
     * *******************************************************************************
     * GETTERS / SETTERS
     * ********************************************************************************
     */

    public List<Customer> getCustomers() {
        return customers;
    }

    /**
     * Get a customer by email if he/she exists in the list
     *
     * @param mail customer identifier
     * @return the customer if he exists in the databse
     */

    public Customer getCustomerByMail(String mail) {
        for (Customer c : customers) {
            if (c.getMail().equals(mail))
                return c;
        }
        return null;
    }

    /**
     * Get a customer by phone Number if he/she exists in the list
     *
     * @param numTel customer identifier
     * @return the customer if he exists in the databse
     */
    public Customer getCustomerByTel(String numTel) {
        for (Customer c : customers) {
            if (c.getPhoneNumber().equals(numTel))
                return c;
        }
        return null;
    }

    public List<Cookie> getCookies() {
        return cookies;
    }

    public List<Store> getStores() {
        return stores;
    }

    public void updateBestOfCookie() {
        this.bestOfNational = getBestCookie(countNationalCookie());
    }


    public Map<Cookie, Integer> countNationalCookie() {
        Map<Cookie, Integer> totalCookie = new HashMap<>();
        for (Store store : getStores()) {
            for (Order order : store.getOrderHistory().getRecentOrders()) { //TODO Nathan refacto avoid duplicate code
                for (Cookie c : order.getCookies().keySet()) {
                    if (totalCookie.containsKey(c)) {
                        int updatedQuantity = totalCookie.get(c) + order.getCookies().get(c);
                        totalCookie.replace(c, updatedQuantity);
                    } else {
                        totalCookie.put(c, order.getCookies().get(c));
                    }
                }
            }
        }
        return totalCookie;
    }

    private Cookie getBestCookie(Map<Cookie, Integer> totalCookie){
        Cookie bestCookie = null;
        for (Map.Entry<Cookie, Integer> entry : totalCookie.entrySet()) {
            if (bestCookie == null || entry.getValue() >= totalCookie.get(bestCookie)) {
                if(entry.getValue() == totalCookie.get(bestCookie))
                    bestCookie = (entry.getKey().getPrice() < bestCookie.getPrice())?entry.getKey():bestCookie; // en cas d'égalité, on renvoie le cookie le moins cher
                else bestCookie = entry.getKey();
            }
        }
        return bestCookie;
    }

    public Cookie getBestCookieNational(){
        return this.bestOfNational;
    }

}


