package fr.unice.polytech.si4.conception.l.stepdefs;

import fr.unice.polytech.si4.conception.l.Log;
import fr.unice.polytech.si4.conception.l.SystemInfo;
import fr.unice.polytech.si4.conception.l.customer.Customer;
import fr.unice.polytech.si4.conception.l.exceptions.WrongPickUpTimeException;
import fr.unice.polytech.si4.conception.l.order.Order;
import fr.unice.polytech.si4.conception.l.store.Kitchen;
import fr.unice.polytech.si4.conception.l.store.Manager;
import fr.unice.polytech.si4.conception.l.store.Store;
import fr.unice.polytech.si4.conception.l.store.schedule.Day;
import io.cucumber.java8.En;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CookieOnDemandStepdefs implements En {
    private Customer customer;
    private Store store;
    private SystemInfo systemInfo;
    private Kitchen kitchen;
    private Order.OrderBuilder orderBuilder;
    private Date datePickUp;

    public CookieOnDemandStepdefs() {
        Given("^a registered client named \"([^\"]*)\" with email \"([^\"]*)\" and phone \"([^\"]*)\"$", (String arg0, String arg1, String arg2) -> {
            systemInfo = SystemInfo.getInstance();
            systemInfo.resetSystemInfo();
            customer = new Customer(arg0, arg2, arg1);
            kitchen = new Kitchen();
            kitchen.assignStore(store);
            store = new Store(1, "", 5, "", "",0,0, new Manager(1, ""));
            store.setKitchen(kitchen);
            systemInfo.addCustomer(customer);
            systemInfo.addStore(store);
        });

        When("^\"([^\"]*)\" place an order$", (String arg0) -> {
            customer.createOrder(store);
            orderBuilder = customer.getOrderBuilder();
        });
        And("^she chooses to pick her cookies at \"([^\"]*)\":\"([^\"]*)\":\"([^\"]*)\" on the same day$", (String arg0, String arg1, String arg2) -> {
            Calendar cal = new GregorianCalendar(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue()-1, LocalDateTime.now().getDayOfMonth(), Integer.parseInt(arg0), Integer.parseInt(arg1), Integer.parseInt(arg2));

            Date pickuptime = cal.getTime();
            assertDoesNotThrow(() -> orderBuilder.choosePickUpTime(pickuptime));
        });
        And("her order is sent to the store$", () -> {
            customer.submitOrder();
        });
        Then("^\"([^\"]*)\" comes at \"([^\"]*)\":\"([^\"]*)\":\"([^\"]*)\" and retrieve her order$", (String arg0, String arg1, String arg2, String arg3) -> {
            Calendar cal = new GregorianCalendar(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue()-1, LocalDateTime.now().getDayOfMonth(), Integer.parseInt(arg1), Integer.parseInt(arg2), Integer.parseInt(arg3));

            Date pickuptime = cal.getTime();
            customer.submitOrder();
            customer.getOrder().paid();
            assertDoesNotThrow(() -> customer.pickUpOrder(pickuptime));
        });

        Then("^\"([^\"]*)\" comes at \"([^\"]*)\":\"([^\"]*)\":\"([^\"]*)\" and she can't pick her order$", (String arg0, String arg1, String arg2, String arg3) -> {
            Calendar cal = new GregorianCalendar(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue()-1, LocalDateTime.now().getDayOfMonth(), Integer.parseInt(arg1), Integer.parseInt(arg2), Integer.parseInt(arg3));

            Date pickuptime = cal.getTime();
            customer.submitOrder();
            customer.getOrder().paid();
            assertThrows(WrongPickUpTimeException.class, () -> customer.pickUpOrder(pickuptime));
        });

        And("^she chooses to pick her cookies when the store is closed$", () -> {
            Date closingTime = store.getSchedule().getOpeningHours(Day.MONDAY).getClosingHours();
            int closing = closingTime.getHours();
            int currentTime = new Date().getHours();
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.HOUR, Math.abs((closing - currentTime)) +1);
            datePickUp = calendar.getTime();
        });

        Then("^her order is not sent to the store$", () -> {
            assertThrows(WrongPickUpTimeException.class, () -> orderBuilder.choosePickUpTime(datePickUp));

        });
        And("^she chooses to pick her cookies at \"([^\"]*)\":\"([^\"]*)\":\"([^\"]*)\"$", (String arg0, String arg1, String arg2) -> {
            Calendar cal = new GregorianCalendar(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue()-1, 22, Integer.parseInt(arg0), Integer.parseInt(arg1), Integer.parseInt(arg2));
            datePickUp = cal.getTime();

        });
        Then("^her order is sent to the store : \"([^\"]*)\"$", (String arg0) -> {
            if(arg0.equals("yes")) {
                assertDoesNotThrow(() -> orderBuilder.choosePickUpTime(datePickUp));
                customer.submitOrder();
                customer.getOrder().paid();
            }
            else
                assertThrows(WrongPickUpTimeException.class, () -> orderBuilder.choosePickUpTime(datePickUp));
        });
        And("^\"([^\"]*)\" comes at \"([^\"]*)\":\"([^\"]*)\":\"([^\"]*)\"$", (String arg0, String arg1, String arg2, String arg3) -> {
            Calendar cal = new GregorianCalendar(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue()-1, 22, Integer.parseInt(arg1), Integer.parseInt(arg2), Integer.parseInt(arg3));
            datePickUp = cal.getTime();
        });
        Then("^she can pick her order : \"([^\"]*)\"$", (String arg0) -> {
            if(arg0.equals("yes"))
                assertDoesNotThrow(() -> customer.pickUpOrder(datePickUp));
            else if (!arg0.equals("_"))
                assertThrows(WrongPickUpTimeException.class, () -> customer.pickUpOrder(datePickUp));
        });
    }
}
