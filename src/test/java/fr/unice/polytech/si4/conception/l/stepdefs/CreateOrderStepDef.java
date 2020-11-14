package fr.unice.polytech.si4.conception.l.stepdefs;

import fr.unice.polytech.si4.conception.l.*;
import fr.unice.polytech.si4.conception.l.cookie.composition.Cooking;
import fr.unice.polytech.si4.conception.l.cookie.composition.Dough;
import fr.unice.polytech.si4.conception.l.cookie.composition.IngredientType;
import fr.unice.polytech.si4.conception.l.cookie.composition.Mix;
import io.cucumber.java8.En;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateOrderStepDef implements En {

    private Cookie cookie;
    private Order order;
    private int nbCookies;
    private Store store;
    private AnonymousCustomer anonymousCustomer;
    private Customer customer;

    public CreateOrderStepDef() {
        Given("^a store, an anonymous customer and a customer$", () -> {
            Manager manager = new Manager(1,"Phillipe");
            store = new Store(1,"0",1.0,"0652487564","@gmail.com",manager);
            anonymousCustomer = new AnonymousCustomer("Philippe", "06.05.45.87.12");
            customer = new Customer("Phil", "06", "phil@mail.fr");
        });

        And("^a cookie of name \"([^\"]*)\"$", (String arg0) -> {
            List<Ingredient> ingredients = new ArrayList<>();
            ingredients.add(new Ingredient("Chocolate", 2, IngredientType.FLAVOR));
            cookie = new Cookie(arg0, ingredients, new Dough("plain", 1),  Mix.MIXED, Cooking.CRUNCHY);

        });
        When("^the anonymous client create an order$", () -> {
            anonymousCustomer.createOrder(store);
        });

        Then("^There is (\\d+) in his number of cookies$", (Integer arg0) -> {
            assertEquals(arg0.intValue(),nbCookies);
        });
        When("^the anonymous client add one cookie to his order$", () -> {
            anonymousCustomer.addCookie(cookie,1);
            order = anonymousCustomer.getOrder();
            nbCookies = order.getCookies().get(cookie);
        });
        When("^the customer create an order$", () -> {
            customer.createOrder(store);
        });
        And("^the customer add (\\d+) cookie to his order$", (Integer arg0) -> {
            customer.addCookie(cookie,arg0);
            order = customer.getOrder();
            nbCookies = order.getCookies().get(cookie);
        });
    }


}
