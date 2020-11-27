package fr.unice.polytech.si4.conception.l.store;

import fr.unice.polytech.si4.conception.l.Log;
import fr.unice.polytech.si4.conception.l.SystemInfo;
import fr.unice.polytech.si4.conception.l.customer.AnonymousCustomer;
import fr.unice.polytech.si4.conception.l.marcel.eat.MarcelEat;
import fr.unice.polytech.si4.conception.l.order.Order;
import fr.unice.polytech.si4.conception.l.order.StateOrder;
import fr.unice.polytech.si4.conception.l.products.Cookie;
import fr.unice.polytech.si4.conception.l.products.composition.Ingredient;
import fr.unice.polytech.si4.conception.l.store.schedule.Schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Store {

    private final int id;
    private final  String address;
    private double tax;
    private String phone;
    private String mail;
    private Manager manager;
    private Schedule schedule;
    private Kitchen kitchen;
    private OrderHistory orderHistory;
    private Cookie bestOfStore;
    private double latitude;
    private double longitude;
    private final double earthRadius = 6371;

    public Store(int id, String address, double tax, String phone, String mail,double latitude, double longitude, Manager manager) {
        this.id = id;
        this.address = address;
        this.tax = tax;
        this.phone = phone;
        this.mail = mail;
        this.manager = manager;
        this.latitude = latitude;
        this.longitude = longitude;
        this.manager.assignStore(this);
        this.schedule = new Schedule();
        this.kitchen = new Kitchen();
        this.kitchen.assignStore(this);
        this.orderHistory = new OrderHistory();
        this.bestOfStore = null;

    }

    public void addToOrderHistory(Order order) {
        this.orderHistory.addOrder(order);
        this.bestOfStore = orderHistory.getBestCookieStore();
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

    public boolean achievableCookie(Map<Cookie, Integer> cookies){
        return this.kitchen.canDo(cookies);
    }

    /**
     * The store notify the kitchen to cook an Order
     * Once it's done Order state pass to Cooked
     * The store notify the client
     * @param order
     */
    public void prepareOrder(Order order){
        this.kitchen.prepareCookies(order.getCookies());
        order.setStateOrder(StateOrder.COOKED);
        notify(order.getCustomer());
    }

    void notify(AnonymousCustomer anonymousCustomer){
        Log.add(" A sms was sent to "+ anonymousCustomer.getName() +" to come and collect his order. ");
    }

    /**
     * Increment the stock of the kitchen with the ingredient in param
     * @param ingredient : ingredient to increment
     * @param n : number of ingredient
     */
    public void incrementStock(Ingredient ingredient, int n){
        kitchen.incrementStock(ingredient, n);
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

    public Map<Ingredient, Integer> getStock() {

        return kitchen.getStock();
    }



    public void setOrderHistory(OrderHistory orderHistory) {
        this.orderHistory = orderHistory;
    }

    public void setKitchen(Kitchen kitchen) {
        this.kitchen = kitchen;
    }

    public OrderHistory getOrderHistory() {
        return this.orderHistory;
    }


    public Cookie getBestCookie() {
        return this.bestOfStore;
    }


    public void payMarcelEat(Order order, double price) {
        MarcelEat.pay(order, price);
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude(){
        return longitude;
    }

    public void setLatitude(double latitude){
        this.latitude = latitude;
    }

    public void setLongitude(double longitude){
        this.longitude = longitude;
    }

    public double getDistance(Store store){
        return Math.acos(Math.sin(store.getLatitude() * Math.PI / 180.0) * Math.sin(this.latitude * Math.PI / 180.0) +
                Math.cos(store.latitude * Math.PI / 180.0) * Math.cos(this.latitude * Math.PI / 180.0) *
                        Math.cos((this.longitude - store.longitude) * Math.PI / 180.0)) * this.earthRadius;
    }

    public List<Store> storesNearby(){
        List<Store> stores = SystemInfo.getInstance().getStores();
        stores.remove(this);
        List<Store> storesNearby = new ArrayList<>();
        double minDistance = 0;
        while(!stores.isEmpty()){
            minDistance = getDistance(stores.get(0));
            Store store = stores.get(0);
            double distance = 0;
            for (Store s : stores) {
                distance = getDistance(s);
                if (!this.equals(s) && distance < minDistance) { minDistance = distance; store = s; }
            }
            stores.remove(store);
            storesNearby.add(store);
        }
        return storesNearby;
    }
}
