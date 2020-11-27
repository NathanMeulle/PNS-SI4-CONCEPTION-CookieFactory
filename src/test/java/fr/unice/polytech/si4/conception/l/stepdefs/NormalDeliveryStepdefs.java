package fr.unice.polytech.si4.conception.l.stepdefs;

import fr.unice.polytech.si4.conception.l.customer.Customer;
import fr.unice.polytech.si4.conception.l.marcel.eat.MarcelEat;
import fr.unice.polytech.si4.conception.l.order.StateOrder;
import fr.unice.polytech.si4.conception.l.products.Cookie;
import fr.unice.polytech.si4.conception.l.products.composition.Ingredient;
import fr.unice.polytech.si4.conception.l.products.composition.IngredientType;
import fr.unice.polytech.si4.conception.l.store.Kitchen;
import fr.unice.polytech.si4.conception.l.store.Manager;
import fr.unice.polytech.si4.conception.l.store.OrderHistory;
import fr.unice.polytech.si4.conception.l.store.Store;
import io.cucumber.java8.En;

import java.awt.event.KeyListener;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

public class NormalDeliveryStepdefs implements En {
    private Customer customer;

    public NormalDeliveryStepdefs() {
        Given("^a customer, an order and a store$", () -> {
            Store store = new Store(1,"address", 5, "06", "mail", mock(Manager.class));
            Kitchen kitchen = mock(Kitchen.class);
            store.setKitchen(kitchen);
            when(kitchen.canDo(any())).thenReturn(true);
            customer = new Customer("francis", "06", "mail");
            customer.createOrder(store);
            customer.assignAddress("address");
            customer.addCookie(mock(Cookie.class), 2);
            MarcelEat.initialization();
        });


        When("^a customer submit a delivery order$", () -> {
            customer.submitOrder();
        });
        And("^MarcelEat can deliver this one$", () -> {
            assertDoesNotThrow(() -> MarcelEat.requestDelivery(customer.getOrder()));
        });
        Then("^the order is well prepare for delivery$", () -> {
            assertEquals(StateOrder.WAITDELIVERY, customer.getOrder().getStateOrder());
        });
        And("^a delivery man pick up the order$", () -> {
            customer.getOrder().deliverByMarcelEat();
        });
        Then("^the order is well deliver by MarcelEat$", () -> {
            assertEquals(StateOrder.CLASSIFIED, customer.getOrder().getStateOrder());
        });

        Then("^the store pay MarcelEat$", () -> {
            assertEquals(2.0, MarcelEat.bank, 0.1);
        });
    }

}
