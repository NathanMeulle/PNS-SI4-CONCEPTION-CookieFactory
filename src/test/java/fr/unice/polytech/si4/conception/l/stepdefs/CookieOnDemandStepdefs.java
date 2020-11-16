package fr.unice.polytech.si4.conception.l.stepdefs;

import fr.unice.polytech.si4.conception.l.Log;
import fr.unice.polytech.si4.conception.l.SystemInfo;
import fr.unice.polytech.si4.conception.l.customer.Customer;
import fr.unice.polytech.si4.conception.l.exceptions.WrongPickUpTimeException;
import fr.unice.polytech.si4.conception.l.order.Order;
import fr.unice.polytech.si4.conception.l.store.Kitchen;
import fr.unice.polytech.si4.conception.l.store.Manager;
import fr.unice.polytech.si4.conception.l.store.Store;
import io.cucumber.java8.En;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CookieOnDemandStepdefs implements En {
    private Customer customer;
    private Store store;
    private SystemInfo systemInfo;
    private Order order;
    private Kitchen kitchen;
    private Order.OrderBuilder orderBuilder;

    public CookieOnDemandStepdefs() {
        Given("^a registered client named \"([^\"]*)\" with email \"([^\"]*)\" and phone \"([^\"]*)\"$", (String arg0, String arg1, String arg2) -> {
            systemInfo = SystemInfo.getInstance();
            systemInfo.resetSystemInfo();
            customer = new Customer(arg0, arg2, arg1);
            kitchen = new Kitchen();
            kitchen.assignStore(store);
            store = new Store(1, "", 5, "", "", new Manager(1, ""));
            store.setKitchen(kitchen);
            systemInfo.addCustomer(customer);
            systemInfo.addStore(store);
        });

        When("^\"([^\"]*)\" place an order$", (String arg0) -> {
            customer.createOrder(store);
            orderBuilder = customer.getOrderBuilder();
        });
        And("^she choose to pick her cookies at \"([^\"]*)\":\"([^\"]*)\":\"([^\"]*)\" on the same day$", (String arg0, String arg1, String arg2) -> {
            Date pickuptime = new Date();
            orderBuilder.choosePickUpTime(pickuptime);
        });
        And("her order is sent to the store$", () -> {
            customer.makeOrder();
            Log.display();
        });
        Then("^\"([^\"]*)\" comes on time and retrieve her order$", (String arg0) -> {
            customer.getOrder().paid();
            assertDoesNotThrow(() -> customer.pickUpOrder());
        });
        Then("^\"([^\"]*)\" comes hour earlier and she can't pick her order$", (String arg0) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.HOUR, +1);
            Date date = calendar.getTime();
            orderBuilder.choosePickUpTime(date);
            customer.makeOrder();
            customer.getOrder().paid();
            assertThrows(WrongPickUpTimeException.class, () -> customer.pickUpOrder());
        });

    }
}
