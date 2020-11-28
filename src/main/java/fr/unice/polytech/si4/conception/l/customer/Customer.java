package fr.unice.polytech.si4.conception.l.customer;

import fr.unice.polytech.si4.conception.l.FactoryCustomerSide;
import fr.unice.polytech.si4.conception.l.ISystemInfo;
import fr.unice.polytech.si4.conception.l.exceptions.*;
import fr.unice.polytech.si4.conception.l.products.Cookie;
import fr.unice.polytech.si4.conception.l.products.composition.Cooking;
import fr.unice.polytech.si4.conception.l.products.composition.Dough;
import fr.unice.polytech.si4.conception.l.products.composition.Ingredient;
import fr.unice.polytech.si4.conception.l.products.composition.Mix;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Customer extends AnonymousCustomer implements CustomerInterface {

    private String mail;
    private boolean loyaltyProgram;
    private int nbCookieOrdered;
    private FactoryCustomerSide factoryCustomerSide;

    public Customer(String name, String phoneNumber, String mail) {
        super(name, phoneNumber);
        this.mail = mail;
        this.loyaltyProgram = false;
        this.nbCookieOrdered = 0;
    }


    @Override
    public void addCookie(Cookie cookie, int quantity) {
        super.addCookie(cookie,quantity);
        if(loyaltyProgram) incrementCookieOrdered(quantity);
        if(loyaltyProgram && nbCookieOrdered >=30) {
            decrementeNbCookie();
        }
    }


    public void incrementCookieOrdered(int nbCookieOrdered) {
        this.nbCookieOrdered += nbCookieOrdered;
    }


    public void decrementeNbCookie() {
        getOrderBuilder().applyDiscount();
        this.nbCookieOrdered = 0;
    }

    public void associateProxy(ISystemInfo iSystemInfo){
        factoryCustomerSide = (FactoryCustomerSide)iSystemInfo;
    }

    public void createCookieUsingProxy(String name, List<Ingredient> ingredientList, Dough dough, Mix mix, Cooking cooking) throws InvalidNumberIngredient, InvalidTypeIngredient, AlreadyCreatedException, NoProxyAssociateException {

        if(factoryCustomerSide == null){
            throw new NoProxyAssociateException("This customer does not have an associate proxy");
        }
        Cookie cookie = factoryCustomerSide.createCookiePersonalized(name, ingredientList, dough, mix, cooking);
        factoryCustomerSide.submitCookie(cookie);
    }

    public void pickUpOrder(Date date) throws NotAlreadyCooked, NotPaid, WrongPickUpTimeException {
        super.getOrder().pickedUp(date);
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
     * GETTERS / SETTERS
     * ********************************************************************************
     */

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setLoyaltyProgram(boolean loyaltyProgram) {
        this.loyaltyProgram = loyaltyProgram;
    }

    public int getNbCookieOrdered() {
        return nbCookieOrdered;
    }

}
