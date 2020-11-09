package fr.unice.polytech.si4.conception.l.stepdefs;

import fr.unice.polytech.si4.conception.l.*;
import io.cucumber.java8.En;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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
            cookieFactory = new CookieFactory(null, new ArrayList<>());
            customer = new Customer(arg0, arg2, arg1);
            kitchen = new Kitchen();
            kitchen.assignStore(store);
            store = new Store(1, "", 5, "", "", new Manager(1, ""));
            store.setKitchen(kitchen);
            cookieFactory.addCustomer(customer);
            cookieFactory.addStore(store);
        });
        When("^\"([^\"]*)\" place an order$", (String arg0) -> {
            order = customer.createOrder(store);
        });
        Then("^she choose to pick her cookies at \"([^\"]*)\":\"([^\"]*)\":\"([^\"]*)\"PM on the same day$", (String arg0, String arg1, String arg2) -> {
            Calendar cal = Calendar.getInstance();

            cal.set(Calendar.MONTH, LocalDateTime.now().getMonthValue()-1);
            cal.set(Calendar.DAY_OF_MONTH, LocalDateTime.now().getDayOfMonth());
            cal.set(Calendar.YEAR, LocalDateTime.now().getYear());

            cal.set(Calendar.HOUR, Integer.parseInt(arg0));
            cal.set(Calendar.MINUTE, Integer.parseInt(arg1));
            cal.set(Calendar.SECOND, Integer.parseInt(arg2));

            Date pickuptime = cal.getTime();
            order.choosePickUpTime(pickuptime);
        });
        And("an order is sent to the store$", () -> {
            customer.makeOrder();
            Log.display();
        });
        Then("^\"([^\"]*)\" comes at \"([^\"]*)\":\"([^\"]*)\":\"([^\"]*)\" and retrieve her order$", (String arg0, String arg1, String arg2, String arg3) -> {
            order.isPaid();
            customer.pickUpOrder();
        });
        And("^there is no order pending$", () -> {
        });

    }
}
