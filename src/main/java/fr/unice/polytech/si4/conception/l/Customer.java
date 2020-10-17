package fr.unice.polytech.si4.conception.l;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return isLoyaltyProgramn() == customer.isLoyaltyProgramn() &&
                getNbCookieOrdered() == customer.getNbCookieOrdered() &&
                getMail().equals(customer.getMail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getMail(), isLoyaltyProgramn(), getNbCookieOrdered());
    }
}
