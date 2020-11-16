package fr.unice.polytech.si4.conception.l;

import fr.unice.polytech.si4.conception.l.exceptions.ErrorPreparingOrder;
import fr.unice.polytech.si4.conception.l.exceptions.NotAlreadyCooked;
import fr.unice.polytech.si4.conception.l.exceptions.NotPaid;
import fr.unice.polytech.si4.conception.l.exceptions.WrongPickUpTimeException;

import java.util.Objects;

/**
 * Class of an AnonynousCustomer
 */
public class AnonymousCustomer implements CustomerInterface{

    private String phoneNumber;
    private String name;
    private Order order;

    public AnonymousCustomer(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public void createOrder(Store store){
        this.order = new Order();
        this.order.setStore(store);
        this.order.assignAnonymousCustomer(this);
    }


    @Override
    public void addCookie(Cookie cookie, int quantity) {
        this.order.addCookie(cookie, quantity);
    }


    public void makeOrder() throws ErrorPreparingOrder {
        this.order.submit();
    }

    public void pickUpOrder() throws NotAlreadyCooked, NotPaid, WrongPickUpTimeException {
        this.order.pickedUp();
    }

    @Override
    public double getPrice() {
        return this.order.getPriceTTC();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnonymousCustomer)) return false;
        AnonymousCustomer that = (AnonymousCustomer) o;
        return Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }


    @Override
    public String toString() {
        return "AnonymousCustomer{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", name='" + name + '\'' +
                ", order=" + order +
                '}';
    }
}
