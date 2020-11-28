package fr.unice.polytech.si4.conception.l.stepdefs;

import fr.unice.polytech.si4.conception.l.SystemInfo;
import fr.unice.polytech.si4.conception.l.customer.Customer;
import fr.unice.polytech.si4.conception.l.exceptions.InvalidTypeIngredient;
import fr.unice.polytech.si4.conception.l.products.Cookie;
import fr.unice.polytech.si4.conception.l.products.CookieFactory;
import fr.unice.polytech.si4.conception.l.products.CookieType;
import fr.unice.polytech.si4.conception.l.products.composition.*;
import fr.unice.polytech.si4.conception.l.store.Kitchen;
import fr.unice.polytech.si4.conception.l.store.Manager;
import fr.unice.polytech.si4.conception.l.store.Store;
import io.cucumber.java8.En;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class CreatePersonalizedRecipeStepDef implements En {

    Customer jhon;
    Store store;
    Manager managerMock;
    SystemInfo systemInfo;
    Kitchen kitchen;
    Ingredient vanilla;
    Ingredient chocolate;
    Dough flour;
    Cookie cookieCreated;
    Cookie selectedCookie;


    public CreatePersonalizedRecipeStepDef() {

        Given("^Client named \"([^\"]*)\" with a phone number \"([^\"]*)\" and with a mail \"([^\"]*)\"$", (String arg0, String arg1, String arg2) -> {
            jhon = new Customer(arg0, arg1, arg2);
        });

        And("^a store, located in \"([^\"]*)\" with a tax of \"([^\"]*)\"$", (String arg0, String arg1) -> {
            managerMock = mock(Manager.class);
            store = new Store(1, arg0, Double.parseDouble(arg1),"01", "mail",0,0, managerMock);
            kitchen = new Kitchen();
            kitchen.assignStore(store);
            store.setKitchen(kitchen);
            systemInfo = SystemInfo.getInstance();
            systemInfo.resetSystemInfo();
            systemInfo.addStore(store);
            jhon.associateProxy(systemInfo.generateProxy());
        });
        And("an ingredient called \"vanilla\" which is a \"FLAVOR\", costing 4", () -> {
            vanilla = new Ingredient("vanilla", 4, IngredientType.FLAVOR);
        });
        And("an ingredient called \"chocolate\" which is a \"TOPPING\", costing 4", () -> {
            chocolate = new Ingredient("chocolate", 4, IngredientType.TOPPING);
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
            systemInfo.addIngredient(ingredientList);
            jhon.createCookieUsingProxy("HeavyVanilla", ingredientList, flour, Mix.MIXED, Cooking.CHEWY);
        });
        And("the cookie \"HeavyVanilla\" is composed by the ingredients vanilla and wholemeal flour", () -> {
            Cookie cookieCreated = systemInfo.getCookieByName("HeavyVanilla");
            assertEquals(vanilla, cookieCreated.getIngredients().get(0));
            assertEquals(flour, cookieCreated.getDough());
            assertEquals(Mix.MIXED, cookieCreated.getMix());
            assertEquals(Cooking.CHEWY, cookieCreated.getCooking());
        });
        And("^Jhon select the cookie named \"([^\"]*)\" with \"([^\"]*)\" cooking, a \"([^\"]*)\" mix and the ingredients vanilla and wholemeal flour$", (String arg0, String arg1, String arg2) -> {
            List<Ingredient> ingredientList = new ArrayList<>();
            ingredientList.add(vanilla);
            systemInfo.resetSystemInfo();
            systemInfo.addIngredient(ingredientList);
            systemInfo.addCookie(new CookieFactory().createDefaultCookie("HeavyVanilla",ingredientList, flour, Mix.MIXED, Cooking.CHEWY));
            selectedCookie = systemInfo.getCookieByName("HeavyVanilla");
        });
        Then("^the cookie is a default Cookie$", () -> {
            assertEquals(CookieType.DEFAULT, selectedCookie.getCookieType());
        });
        And("^Jhon add the ingredient called \"([^\"]*)\"$", (String arg0) -> {
            systemInfo.addIngredient(List.of(chocolate));
            cookieCreated = systemInfo.generateProxy().addIngredientToCookie(selectedCookie, chocolate);
        });
        And("^the cookie \"([^\"]*)\" is composed by the ingredients vanilla, chocolate and wholemeal flour$", (String arg0) -> {
            assertEquals(vanilla, cookieCreated.getIngredients().get(0));
            assertEquals(chocolate, cookieCreated.getIngredients().get(1));
            assertEquals(flour, cookieCreated.getDough());
            assertEquals(Mix.MIXED, cookieCreated.getMix());
            assertEquals(Cooking.CHEWY, cookieCreated.getCooking());
        });
        Then("^the cookie is a personnalized Cookie$", () -> {
            assertEquals(CookieType.PERSONNALIZED, cookieCreated.getCookieType());
        });

        And("^Jhon select the cookie named \"([^\"]*)\" with \"([^\"]*)\" cooking, a \"([^\"]*)\" mix and the ingredients vanilla, chocolate and wholemeal flour$", (String arg0, String arg1, String arg2) -> {
            List<Ingredient> ingredientList = new ArrayList<>();
            ingredientList.add(vanilla);
            ingredientList.add(chocolate);
            systemInfo.addIngredient(ingredientList);
            systemInfo.addCookie(new CookieFactory().createDefaultCookie("HeavyVanilla",ingredientList, flour, Mix.MIXED, Cooking.CHEWY));
            selectedCookie = systemInfo.getCookieByName("HeavyVanilla");
        });
        And("^Jhon remove the ingredient called \"([^\"]*)\"$", (String arg0) -> {
            cookieCreated = systemInfo.generateProxy().removeIngredientToCookie(selectedCookie, chocolate);
        });

        Then("^Jhon add the ingredient called \"([^\"]*)\" and an error occured$", (String arg0) -> {
            assertThrows(InvalidTypeIngredient.class, () -> systemInfo.generateProxy().addIngredientToCookie(selectedCookie, new Ingredient("banana", 0, IngredientType.TOPPING)));
        });

    }
}
