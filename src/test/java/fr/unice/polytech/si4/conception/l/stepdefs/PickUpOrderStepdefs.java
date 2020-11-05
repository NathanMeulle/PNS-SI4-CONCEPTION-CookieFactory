package fr.unice.polytech.si4.conception.l.stepdefs;

import fr.unice.polytech.si4.conception.l.*;
import fr.unice.polytech.si4.conception.l.exceptions.NotAlreadyCooked;
import io.cucumber.java8.En;
import org.mockito.internal.matchers.Or;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class PickUpOrderStepdefs implements En {

    private AnonymousCustomer anonymousCustomer;
    private Order order;
    private Store store;


    public PickUpOrderStepdefs() {

        Given("^an order and an anonymous customer$", () -> {
            store = new Store(1, "adresse", 25, "06", "mail", mock(Manager.class));
            anonymousCustomer = new AnonymousCustomer("Bertrand", "06");
            order = anonymousCustomer.createOrder(store);
            order.setStore(store);
        });

        When("^an anonymous client pick up his order$", () -> {
        });

        And("^his order is ready$", () -> {
            order.setStateOrder(StateOrder.Cooked);
        });

        Then("^order is well picked up and put in the history$", () -> {
            assertDoesNotThrow(() -> anonymousCustomer.pickUpOrder());
        });

        And("^his order isn't ready yet$", () -> {
            order.setStateOrder(StateOrder.Validated);
        });

        Then("^order raise an exception$", () -> {
            assertThrows(NotAlreadyCooked.class, () -> anonymousCustomer.pickUpOrder());
        });
    }
}
