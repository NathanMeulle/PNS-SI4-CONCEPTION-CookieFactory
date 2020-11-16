package fr.unice.polytech.si4.conception.l;

import fr.unice.polytech.si4.conception.l.exceptions.ErrorPreparingOrder;
import fr.unice.polytech.si4.conception.l.exceptions.NotAlreadyCooked;
import fr.unice.polytech.si4.conception.l.exceptions.NotPaid;
import fr.unice.polytech.si4.conception.l.exceptions.WrongPickUpTimeException;

import java.util.Objects;

public class Customer extends AnonymousCustomer implements CustomerInterface {


    private String mail;
    private boolean loyaltyProgram;
    private int nbCookieOrdered;
    private Order order;

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
    public void makeOrder() throws ErrorPreparingOrder {
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

    public void pickUpOrder() throws NotAlreadyCooked, NotPaid, WrongPickUpTimeException {
        this.order.pickedUp();
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

    @Override
    public String toString() {
        return "Customer{" +
                "mail='" + mail + '\'' +
                ", loyaltyProgramn=" + loyaltyProgram +
                ", nbCookieOrdered=" + nbCookieOrdered +
                '}';
    }
}
