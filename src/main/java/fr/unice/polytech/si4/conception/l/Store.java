package fr.unice.polytech.si4.conception.l;

import fr.unice.polytech.si4.conception.l.util.schedule.Schedule;

import java.util.HashMap;
import java.util.Objects;

public class Store {

    final private int id;
    final private String address;
    private double tax;
    private String phone;
    private String mail;
    private Manager manager;
    private Schedule schedule;
    private Kitchen kitchen;

    public Store(int id, String address, double tax, String phone, String mail, Manager manager) {
        this.id = id;
        this.address = address;
        this.tax = tax;
        this.phone = phone;
        this.mail = mail;
        this.manager = manager;
        this.manager.assignStore(this);
        this.schedule = new Schedule();
        this.kitchen = new Kitchen();
        this.kitchen.assignStore(this);
    }

    /** ********************************************************************************
     *                               GETTERS / SETTERS
     *  ********************************************************************************
     */
    public int getId() {
        return id;
    }
    public String getAddress() {
        return address;
    }
    public double getTax() {
        return tax;
    }
    public String getPhone() {
        return phone;
    }
    public String getMail() {
        return mail;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Store)) return false;
        Store store = (Store) o;
        return getId() == store.getId() &&
                Double.compare(store.getTax(), getTax()) == 0 &&
                getAddress().equals(store.getAddress()) &&
                getPhone().equals(store.getPhone()) &&
                getMail().equals(store.getMail()) &&
                manager.equals(store.manager) &&
                schedule.equals(store.schedule);
    }

    boolean achievableCookie(HashMap<Cookie, Integer> cookies){
        return this.kitchen.canDo(cookies);
    }

    void prepareOrder(Order order){
        this.kitchen.prepareCookies(order.getCookies());
        notify(order.getAnonymousCustomer());
    }

    void notify(AnonymousCustomer anonymousCustomer){
        Log.add(" An email was sent to "+ anonymousCustomer.getName() +" to come and collect his order. ");
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAddress(), getTax(), getPhone(), getMail(), manager, schedule);
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Kitchen getKitchen() {
        return kitchen;
    }

    public void setKitchen(Kitchen kitchen) {
        this.kitchen = kitchen;
    }
}
