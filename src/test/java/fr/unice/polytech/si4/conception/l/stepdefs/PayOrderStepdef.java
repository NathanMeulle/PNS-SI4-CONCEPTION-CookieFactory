package fr.unice.polytech.si4.conception.l.stepdefs;

import fr.unice.polytech.si4.conception.l.*;
import io.cucumber.java8.En;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PayOrderStepdef implements En {
    private Store store;
    private Kitchen kitchen;
    Cookie cookie;
    Customer customer;
    Manager manager;



    public PayOrderStepdef() {
        And("^a store$", () -> {
            manager = mock(Manager.class);
            store = new Store(1, "", 1, "", "", manager);
            kitchen = mock(Kitchen.class);
            store.setKitchen(kitchen);
            when(kitchen.canDo(any())).thenReturn(true);
        });
        When("^a client subscribe to the loyalty program$", () -> {
            customer = new Customer("vincent", "06", "mail");
            customer.setLoyaltyProgram(true);
        });
        And("^he makes an order of (\\d+) cookies$", (Integer arg0) -> {
            cookie = mock(Cookie.class);
            when(cookie.getPrice()).thenReturn(1.0);
            customer.createOrder(store);
            customer.addCookie(cookie, arg0);
            customer.makeOrder();
        });
        Then("^there is (\\d+) in the cookie counter$", (Integer arg0) -> {
            assertEquals(arg0, customer.getNbCookieOrdered());
        });
        When("^a client subscribe or not to the \"([^\"]*)\"$", (String arg0) -> {
            customer = new Customer("vincent", "06", "mail");
            if(arg0.equals("yes"))
                customer.setLoyaltyProgram(true);
            else
                customer.setLoyaltyProgram(false);

        });
        And("^he makes an order of \"([^\"]*)\" cookie costing \"([^\"]*)\" at a store with \"([^\"]*)\"$", (Integer arg0, Double price, Double tax) -> {
            store.setTax(tax);
            cookie = mock(Cookie.class);
            when(cookie.getPrice()).thenReturn(price);
            customer.createOrder(store);
            customer.addCookie(cookie, arg0);
            customer.makeOrder();
        });
        Then("^he must pay \"([^\"]*)\"$", (Double arg0) -> {
            assertEquals(arg0, customer.getPrice(), 0.01);
        });
        And("^there is \"([^\"]*)\" cookies$", (Integer arg0) -> {
            assertEquals(arg0, customer.getNbCookieOrdered());
        });

    }
}
