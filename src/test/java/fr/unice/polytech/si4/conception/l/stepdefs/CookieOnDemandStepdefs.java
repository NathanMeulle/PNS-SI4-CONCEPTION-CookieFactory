package fr.unice.polytech.si4.conception.l.stepdefs;

import fr.unice.polytech.si4.conception.l.*;
import fr.unice.polytech.si4.conception.l.exceptions.WrongPickUpTimeException;
import io.cucumber.java8.En;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class CookieOnDemandStepdefs implements En {
    private Customer customer;
    private Store store;
    private CookieFactory cookieFactory;
    private Order order;
    private Kitchen kitchen;

    public CookieOnDemandStepdefs() {
        Given("^a registered client named \"([^\"]*)\" with email \"([^\"]*)\" and phone \"([^\"]*)\"$", (String arg0, String arg1, String arg2) -> {
            cookieFactory = CookieFactory.getInstance();
            cookieFactory.resetFactory();
            customer = new Customer(arg0, arg2, arg1);
            kitchen = new Kitchen();
            kitchen.assignStore(store);
            store = new Store(1, "", 5, "", "", new Manager(1, ""));
            store.setKitchen(kitchen);
            cookieFactory.addCustomer(customer);
            cookieFactory.addStore(store);
        });
        When("^\"([^\"]*)\" place an order$", (String arg0) -> {
            customer.createOrder(store);
            order = customer.getOrder();
        });
        And("^she choose to pick her cookies at \"([^\"]*)\":\"([^\"]*)\":\"([^\"]*)\" on the same day$", (String arg0, String arg1, String arg2) -> {
            Date pickuptime = new Date();
            order.choosePickUpTime(pickuptime);
        });
        And("her order is sent to the store$", () -> {
            customer.makeOrder();
            Log.display();
        });
        Then("^\"([^\"]*)\" comes on time and retrieve her order$", (String arg0) -> {
            order.isPaid();
            assertDoesNotThrow(() -> customer.pickUpOrder());
        });
        Then("^\"([^\"]*)\" comes hour earlier and she can't pick her order$", (String arg0) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.HOUR, +1);
            Date date = calendar.getTime();
            order.choosePickUpTime(date);
            order.isPaid();
            assertThrows(WrongPickUpTimeException.class, () -> customer.pickUpOrder());
        });

    }
}
