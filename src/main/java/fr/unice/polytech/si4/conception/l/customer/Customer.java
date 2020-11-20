package fr.unice.polytech.si4.conception.l.customer;

import fr.unice.polytech.si4.conception.l.exceptions.NotAlreadyCooked;
import fr.unice.polytech.si4.conception.l.exceptions.NotPaid;
import fr.unice.polytech.si4.conception.l.exceptions.WrongPickUpTimeException;
import fr.unice.polytech.si4.conception.l.products.Cookie;

import java.util.Date;
import java.util.Objects;

public class Customer extends AnonymousCustomer implements CustomerInterface {

    private String mail;
    private boolean loyaltyProgram;
    private int nbCookieOrdered;

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
