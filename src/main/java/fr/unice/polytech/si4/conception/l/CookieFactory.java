package fr.unice.polytech.si4.conception.l;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author nathan
 * Class containing our company, regrouping our stores, cookies, customers and managers
 */
public class CookieFactory {

    private Set<Customer> customers;
    private List<Cookie> cookies;
    private List<Store> stores;

    public CookieFactory(List<Cookie> cookies, List<Store> stores) {
        this.cookies = cookies;
        this.stores = stores;
    }

    /**
     * allows the creation of a new store
     * @param store a store object
     */
    public void addStore(Store store){
        if(stores.contains(store)){
            throw new IllegalArgumentException("Store already exists");
        }
        else {
            stores.add(store);
        }
    }

    public void addCookie(Cookie cookie){
        if(cookies.contains(cookie)){
            throw new IllegalArgumentException("Cookie already exists");
        }
        else {
            cookies.add(cookie);
        }
    }

    public void addCustomer(Customer customer){
        if(customers.contains(customer)){
            throw new IllegalArgumentException("Customer already exists");
        }
        else {
            customers.add(customer);
        }
    }

    /** ********************************************************************************
     *                               GETTERS / SETTERS
     *  ********************************************************************************
     */

    public Set<Customer> getCustomers() {
        return customers;
    }

    public List<Cookie> getCookies() {
        return cookies;
    }

    public List<Store> getStores() {
        return stores;
    }
}
