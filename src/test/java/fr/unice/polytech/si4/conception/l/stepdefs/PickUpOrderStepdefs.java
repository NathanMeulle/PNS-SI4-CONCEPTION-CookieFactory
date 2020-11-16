package fr.unice.polytech.si4.conception.l.stepdefs;

import fr.unice.polytech.si4.conception.l.customer.AnonymousCustomer;
import fr.unice.polytech.si4.conception.l.exceptions.NotAlreadyCooked;
import fr.unice.polytech.si4.conception.l.exceptions.NotPaid;
import fr.unice.polytech.si4.conception.l.order.Order;
import fr.unice.polytech.si4.conception.l.order.StateOrder;
import fr.unice.polytech.si4.conception.l.store.Manager;
import fr.unice.polytech.si4.conception.l.store.Store;
import io.cucumber.java8.En;

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
            anonymousCustomer.createOrder(store);
            order = anonymousCustomer.getOrder();
        });

        When("^an anonymous client pick up his order$", () -> {
        });

        And("^his order is ready$", () -> {
            order.setStateOrder(StateOrder.COOKED);
        });

        Then("^order is well picked up and put in the history$", () -> {
            assertDoesNotThrow(() -> anonymousCustomer.pickUpOrder());
        });

        And("^his order isn't ready yet$", () -> {
            order.setStateOrder(StateOrder.VALIDATED);
        });

        Then("^order raise an exception$", () -> {
            assertThrows(NotAlreadyCooked.class, () -> anonymousCustomer.pickUpOrder());
        });
        And("^the order is paid$", () -> {
            order.paid();
        });
        And("^the order is not paid$", () -> {
        });
        Then("^order is not picked up$", () -> {
            assertThrows(NotPaid.class, () -> anonymousCustomer.pickUpOrder());

        });
    }
}
