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
import static org.mockito.Mockito.mock;


public class IntegrationOrderingStepDef implements En {


    AnonymousCustomer vincent;
    Store store;
    Ingredient chocolate;
    Ingredient mnm;
    Cookie chocoCookie;
    Cookie mnMChocoCookie;
    Manager managerMock;
    CookieFactory cookieFactory;
    Kitchen kitchen;
    Cookie customCookie;


    public IntegrationOrderingStepDef() {
        Given("^An anonymous client called \"([^\"]*)\" with the phoneNumber \"([^\"]*)\"$", (String arg0, String arg1) -> {
            vincent = new AnonymousCustomer(arg0, arg1);
        });
        And("^a store located in \"([^\"]*)\" with a tax of \"([^\"]*)\"$", (String arg0, String arg1) -> {
            managerMock = mock(Manager.class);
            store = new Store(1, arg0, Double.parseDouble(arg1),"01", "mail", managerMock);
            kitchen = new Kitchen();
            kitchen.assignStore(store);
            store.setKitchen(kitchen);

            cookieFactory = CookieFactory.getInstance();
            cookieFactory.resetFactory();
            cookieFactory.addStore(store);
        });
        And("^an ingredient called Chocolate, costing (\\d+)$", (Integer arg0) -> {
            chocolate = new Ingredient("Chocolate", arg0, IngredientType.FLAVOR);
        });
        And("^an ingredient called MnMs, costing (\\d+)$", (Integer arg0) -> {
            mnm = new Ingredient("MnM", arg0, IngredientType.FLAVOR);
        });
        And("^A recipe \"([^\"]*)\" with ingredients \"([^\"]*)\"$", (String arg0, String arg1) -> {
            List<Ingredient> ingredients = new ArrayList<>();
            ingredients.add(chocolate);
            chocoCookie = new Cookie(arg0, ingredients, Mix.TOPPED, Cooking.CRUNCHY);
            cookieFactory.addCookie(chocoCookie);
        });
        And("^A recipe \"([^\"]*)\" with ingredients \"([^\"]*)\" and \"([^\"]*)\"$", (String arg0, String arg1, String arg2) -> {
            List<Ingredient> ingredients = new ArrayList<>();
            ingredients.add(chocolate);
            ingredients.add(mnm);
            mnMChocoCookie = new Cookie(arg0, ingredients, Mix.TOPPED, Cooking.CRUNCHY);
            cookieFactory.addCookie(mnMChocoCookie);
        });
        When("^Add (\\d+) Chocolate and (\\d+) MnMs to the stock at store \"([^\"]*)\"$", (Integer arg0, Integer arg2, String arg4) -> {
            store = cookieFactory.getStoreByAddress(arg4);
            kitchen.incrementStock(chocolate, arg0);
            kitchen.incrementStock(mnm, arg2);
        });
        And("^The client \"([^\"]*)\" makes an order of (\\d+) \"([^\"]*)\" and (\\d+) \"([^\"]*)\" at store \"([^\"]*)\"$", (String arg0, Integer arg1, String arg2, Integer arg3, String arg4, String arg5) -> {
            store = cookieFactory.getStoreByAddress(arg5);
            vincent.createOrder(store);
            vincent.addCookie(chocoCookie, arg1);
            vincent.addCookie(mnMChocoCookie, arg3);
            assertDoesNotThrow(()->vincent.makeOrder());
        });
        And("^The client \"([^\"]*)\" try to create an order of (\\d+) \"([^\"]*)\" and (\\d+) \"([^\"]*)\" at store \"([^\"]*)\"$", (String arg0, Integer arg1, String arg2, Integer arg3, String arg4, String arg5) -> {
            store = cookieFactory.getStoreByAddress(arg5);
            vincent.createOrder(store);
            vincent.addCookie(chocoCookie, arg1);
            vincent.addCookie(mnMChocoCookie, arg3);
            assertThrows(ErrorPreparingOrder.class, ()-> vincent.makeOrder());
        });
        Then("^The client \"([^\"]*)\" has (\\d+) order$", (String arg0, Integer arg1) -> {
            assertNotNull(vincent.getOrder());
        });
        And("^the total price of this order is now (.+) TTC for client \"([^\"]*)\"$", (Double arg0, String arg2) -> {
            assertEquals(2, chocoCookie.getPrice());
            assertEquals(6, mnMChocoCookie.getPrice());
            assertEquals(arg0, vincent.getPrice(), 0.01);
        });
        When("^The user makes an order of (\\d+) \"([^\"]*)\"$", (Integer arg0, String arg1) -> {
            vincent.createOrder(store);
            vincent.addCookie(chocoCookie, arg0);
            vincent.makeOrder();
        });
        When("^the user creates his custom recipe composed of \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\" and is TOPPED and CRUNCHY$", (String arg0, String arg1, String arg2) -> {
            List<Ingredient> ingredients = new ArrayList<>();
            Ingredient ingredient1 = new Ingredient(arg0, 3, IngredientType.FLAVOR);
            Ingredient ingredient2 = new Ingredient(arg1, 4, IngredientType.FLAVOR);
            Ingredient ingredient3 = new Ingredient(arg2, 5, IngredientType.FLAVOR);
            kitchen.incrementStock(ingredient1, 4);
            kitchen.incrementStock(ingredient2, 4);
            kitchen.incrementStock(ingredient3, 4);
            ingredients.add(ingredient1);
            ingredients.add(ingredient2);
            ingredients.add(ingredient3);
            customCookie = new Cookie("custom", ingredients, Mix.TOPPED, Cooking.CRUNCHY);

        });
        Then("^the user makes the order of his custom cookies$", () -> {
            vincent.createOrder(store);
            vincent.addCookie(customCookie, 1);
            vincent.makeOrder();
        });
        And("^the store starts making the order$", () -> {
            assertEquals(StateOrder.COOKED, vincent.getOrder().getStateOrder());
        });
    }
}
