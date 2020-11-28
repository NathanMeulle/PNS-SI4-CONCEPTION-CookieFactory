package fr.unice.polytech.si4.conception.l.customer;

import fr.unice.polytech.si4.conception.l.exceptions.ErrorPreparingOrder;
import fr.unice.polytech.si4.conception.l.exceptions.NotAlreadyCooked;
import fr.unice.polytech.si4.conception.l.exceptions.NotPaid;
import fr.unice.polytech.si4.conception.l.exceptions.WrongPickUpTimeException;
import fr.unice.polytech.si4.conception.l.marcel.eat.NoDeliveryManDispo;
import fr.unice.polytech.si4.conception.l.order.Order;
import fr.unice.polytech.si4.conception.l.products.Cookie;
import fr.unice.polytech.si4.conception.l.store.Store;

import java.util.Date;
import java.util.Objects;

/**
 * Class of an Anonymous Customer
 */
public class AnonymousCustomer implements CustomerInterface {


    private String phoneNumber;
    private String name;
    private Order.OrderBuilder orderBuilder;
    private Order order;

    public AnonymousCustomer(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public void createOrder(Store store){
        this.orderBuilder = new Order.OrderBuilder(store).assignCustomer(this);
    }

    @Override
    public void addCookie(Cookie cookie, int quantity) {
        this.orderBuilder.addCookie(cookie, quantity);
    }

    public void assignAddress(String address) {
        this.getOrderBuilder().assignDeliveryAddress(address);
    }

    public void submitOrder() throws ErrorPreparingOrder, NoDeliveryManDispo {
        this.order = this.orderBuilder.build();
        this.submit();
    }

    public void submit() throws ErrorPreparingOrder, NoDeliveryManDispo {
        this.order.submit();
    }

    public void pickUpOrder(Date date) throws NotAlreadyCooked, NotPaid, WrongPickUpTimeException {
        this.order.pickedUp(date);
    }

    @Override
    public double getPrice() {
        return this.orderBuilder.getPriceTTC();
    }

    public void changeStore(Store store){
        this.order.setStore(store);
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

    public Order.OrderBuilder getOrderBuilder() {
        return orderBuilder;
    }


}
