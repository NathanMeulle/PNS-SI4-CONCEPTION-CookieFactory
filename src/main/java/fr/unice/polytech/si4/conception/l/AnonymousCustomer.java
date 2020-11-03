package fr.unice.polytech.si4.conception.l;

import fr.unice.polytech.si4.conception.l.exceptions.ErrorPreparingOrder;

import java.util.Objects;

/**
 * Class of an AnonynousCustomer
 */
public class AnonymousCustomer {

    private String phoneNumber;
    private String name;
    private Order order;

    public AnonymousCustomer(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Order createOrder(Store store){
        order = new Order();
        order.setStore(store);
        order.assignCustomer(this);
        return order;
    }


    public void makeOrder() throws ErrorPreparingOrder {
        this.order.submit();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnonymousCustomer)) return false;
        AnonymousCustomer that = (AnonymousCustomer) o;
        return Objects.equals(phoneNumber, that.phoneNumber);
    }

    void becomeMember(){
       //TODO à completer
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
