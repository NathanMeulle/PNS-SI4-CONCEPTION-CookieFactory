package fr.unice.polytech.si4.conception.l.stepdefs;

import fr.unice.polytech.si4.conception.l.SystemInfo;
import fr.unice.polytech.si4.conception.l.customer.AnonymousCustomer;
import fr.unice.polytech.si4.conception.l.customer.Customer;
import fr.unice.polytech.si4.conception.l.exceptions.ErrorPreparingOrder;
import fr.unice.polytech.si4.conception.l.order.StateOrder;
import fr.unice.polytech.si4.conception.l.products.Cookie;
import fr.unice.polytech.si4.conception.l.products.CookieFactory;
import fr.unice.polytech.si4.conception.l.products.composition.*;
import fr.unice.polytech.si4.conception.l.store.Kitchen;
import fr.unice.polytech.si4.conception.l.store.Manager;
import fr.unice.polytech.si4.conception.l.store.Store;
import io.cucumber.java8.En;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


public class IntegrationOrderingStepDef implements En {


    AnonymousCustomer vincent;
    Customer gustave;
    Store store;
    Ingredient chocolate;
    Ingredient mnm;
    Cookie chocoCookie;
    Cookie mnMChocoCookie;
    Manager managerMock;
    SystemInfo systemInfo;
    Kitchen kitchen;
    Cookie customCookie;
    CookieFactory cookieFactory;


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

