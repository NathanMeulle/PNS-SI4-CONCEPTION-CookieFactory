package fr.unice.polytech.si4.conception.l;

import fr.unice.polytech.si4.conception.l.exceptions.AlreadyCreatedException;

import java.util.Currency;
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
        this.customers = new HashSet<>();
    }

    /**
     * allows the creation of a new store
     * @param store a store object
     */
    public void addStore(Store store) throws AlreadyCreatedException {
        if(stores.contains(store)){
            throw new AlreadyCreatedException("Store already exists");
        }
        else {
            stores.add(store);
        }
    }

    public void addCookie(Cookie cookie) throws AlreadyCreatedException {
        if(cookies.contains(cookie)){
            throw new AlreadyCreatedException("Cookie already exists");
        }
        else {
            cookies.add(cookie);
        }
    }

    /**
     * create and add a customer to the customer list. If it already exists, returns an exception.
     * @param name : name of the customer
     * @param phoneNumber : customer's phone number
     * @param mail : customer's e-mail
     * @throws AlreadyCreatedException : exception to indicate that the customer already exists in the customer list.
     */
    public void subscription(String name, String phoneNumber, String mail) throws AlreadyCreatedException {
        Customer customer = new Customer(name, phoneNumber, mail);
        addCustomer(customer);
    }

    /**
     * Adds a customer to the customer list. If it already exists, returns an exception.
     * @param customer  : the client to be added to the custormers list.
     * @throws AlreadyCreatedException : exception to indicate that the customer already exists in the customer list.
     */
    public void addCustomer(Customer customer) throws AlreadyCreatedException{
        if(customers.contains(customer)){
            throw new AlreadyCreatedException("Customer already exists");
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

    /**
     * Get a customer by email if he/she exists in the list
     * @param mail customer identifier
     * @return the customer if he exists in the databse
     */

    public Customer getCustomerByMail(String mail){
        for (Customer c : customers){
            if (c.getMail().equals(mail))
                return c;
        }
        return null;
    }

    /**
     * Get a customer by phone Number if he/she exists in the list
     * @param numTel customer identifier
     * @return the customer if he exists in the databse
     */
    public Customer getCustomerByTel(String numTel){
        for (Customer c : customers){
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


}
