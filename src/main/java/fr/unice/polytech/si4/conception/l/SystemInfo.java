package fr.unice.polytech.si4.conception.l;

import fr.unice.polytech.si4.conception.l.customer.Customer;
import fr.unice.polytech.si4.conception.l.exceptions.AlreadyCreatedException;
import fr.unice.polytech.si4.conception.l.exceptions.NotFindException;
import fr.unice.polytech.si4.conception.l.products.Cookie;
import fr.unice.polytech.si4.conception.l.products.composition.Ingredient;
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
public class SystemInfo implements ISystemInfo {

    private List<Customer> customers;
    private List<Cookie> cookies;
    private List<Store> stores;
    private List<Ingredient> ingredients;
    private Cookie bestOfNational;

    private SystemInfo() {
        //Constructeur privé car SINGLETON
        this.cookies = new ArrayList<>();
        this.stores = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.ingredients = new ArrayList<>();
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
        this.ingredients.clear();
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

    public Store getStoreByAddress(String address) throws NotFindException {
        for (Store s : stores) {
            if (s.getAddress().equals(address))
                return s;
        }
        throw new NotFindException("address not find");
    }

    public void addCookie(Cookie cookie) throws AlreadyCreatedException {
        if (cookies.contains(cookie)) {
            throw new AlreadyCreatedException("Cookie already exists");
        } else {
            cookies.add(cookie);
        }
    }

    /**
     * Add a new list of ingredients in the stock of each stores.
     * @param ingredientList : the list of ingredients which we want to had into the catalogue of CookieFactory
     */
    public void addIngredient(List<Ingredient> ingredientList) {
        for (Ingredient i : ingredientList){
            if (!ingredients.contains(i)) {
                ingredients.add(i);
            }
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
     * Generate a proxy version of the CookieFactory named FactoryCustomerSide.
     * The FactoryCustomerSide give just access to the cookies, stores and ingredients
     * but within unmodifiableList
     * @return : an instance of class FactoryCustomerSide
     */
    public FactoryCustomerSide generateProxy(){
        return new FactoryCustomerSide();
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

    public Customer getCustomerByMail(String mail) throws NotFindException {
        for (Customer c : customers) {
            if (c.getMail().equals(mail))
                return c;
        }
        throw new NotFindException("mail not find");
    }

    /**
     * Get a customer by phone Number if he/she exists in the list
     *
     * @param numTel customer identifier
     * @return the customer if he exists in the databse
     */
    public Customer getCustomerByTel(String numTel) throws NotFindException {
        for (Customer c : customers) {
            if (c.getPhoneNumber().equals(numTel))
                return c;
        }
        throw new NotFindException("tel not find");
    }

    @Override
    public List<Cookie> getCookies() {
        return cookies;
    }

    public Cookie getCookieByName(String name) throws NotFindException {
        for(Cookie cookie : cookies){
            if(cookie.getName().equals(name)){
                return cookie;
            }
        }
        throw new NotFindException("cookie not found");
    }

    @Override
    public List<Store> getStores() {
        return stores;
    }

    @Override
    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void updateBestOfCookie() {
        this.bestOfNational = getBestCookie(countNationalCookie());
    }


    public Map<Cookie, Integer> countNationalCookie() {
        Map<Cookie, Integer> totalCookie = new HashMap<>();
        for (Store store : getStores()) {
            Map<Cookie, Integer> m = store.getOrderHistory().countStoreCookie(store.getOrderHistory().getRecentOrders());
            for (Cookie c : m.keySet()) {
                if (totalCookie.containsKey(c)) {
                    int updatedQuantity = totalCookie.get(c) + m.get(c);
                    totalCookie.replace(c, updatedQuantity);
                } else {
                    totalCookie.put(c, m.get(c));
                }
            }
        }
        return totalCookie;
    }

    private Cookie getBestCookie(Map<Cookie, Integer> totalCookie){
        Cookie bestCookie = null;
        for (Map.Entry<Cookie, Integer> entry : totalCookie.entrySet()) {
            if (bestCookie == null || entry.getValue() >= totalCookie.get(bestCookie)) {
                bestCookie = changeBestOf(totalCookie, bestCookie, entry);
            }
        }
        return bestCookie;
    }

    private Cookie changeBestOf(Map<Cookie, Integer> totalCookie, Cookie bestCookie, Map.Entry<Cookie, Integer> entry) {
        if(entry.getValue().equals(totalCookie.get(bestCookie))){
            double tmp = (bestCookie == null)?0: bestCookie.getPrice();
            bestCookie = (entry.getKey().getPrice() < tmp)? entry.getKey(): bestCookie; // en cas d'égalité, on renvoie le cookie le moins cher
        }
        else bestCookie = entry.getKey();
        return bestCookie;
    }

    public Cookie getBestCookieNational(){
        return this.bestOfNational;
    }

}