            cookieFactory = new CookieFactory();
            systemInfo = SystemInfo.getInstance();
            systemInfo.resetSystemInfo();
            systemInfo.addStore(store);

        });
        And("^an ingredient called Chocolate, costing (\\d+)$", (Integer arg0) -> {
            chocolate = new Ingredient("Chocolate", arg0, IngredientType.FLAVOR);
            systemInfo.addIngredient(List.of(chocolate));
        });
        And("^an ingredient called MnMs, costing (\\d+)$", (Integer arg0) -> {
            mnm = new Ingredient("MnM", arg0, IngredientType.TOPPING);
            systemInfo.addIngredient(List.of(mnm));
        });
        And("^A recipe \"([^\"]*)\" with ingredients \"([^\"]*)\"$", (String arg0, String arg1) -> {
            List<Ingredient> ingredients = new ArrayList<>();
            ingredients.add(chocolate);
            chocoCookie = cookieFactory.createDefaultCookie(arg0, ingredients, new Dough("plain", 1),  Mix.TOPPED, Cooking.CRUNCHY);
            systemInfo.addCookie(chocoCookie);
        });
        And("^A recipe \"([^\"]*)\" with ingredients \"([^\"]*)\" and \"([^\"]*)\"$", (String arg0, String arg1, String arg2) -> {
            List<Ingredient> ingredients = new ArrayList<>();
            ingredients.add(chocolate);
            ingredients.add(mnm);
            mnMChocoCookie = cookieFactory.createDefaultCookie(arg0, ingredients, new Dough("plain", 1),  Mix.TOPPED, Cooking.CRUNCHY);
            systemInfo.addCookie(mnMChocoCookie);
        });
        When("^Add (\\d+) Chocolate and (\\d+) MnMs to the stock at store \"([^\"]*)\"$", (Integer arg0, Integer arg2, String arg4) -> {
            store = systemInfo.getStoreByAddress(arg4);
            kitchen.incrementStock(chocolate, arg0);
            kitchen.incrementStock(mnm, arg2);
        });
        And("^The Anonymous client \"([^\"]*)\" makes an order of (\\d+) \"([^\"]*)\" and (\\d+) \"([^\"]*)\" at store \"([^\"]*)\"$", (String arg0, Integer arg1, String arg2, Integer arg3, String arg4, String arg5) -> {
            store = systemInfo.getStoreByAddress(arg5);

            vincent.createOrder(store);
            vincent.addCookie(chocoCookie, arg1);
            vincent.addCookie(mnMChocoCookie, arg3);
            assertDoesNotThrow(()->vincent.submitOrder());
        });
        And("^The Anonymous client \"([^\"]*)\" try to create an order of (\\d+) \"([^\"]*)\" and (\\d+) \"([^\"]*)\" at store \"([^\"]*)\"$", (String arg0, Integer arg1, String arg2, Integer arg3, String arg4, String arg5) -> {
            store = systemInfo.getStoreByAddress(arg5);
            vincent.createOrder(store);
            vincent.addCookie(chocoCookie, arg1);
            vincent.addCookie(mnMChocoCookie, arg3);
            assertThrows(ErrorPreparingOrder.class, ()-> vincent.submitOrder());
        });
        Then("^The Anonymous client \"([^\"]*)\" has (\\d+) order$", (String arg0, Integer arg1) -> {
            assertNotNull(vincent.getOrder());
        });
        And("^the total price of this order is now (.+) TTC for Anonymous client \"([^\"]*)\"$", (Double arg0, String arg2) -> {
            assertEquals(3, chocoCookie.getPrice());
            assertEquals(7, mnMChocoCookie.getPrice());

            assertEquals(arg0, vincent.getPrice(), 0.01);
        });
        And("^the total price of this order is now (.+) TTC for client \"([^\"]*)\"$", (Double arg0, String arg2) -> {
            assertEquals(3, chocoCookie.getPrice());
            assertEquals(7, mnMChocoCookie.getPrice());

            assertEquals(arg0, gustave.getPrice(), 0.01);
        });
        When("^The user makes an order of (\\d+) \"([^\"]*)\"$", (Integer arg0, String arg1) -> {
            vincent.createOrder(store);
            vincent.addCookie(chocoCookie, arg0);
            vincent.submitOrder();
        });
        When("^the user creates his custom recipe composed of \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\" and is TOPPED and CRUNCHY$", (String arg0, String arg1, String arg2) -> {
            List<Ingredient> ingredients = new ArrayList<>();
            Ingredient ingredient1 = new Ingredient(arg0, 3, IngredientType.FLAVOR);
            Ingredient ingredient2 = new Ingredient(arg1, 4, IngredientType.TOPPING);
            Ingredient ingredient3 = new Ingredient(arg2, 5, IngredientType.TOPPING);
            systemInfo.addIngredient(List.of(ingredient1, ingredient2, ingredient3));
            kitchen.incrementStock(ingredient1, 4);
            kitchen.incrementStock(ingredient2, 4);
            kitchen.incrementStock(ingredient3, 4);
            ingredients.add(ingredient1);
            ingredients.add(ingredient2);
            ingredients.add(ingredient3);
            customCookie = cookieFactory.createDefaultCookie("custom", ingredients, new Dough("plain", 1),  Mix.TOPPED, Cooking.CRUNCHY);

        });
        Then("^the user makes the order of his custom cookies$", () -> {
            vincent.createOrder(store);
            vincent.addCookie(customCookie, 1);
            vincent.submitOrder();
        });
        And("^the store starts making the order$", () -> {
            assertEquals(StateOrder.COOKED, vincent.getOrder().getStateOrder());
        });
        Given("^A client called \"([^\"]*)\" with the phoneNumber \"([^\"]*)\" and mail \"([^\"]*)\"$", (String arg0, String arg1, String arg2) -> {
            gustave = new Customer(arg0, arg1, arg2);
        });
        And("^The client \"([^\"]*)\" makes an order of (\\d+) \"([^\"]*)\" and (\\d+) \"([^\"]*)\" at store \"([^\"]*)\"$", (String arg0, Integer arg1, String arg2, Integer arg3, String arg4, String arg5) -> {
            store = systemInfo.getStoreByAddress(arg5);
            gustave.createOrder(store);
            gustave.addCookie(chocoCookie, arg1);
            gustave.addCookie(mnMChocoCookie, arg3);
            assertDoesNotThrow(()->gustave.submitOrder());});
        Then("^The client \"([^\"]*)\" has (\\d+) order$", (String arg0, Integer arg1) -> {
            assertNotNull(gustave.getOrder());
        });
        And("^The client \"([^\"]*)\" try to create an order of (\\d+) \"([^\"]*)\" and (\\d+) \"([^\"]*)\" at store \"([^\"]*)\"$", (String arg0, Integer arg1, String arg2, Integer arg3, String arg4, String arg5) -> {
            store = systemInfo.getStoreByAddress(arg5);
            gustave.createOrder(store);
            gustave.addCookie(chocoCookie, arg1);
            gustave.addCookie(mnMChocoCookie, arg3);
            assertThrows(ErrorPreparingOrder.class, ()-> gustave.submitOrder());
        });
    }
}
