package fr.unice.polytech.si4.conception.l.stepdefs;

import fr.unice.polytech.si4.conception.l.*;
import fr.unice.polytech.si4.conception.l.cookie.composition.Cooking;
import fr.unice.polytech.si4.conception.l.cookie.composition.IngredientType;
import fr.unice.polytech.si4.conception.l.cookie.composition.Mix;
import fr.unice.polytech.si4.conception.l.exceptions.AlreadyCreatedException;
import io.cucumber.java8.En;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateRecipeStepdefs implements En {

    private CookieFactory cookieFactory;
    private Cookie cookie;
    private int nbCookies;

    public CreateRecipeStepdefs() {

        Given("^a factory with a store$", () -> {
            Manager manager = new Manager(1, "Nathan");
            Store store = new Store(1, "a", 1.0, "01", "mail.com", manager);
            manager.assignStore(store);
            List<Store> stores = new ArrayList<>();
            stores.add(store);
            cookieFactory = CookieFactory.getInstance();
            for (Store s : stores) {
                cookieFactory.addStore(s);
            }
        });

        And("^a recipe of a cookie named \"([^\"]*)\"$", (String arg0) -> {
            List<Ingredient> ingredients = new ArrayList<>();
            ingredients.add(new Ingredient("Chocolate", 2, IngredientType.FLAVOR));
            cookie = new Cookie(arg0, ingredients, Mix.MIXED, Cooking.CRUNCHY);
        });
        When("^the factory requests his number of recipe$", () -> {
            nbCookies = cookieFactory.getCookies().size();
        });
        Then("^There is (\\d+) in his number of recipe$", (Integer arg0) -> {
            assertEquals(arg0.intValue(),nbCookies);
        });
        When("^the factory adds the recipe \"([^\"]*)\"$", (String arg0) -> {
            cookieFactory.addCookie(cookie);
        });
        And("^The cookie \"([^\"]*)\" can't be add a second time$", (String arg0) -> {
            assertThrows(AlreadyCreatedException.class, () -> cookieFactory.addCookie(cookie));

        });
    }
}
