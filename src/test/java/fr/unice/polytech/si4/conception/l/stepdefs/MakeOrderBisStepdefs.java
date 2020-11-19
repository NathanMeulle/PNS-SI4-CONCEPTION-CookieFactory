package fr.unice.polytech.si4.conception.l.stepdefs;

import fr.unice.polytech.si4.conception.l.customer.AnonymousCustomer;
import fr.unice.polytech.si4.conception.l.exceptions.ErrorPreparingOrder;
import fr.unice.polytech.si4.conception.l.order.Order;
import fr.unice.polytech.si4.conception.l.products.Cookie;
import fr.unice.polytech.si4.conception.l.products.composition.Ingredient;
import fr.unice.polytech.si4.conception.l.products.composition.IngredientType;
import fr.unice.polytech.si4.conception.l.store.Kitchen;
import fr.unice.polytech.si4.conception.l.store.Manager;
import fr.unice.polytech.si4.conception.l.store.Store;
import io.cucumber.java8.En;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MakeOrderBisStepdefs implements En {

    private AnonymousCustomer anonymousCustomer;
    private Order order;
    private Cookie cookieM;
    private Store store;
    private Kitchen kitchen;
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
            when(cookieM.getPrice()).thenReturn((double) 2);
            when(cookieM.getPrice()).thenReturn(2.0);
            store = new Store(1, "oui", 25, "06", "", mock(Manager.class));
            store.setKitchen(kitchen);
            kitchen.assignStore(store);

            anonymousCustomer.createOrder(store);
            anonymousCustomer.addCookie(cookieM, 3);

        });
        When("^an anonymous customer submit his order and (\\d+) \"([^\"]*)\" in the kitchen$", (Integer arg0, String arg1) -> {
            kitchen.incrementStock(chocolate ,arg0);
        });
        Then("^order is done$", () -> {
            assertDoesNotThrow(() -> anonymousCustomer.submitOrder());
        });

        Then("^order is cancel$", () -> {
            assertThrows(ErrorPreparingOrder.class, () -> anonymousCustomer.submitOrder());
        });

    }
}
