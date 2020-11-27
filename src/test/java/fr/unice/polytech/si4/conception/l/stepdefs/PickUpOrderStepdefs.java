package fr.unice.polytech.si4.conception.l.stepdefs;

import fr.unice.polytech.si4.conception.l.customer.AnonymousCustomer;
import fr.unice.polytech.si4.conception.l.exceptions.NotAlreadyCooked;
import fr.unice.polytech.si4.conception.l.exceptions.NotPaid;
import fr.unice.polytech.si4.conception.l.order.Order;
import fr.unice.polytech.si4.conception.l.order.StateOrder;
import fr.unice.polytech.si4.conception.l.store.Manager;
import fr.unice.polytech.si4.conception.l.store.Store;
import io.cucumber.java8.En;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class PickUpOrderStepdefs implements En {

    private AnonymousCustomer anonymousCustomer;
    private Order order;
    private Store store;
    private Date pickuptime;
    private Order.OrderBuilder orderBuilder;


    public PickUpOrderStepdefs() {

        Given("^an order and an anonymous customer$", () -> {
            store = new Store(1, "adresse", 25, "06", "mail",0,0, mock(Manager.class));
            anonymousCustomer = new AnonymousCustomer("Bertrand", "06");
            anonymousCustomer.createOrder(store);
            orderBuilder = anonymousCustomer.getOrderBuilder();
            Calendar cal = new GregorianCalendar(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue()-1, LocalDateTime.now().getDayOfMonth(), 14, 0, 0);
            Date choosenPickuptime = cal.getTime();
            orderBuilder.choosePickUpTime(choosenPickuptime);
            anonymousCustomer.submitOrder();
            order = anonymousCustomer.getOrder();
        });

        When("^an anonymous client pick up his order at \"([^\"]*)\":\"([^\"]*)\":\"([^\"]*)\"$", (String arg1, String arg2, String arg3) -> {
            Calendar cal = new GregorianCalendar(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue()-1, LocalDateTime.now().getDayOfMonth(), Integer.parseInt(arg1), Integer.parseInt(arg2), Integer.parseInt(arg3));
            pickuptime = cal.getTime();
        });

        And("^his order is ready$", () -> {
            order.setStateOrder(StateOrder.COOKED);
        });

        Then("^order is well picked up and put in the history$", () -> {
            assertDoesNotThrow(() -> anonymousCustomer.pickUpOrder(pickuptime));
        });

        And("^his order isn't ready yet$", () -> {
            order.setStateOrder(StateOrder.VALIDATED);
        });

        Then("^order raise an exception$", () -> {
            assertThrows(NotAlreadyCooked.class, () -> anonymousCustomer.pickUpOrder(pickuptime));
        });
        And("^the order is paid$", () -> {
            order.paid();
        });
        And("^the order is not paid$", () -> {
        });
        Then("^order is not picked up$", () -> {
            assertThrows(NotPaid.class, () -> anonymousCustomer.pickUpOrder(pickuptime));

        });
    }
}
