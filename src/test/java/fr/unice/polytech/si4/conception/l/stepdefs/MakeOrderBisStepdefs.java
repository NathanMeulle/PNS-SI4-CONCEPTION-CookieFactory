package fr.unice.polytech.si4.conception.l.stepdefs;

import fr.unice.polytech.si4.conception.l.*;
import fr.unice.polytech.si4.conception.l.cookie.composition.IngredientType;
import fr.unice.polytech.si4.conception.l.exceptions.ErrorPreparingOrder;
import io.cucumber.java8.En;
import org.mockito.internal.matchers.NotNull;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MakeOrderBisStepdefs implements En {

    private AnonymousCustomer anonymousCustomer;
    private Order order;
    private Cookie cookieM;
    private Store store;
    private Kitchen kitchen;
    private Exception exception;
    private Ingredient chocolate;


    public MakeOrderBisStepdefs() {
        Given("^an order with (\\d+) \"([^\"]*)\" cookies, an anonymous customer, a kitchen$", (Integer arg0, String arg1) -> {
            kitchen = new Kitchen();
            anonymousCustomer = new AnonymousCustomer("Gilbert", "06666666");
            chocolate = new Ingredient(arg1, 1, IngredientType.FLAVOR);

            List<Ingredient> ingredients = new ArrayList<>();
            ingredients.add(chocolate);
            cookieM = mock(Cookie.class);
            when(cookieM.getName()).thenReturn(arg1);
            when(cookieM.getIngredients()).thenReturn(ingredients);
            store = new Store(1, "oui", 25, "06", "", mock(Manager.class));
            store.setKitchen(kitchen);
            kitchen.assignStore(store);

            order = anonymousCustomer.createOrder(store);
            order.addCookie(cookieM, 3);

        });
        When("^an anonymous customer submit his order and (\\d+) \"([^\"]*)\" in the kitchen$", (Integer arg0, String arg1) -> {
            kitchen.incrementStock(chocolate ,arg0);
        });
        Then("^order is done$", () -> {
            assertDoesNotThrow(() -> anonymousCustomer.makeOrder());
        });

        Then("^order is cancel$", () -> {
            assertThrows(ErrorPreparingOrder.class, () -> anonymousCustomer.makeOrder());
            Log.display();
        });

    }
}
