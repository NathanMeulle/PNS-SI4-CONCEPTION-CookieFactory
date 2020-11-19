package fr.unice.polytech.si4.conception.l.customer;

import fr.unice.polytech.si4.conception.l.FactoryCustomerSide;
import fr.unice.polytech.si4.conception.l.ISystemInfo;
import fr.unice.polytech.si4.conception.l.exceptions.AlreadyCreatedException;
import fr.unice.polytech.si4.conception.l.exceptions.ErrorPreparingOrder;
import fr.unice.polytech.si4.conception.l.exceptions.InvalidNumberIngredient;
import fr.unice.polytech.si4.conception.l.exceptions.InvalidTypeIngredient;
import fr.unice.polytech.si4.conception.l.order.Order;
import fr.unice.polytech.si4.conception.l.products.Cookie;
import fr.unice.polytech.si4.conception.l.products.composition.Cooking;
import fr.unice.polytech.si4.conception.l.products.composition.Dough;
import fr.unice.polytech.si4.conception.l.products.composition.Ingredient;
import fr.unice.polytech.si4.conception.l.products.composition.Mix;
import fr.unice.polytech.si4.conception.l.store.Store;

import java.util.List;
import java.util.Objects;

public class Customer extends AnonymousCustomer implements CustomerInterface {


    private String mail;
    private boolean loyaltyProgram;
    private int nbCookieOrdered;
    private Order order;
    private FactoryCustomerSide factoryCustomerSide;

    public Customer(String name, String phoneNumber, String mail) {
        super(name, phoneNumber);
        this.mail = mail;
        this.loyaltyProgram = false;
        this.nbCookieOrdered = 0;
    }

    @Override
    public void createOrder(Store store){
        this.order = new Order();
        this.order.setStore(store);
        this.order.assignCustomer(this);
    }

    @Override
    public void submitOrder() throws ErrorPreparingOrder {
        this.order.submit();
    }

    @Override
    public void addCookie(Cookie cookie, int quantity) {
        order.addCookie(cookie, quantity);
        if(loyaltyProgram) incrementCookieOrdered(quantity);
        if(loyaltyProgram && nbCookieOrdered >=30) {
            decrementeNbCookie();
        }
    }


    public void incrementCookieOrdered(int nbCookieOrdered) {
        this.nbCookieOrdered += nbCookieOrdered;
    }


    public void decrementeNbCookie() {
        this.order.applyDiscount();
        this.nbCookieOrdered = 0;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return getMail().equals(customer.getMail()) || getPhoneNumber().equals(customer.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getMail());
    }

    /**
     * *******************************************************************************
     * Cookie creation
     * ********************************************************************************
     */

    public void createCookie(ISystemInfo factoryCustomerSide, String name, List<Ingredient> ingredients, Dough dough, Mix mix, Cooking cooking) throws InvalidNumberIngredient, InvalidTypeIngredient, AlreadyCreatedException {
        if(this.factoryCustomerSide==null){
            this.factoryCustomerSide = (FactoryCustomerSide)factoryCustomerSide;
        }
        this.factoryCustomerSide.createCookiePersonalized(name, ingredients, dough, mix, cooking);
    }

    /**
     * *******************************************************************************
     * GETTERS / SETTERS
     * ********************************************************************************
     */


    public String getMail() {
        return mail;
    }

    public Order getOrder() {
        return order;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public boolean isLoyaltyProgram() {
        return loyaltyProgram;
    }

    public void setLoyaltyProgram(boolean loyaltyProgram) {
        this.loyaltyProgram = loyaltyProgram;
    }

    public int getNbCookieOrdered() {
        return nbCookieOrdered;
    }

    public void setNbCookieOrdered(int nbCookieOrdered) {
        this.nbCookieOrdered = nbCookieOrdered;
    }

    @Override
    public double getPrice() {
        return this.order.getPriceTTC();
    }

}
