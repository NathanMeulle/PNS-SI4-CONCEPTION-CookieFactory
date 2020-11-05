package fr.unice.polytech.si4.conception.l;

import fr.unice.polytech.si4.conception.l.exceptions.ErrorPreparingOrder;

import java.util.Objects;

public class Customer extends AnonymousCustomer{


    private String mail;
    private boolean loyaltyProgramn;
    private int nbCookieOrdered;

    public Customer(String name, String phoneNumber, String mail) {
        super(name,phoneNumber);
        this.mail = mail;
        this.loyaltyProgramn = false;
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

    /** ********************************************************************************
     *                               GETTERS / SETTERS
     *  ********************************************************************************
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
