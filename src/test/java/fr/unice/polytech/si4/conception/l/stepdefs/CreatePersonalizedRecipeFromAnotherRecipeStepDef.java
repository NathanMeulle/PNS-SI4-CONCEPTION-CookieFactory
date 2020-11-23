package fr.unice.polytech.si4.conception.l.stepdefs;

import fr.unice.polytech.si4.conception.l.SystemInfo;
import fr.unice.polytech.si4.conception.l.customer.Customer;
import fr.unice.polytech.si4.conception.l.exceptions.NotContainsIngredient;
import fr.unice.polytech.si4.conception.l.products.Cookie;
import fr.unice.polytech.si4.conception.l.products.CookieFactory;
import fr.unice.polytech.si4.conception.l.products.DefaultCookie;
import fr.unice.polytech.si4.conception.l.products.composition.*;
import fr.unice.polytech.si4.conception.l.store.Kitchen;
import fr.unice.polytech.si4.conception.l.store.Manager;
import fr.unice.polytech.si4.conception.l.store.Store;
import io.cucumber.java8.En;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class CreatePersonalizedRecipeFromAnotherRecipeStepDef implements En {
    Customer bob;
    Store store;
    Manager managerMock;
    CookieFactory cookieFactory;
    SystemInfo systemInfo;
    Kitchen kitchen;
    Ingredient vanilla;
    Ingredient milk;
    Dough chocolate;
    Cookie cookieCreated;
    Cookie cookie;
    
    public CreatePersonalizedRecipeFromAnotherRecipeStepDef() {
        Given("^Client named \"([^\"]*)\" with a phone number \"([^\"]*)\" and with mail \"([^\"]*)\"$", (String arg0, String arg1, String arg2) -> {
            bob = new Customer(arg0, arg1, arg2);
            cookieFactory = new CookieFactory();
        });
        And("^store located in \"([^\"]*)\" with a tax of \"([^\"]*)\"$", (String arg0, String arg1) -> {
            managerMock = mock(Manager.class);
            store = new Store(1, arg0, Double.parseDouble(arg1),"01", "mail", managerMock);
            kitchen = new Kitchen();
            kitchen.assignStore(store);
            store.setKitchen(kitchen);
            systemInfo = SystemInfo.getInstance();
            systemInfo.addStore(store);
        });
        And("^an ingredient \"([^\"]*)\" and an ingredient \"([^\"]*)\" and another ingredient \"([^\"]*)\"$", (String arg0, String arg1, String arg2) -> {
            vanilla = new Ingredient(arg0, 4, IngredientType.FLAVOR);
            chocolate = new Dough(arg1, 3);
            milk = new Ingredient(arg2, 5, IngredientType.TOPPING);

        });
        And("^a recipe of cookie named \"([^\"]*)\" composed by \"([^\"]*)\" and \"([^\"]*)\"$", (String arg0, String arg1, String arg2) -> {
            List<Ingredient> ingredients = new ArrayList<>();
            ingredients.add(vanilla);
            cookie = cookieFactory.createDefaultCookie(arg0, ingredients, chocolate,  Mix.MIXED, Cooking.CRUNCHY);
        });
        When("^Bob create his recipe named \"([^\"]*)\" from the recipe \"([^\"]*)\"$", (String arg0, String arg1) -> {
            systemInfo.generateProxy().createCookiePersonalizedFromAnotherCookie(cookie);
            cookieCreated = systemInfo.generateProxy().validCookie();
        });

        And("^the cookie \"([^\"]*)\" is composed by same ingredients of \"([^\"]*)\"$", (String arg0, String arg1) -> {
            assertTrue(cookieCreated.getIngredients().contains(vanilla));
            assertEquals(chocolate, cookieCreated.getDough());
            assertEquals(Mix.MIXED, cookieCreated.getMix());
            assertEquals(Cooking.CRUNCHY, cookieCreated.getCooking());
        });
        And("^Bob can't retire the ingredient \"([^\"]*)\" to the recipe \"([^\"]*)\"$", (String arg0, String arg1) -> {
            //assertThrows(NotContainsIngredient.class, () -> cookieCreated.);
        });
        Then("^Bob add the ingredient \"([^\"]*)\" to \"([^\"]*)\"$", (String arg0, String arg1) -> {
            cookieCreated.getIngredients().add(milk);
        });
        And("^\"([^\"]*)\" contains \"([^\"]*)\"$", (String arg0, String arg1) -> {
            assertTrue(cookieCreated.getIngredients().contains(milk));
        });
        Then("^Bob retire the ingredient \"([^\"]*)\" from \"([^\"]*)\"$", (String arg0, String arg1) -> {
            //cookieCreated.retire(vanilla);
            assertFalse(cookieCreated.getIngredients().contains(vanilla));
        });
    }
}
