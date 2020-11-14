package fr.unice.polytech.si4.conception.l.stepdefs;

import fr.unice.polytech.si4.conception.l.*;
import fr.unice.polytech.si4.conception.l.cookie.composition.Cooking;
import fr.unice.polytech.si4.conception.l.cookie.composition.IngredientType;
import fr.unice.polytech.si4.conception.l.cookie.composition.Mix;
import io.cucumber.java8.En;

import java.util.ArrayList;
import java.util.List;

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
    CookieFactory cookieFactory;
    Cookie cookieChoco;
    Cookie cookieVanilla;
    Ingredient chocolate;
    Ingredient vanilla;
    List ingredients1;
    List ingredients2;



    public PayOrderStepdef() {
        Given("^a new cookieFactory$", () -> {
            cookieFactory = CookieFactory.getInstance();
            cookieFactory.resetFactory();
        });

        And("^a store$", () -> {
            manager = mock(Manager.class);
            store = new Store(1, "", 1, "", "", manager);
            kitchen = mock(Kitchen.class);
            store.setKitchen(kitchen);
            when(kitchen.canDo(any())).thenReturn(true);
            cookieFactory.addStore(store);
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

        When("^an order of (\\d+) cookie choco and (\\d+) cookie vanilla$", (Integer arg0, Integer arg1) -> {
            customer = new Customer("vincent", "06", "mail");

            chocolate = new Ingredient("Chocolate", 1, IngredientType.FLAVOR);
            ingredients1 = new ArrayList<>();
            ingredients1.add(chocolate);
            cookieChoco = new Cookie("Choco", ingredients1, Mix.TOPPED, Cooking.CRUNCHY);

            vanilla = new Ingredient("Vanilla", 1, IngredientType.FLAVOR);
            ingredients2 = new ArrayList<>();
            ingredients2.add(chocolate);
            cookieVanilla = new Cookie("Vanilla", ingredients2, Mix.TOPPED, Cooking.CRUNCHY);

            customer.createOrder(store);
            customer.addCookie(cookieChoco, arg0);
            customer.addCookie(cookieVanilla, arg1);
            customer.makeOrder();
            store.addToOrderHistory(customer.getOrder());

        });
        Then("^the customer pay \"([^\"]*)\" euros$", (Double arg0) -> {
            assertEquals(arg0, customer.getPrice());
        });
        And("^the cookiFactory update the bestOf$", () -> {
            cookieFactory.updateBestOfCookie();
        });
        Then("^cookie choco is the bestOfCookie$", () -> {
            assertEquals(cookieChoco, cookieFactory.getBestCookieNational());
        });


    }
}
