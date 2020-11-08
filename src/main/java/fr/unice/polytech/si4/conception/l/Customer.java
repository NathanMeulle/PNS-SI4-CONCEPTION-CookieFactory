package fr.unice.polytech.si4.conception.l;

import fr.unice.polytech.si4.conception.l.exceptions.ErrorPreparingOrder;
import fr.unice.polytech.si4.conception.l.exceptions.NotAlreadyCooked;
import fr.unice.polytech.si4.conception.l.exceptions.NotPaid;

import java.util.Objects;

public class Customer extends AnonymousCustomer implements DiscountObserver {


    private String mail;
    private boolean loyaltyProgramn;
    private int nbCookieOrdered;
    private Order order;

    public Customer(String name, String phoneNumber, String mail) {
        super(name, phoneNumber);
        this.mail = mail;
        this.loyaltyProgramn = false;
        this.nbCookieOrdered = 0;
    }

    public Order createOrder(Store store){
        order = new Order();
        order.setStore(store);
        order.assignCustomer(this);
        return order;
    }

    public void makeOrder() throws ErrorPreparingOrder {
        this.order.submit();
        if(loyaltyProgramn)
            incrementCookieOrdered(this.order.getNbCookies());
    }

    public void pickUpOrder() throws NotAlreadyCooked, NotPaid {
        this.order.pickedUp();
    }

    public void incrementCookieOrdered(int nbCookieOrdered) {
        this.nbCookieOrdered += nbCookieOrdered;
    }


    @Override
    public void update() {
        if (loyaltyProgramn) {
            this.order.applyDiscount();
            nbCookieOrdered-=30;
        }
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

    public boolean isLoyaltyProgramn() {
        return loyaltyProgramn;
    }

    public void setLoyaltyProgramn(boolean loyaltyProgramn) {
        this.loyaltyProgramn = loyaltyProgramn;
    }

    public int getNbCookieOrdered() {
        return nbCookieOrdered;
    }

    public void setNbCookieOrdered(int nbCookieOrdered) {
        this.nbCookieOrdered = nbCookieOrdered;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "mail='" + mail + '\'' +
                ", loyaltyProgramn=" + loyaltyProgramn +
                ", nbCookieOrdered=" + nbCookieOrdered +
                '}';
    }
}
