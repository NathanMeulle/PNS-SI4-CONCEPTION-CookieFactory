package fr.unice.polytech.si4.conception.l;

import fr.unice.polytech.si4.conception.l.exceptions.AlreadyCreatedException;

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

    public void subscription(String name, String phoneNumber, String mail) throws AlreadyCreatedException {
        Customer customer = new Customer(name, phoneNumber, mail);
        addCustomer(customer);
    }

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

    public Customer getCustomerByMail(String mail){
        for (Customer c : customers){
            if (c.getMail().equals(mail))
                return c;
        }
        return null;
    }

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
