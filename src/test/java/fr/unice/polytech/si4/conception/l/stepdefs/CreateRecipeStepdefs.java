package fr.unice.polytech.si4.conception.l.stepdefs;

import fr.unice.polytech.si4.conception.l.SystemInfo;
import fr.unice.polytech.si4.conception.l.exceptions.AlreadyCreatedException;
import fr.unice.polytech.si4.conception.l.products.Cookie;
import fr.unice.polytech.si4.conception.l.products.CookieFactory;
import fr.unice.polytech.si4.conception.l.products.composition.*;
import fr.unice.polytech.si4.conception.l.store.Manager;
import fr.unice.polytech.si4.conception.l.store.Store;
import io.cucumber.java8.En;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateRecipeStepdefs implements En {

    private SystemInfo systemInfo;
    private CookieFactory cookieFactory;
    private Cookie cookie;
    private int nbCookies;

    public CreateRecipeStepdefs() {

        Given("^a factory with a store$", () -> {

            Manager manager = new Manager(1, "Nathan");
            cookieFactory = new CookieFactory();
            Store store = new Store(1, "a", 1.0, "01", "mail.com", manager);
            manager.assignStore(store);
            List<Store> stores = new ArrayList<>();
            stores.add(store);
            systemInfo = systemInfo.getInstance();
            for (Store s : stores) {
                systemInfo.addStore(s);

            }
        });

        And("^a recipe of a cookie named \"([^\"]*)\"$", (String arg0) -> {
            List<Ingredient> ingredients = new ArrayList<>();
            ingredients.add(new Ingredient("Chocolate", 2, IngredientType.FLAVOR));
            cookie = cookieFactory.createDefaultCookie(arg0, ingredients, new Dough("plain", 1),  Mix.MIXED, Cooking.CRUNCHY);
        });
        When("^the factory requests his number of recipe$", () -> {
            nbCookies = systemInfo.getCookies().size();
        });
        Then("^There is (\\d+) in his number of recipe$", (Integer arg0) -> {
            assertEquals(arg0.intValue(),nbCookies);
        });
        When("^the factory adds the recipe \"([^\"]*)\"$", (String arg0) -> {
            systemInfo.addCookie(cookie);
        });
        And("^The cookie \"([^\"]*)\" can't be add a second time$", (String arg0) -> {
            assertThrows(AlreadyCreatedException.class, () -> systemInfo.addCookie(cookie));

        });
    }
}
