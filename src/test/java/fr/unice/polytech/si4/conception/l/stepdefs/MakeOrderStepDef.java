package fr.unice.polytech.si4.conception.l.stepdefs;

import fr.unice.polytech.si4.conception.l.*;
import fr.unice.polytech.si4.conception.l.cookie.composition.Cooking;
import fr.unice.polytech.si4.conception.l.cookie.composition.IngredientType;
import fr.unice.polytech.si4.conception.l.cookie.composition.Mix;
import fr.unice.polytech.si4.conception.l.exceptions.ErrorPreparingOrder;
import io.cucumber.java8.En;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MakeOrderStepDef implements En {
    private CookieFactory cookieFactory;
    private Manager manager;
    private Store store;
    private Cookie cookie;
    private Ingredient ingredient;
    private AnonymousCustomer anonymousCustomer;
    private Kitchen kitchen;
    private int nbIngredients;
    private int nbRecipe;
    private int nbCookies;

    public MakeOrderStepDef() {
        Given("^a manager, a factory with a store and a anonymous client$", () -> {
            manager = new Manager(1, "Gerard");
            store = new Store(1, "a", 1.0, "01", "mail.com", manager);
            List<Store> stores = new ArrayList<>();
            stores.add(store);
            cookieFactory = CookieFactory.getInstance();
            for (Store s : stores) {
                cookieFactory.addStore(s);
            }
            anonymousCustomer = new AnonymousCustomer("Philippe", "06.05.45.87.12");
            anonymousCustomer.createOrder(store);
            kitchen = store.getKitchen();
            manager.assignStore(store);
        });

        And("^a cookie named \"([^\"]*)\" with ingredient \"([^\"]*)\"$", (String arg0, String arg1) -> {
            ingredient = new Ingredient("Chocolate", 2, IngredientType.DOUGH);
            List<Ingredient> ingredients = new ArrayList<>();
            ingredients.add(ingredient);
            cookie = new Cookie("Choco", ingredients, Mix.MIXED, Cooking.CRUNCHY);
        });

        When("^the manager adds (\\d+) \"([^\"]*)\" to stock$", (Integer arg0, String arg1) -> {
            kitchen.incrementStock(ingredient, arg0);
        });

        And("^the manager asks for the number of ingredients in kitchen stock$", () -> {
            nbIngredients = kitchen.getStock().size();
        });

        Then("^There are (\\d+) Ingredient in the stock$", (Integer arg0) -> {
            assertEquals(arg0, nbIngredients);
        });

        And("^the manager asks for the number of \"([^\"]*)\" in stock$", (String arg0) -> {
            nbIngredients = kitchen.getStock().get(ingredient);
        });

        Then("^There are (\\d+) \"([^\"]*)\" in stock$", (Integer arg0, String arg1) -> {
            assertEquals(5, nbIngredients);
        });

        When("^the factory adds the new recipe \"([^\"]*)\"$", (String arg0) -> {
            cookieFactory.addCookie(cookie);
        });

        And("^the factory asks for the number of recipe$", () -> {
            nbRecipe = cookieFactory.getCookies().size();
        });

        Then("^There are (\\d+) in his number of recipe$", (Integer arg0) -> {
            assertEquals(arg0,nbRecipe);
        });

        When("^the client create an order$", () -> {
            anonymousCustomer.createOrder(store);
        });

        And("^the client add (\\d+) cookies to his order$", (Integer arg0) -> {
            anonymousCustomer.addCookie(cookie,arg0);
            Order order = anonymousCustomer.getOrder();
            nbCookies = order.getCookies().get(cookie);

        });

        Then("^There are (\\d+) in his number of cookies$", (Integer arg0) -> {
            assertEquals(arg0,nbCookies);
        });

        And("^the client valid the order$", () -> {

        });
        Then("^the kitchen does not have enough ingredients in stock to prepare the order$", () -> {
            assertThrows(ErrorPreparingOrder.class, () -> anonymousCustomer.makeOrder());
        });


        And("^manager adds (\\d+) \"([^\"]*)\"$", (Integer arg0, String arg1) -> {
            kitchen.incrementStock(ingredient, arg0);
        });
        Then("^There are (\\d+) \"([^\"]*)\"$", (Integer arg0, String arg1) -> {
            nbIngredients = kitchen.getStock().get(ingredient);
            assertEquals(arg0, nbIngredients);
        });
        And("^the client valid the order again$", () -> {

        });
        Then("^the kitchen has enough ingredients in stock to prepare the order$", () -> {
            assertDoesNotThrow(() ->anonymousCustomer.makeOrder());
        });
        Then("^the order is done$", () -> {
            Order order = anonymousCustomer.getOrder();
            assertTrue(order.getIsDone());
        });

    }
}