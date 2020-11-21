package fr.unice.polytech.si4.conception.l.stepdefs;

import fr.unice.polytech.si4.conception.l.SystemInfo;
import fr.unice.polytech.si4.conception.l.customer.Customer;
import fr.unice.polytech.si4.conception.l.products.Cookie;
import fr.unice.polytech.si4.conception.l.products.composition.*;
import fr.unice.polytech.si4.conception.l.store.Kitchen;
import fr.unice.polytech.si4.conception.l.store.Manager;
import fr.unice.polytech.si4.conception.l.store.Store;
import io.cucumber.java8.En;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class CreatePersonalizedRecipeStepDef implements En {

    Customer jhon;
    Store store;
    Manager managerMock;
    SystemInfo systemInfo;
    Kitchen kitchen;
    Ingredient vanilla;
    Dough flour;
    Cookie cookieCreated;


    public CreatePersonalizedRecipeStepDef() {

        ////////////////////////////// Given //////////////////////////////

        Given("^Client named \"([^\"]*)\" with a phone number \"([^\"]*)\" and with a mail \"([^\"]*)\"$", (String arg0, String arg1, String arg2) -> {
            jhon = new Customer(arg0, arg1, arg2);
        });

        And("^a store, located in \"([^\"]*)\" with a tax of \"([^\"]*)\"$", (String arg0, String arg1) -> {
            managerMock = mock(Manager.class);
            store = new Store(1, arg0, Double.parseDouble(arg1),"01", "mail", managerMock);
            kitchen = new Kitchen();
            kitchen.assignStore(store);
            store.setKitchen(kitchen);
            systemInfo = SystemInfo.getInstance();
            systemInfo.addStore(store);
        });


        And("an ingredient called \"vanilla\" which is a \"FLAVOR\", costing 4", () -> {
            vanilla = new Ingredient("vanilla", 4, IngredientType.FLAVOR);
        });
        And("another ingredient called \"wholemeal flour\" which is a \"DOUGH\", costing 2", () -> {
            flour = new Dough("wholemeal flour", 2);
        });
        When("We add 8 vanilla and 10 wholemeal flour to the stock at store \"Avignon\"", () -> {
              store.incrementStock(vanilla, 8);
              store.incrementStock(flour, 10);
        });
        And("Jhon create his recipe named \"HeavyVanilla\" with \"CHEWY\" cooking, a \"MIXED\" mix and the ingredients vanilla and wholemeal flour",
                () -> {
            List<Ingredient> ingredientList = new ArrayList<>();
            ingredientList.add(vanilla);
            cookieCreated = systemInfo.generateProxy().createCookiePersonalized( "HeavyVanilla", ingredientList, flour, Mix.MIXED, Cooking.CHEWY);
        });
        And("the cookie \"HeavyVanilla\" is composed by the ingredients vanilla and wholemeal flour", () -> {
            assertEquals(vanilla, cookieCreated.getIngredients().get(0));
            assertEquals(flour, cookieCreated.getDough());
            assertEquals(Mix.MIXED, cookieCreated.getMix());
            assertEquals(Cooking.CHEWY, cookieCreated.getCooking());
        });

    }
}
