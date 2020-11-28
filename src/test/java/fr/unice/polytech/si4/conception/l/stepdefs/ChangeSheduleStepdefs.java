package fr.unice.polytech.si4.conception.l.stepdefs;

import fr.unice.polytech.si4.conception.l.store.Store;
import fr.unice.polytech.si4.conception.l.store.schedule.Day;
import fr.unice.polytech.si4.conception.l.store.schedule.Schedule;
import fr.unice.polytech.si4.conception.l.store.schedule.Time;
import io.cucumber.java8.En;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ChangeSheduleStepdefs implements En {

    private Schedule schedule1;
    private Store store;
    private int closingHour;
    private final Calendar calendarOpening = Calendar.getInstance();


    public ChangeSheduleStepdefs() {
        Given("^a store openning at (\\d+)am each day and closing at (\\d+)pm$", (Integer arg0, Integer arg1) -> {
            closingHour = arg1;
            schedule1 = new Schedule(new Time(arg0, 0, 0, arg1, 0, 0));
            store = mock(Store.class);
            when(store.getSchedule()).thenReturn(schedule1);
        });
        When("^the manager sets the openning hours to (\\d+)am on Monday$", (Integer arg0) -> {
            schedule1.alterOpeningHours(Day.MONDAY, new Time(arg0, 0, 0, closingHour, 0, 0));
        });
        Then("^the store opens at (\\d+)am on Monday$$", (Integer arg0) -> {
            assertEquals(arg0, store.getSchedule().getOpeningHours(Day.MONDAY).getOpeningHours().getHours());
        });
        And("^the store opens at (\\d+)am on Tuesday$", (Integer arg0) -> {
            assertEquals(arg0, store.getSchedule().getOpeningHours(Day.TUESDAY).getOpeningHours().getHours());
        });
        When("^the manager sets a day of on Sunday$", () -> {
            schedule1.deleteDayOpeningHours(Day.MONDAY);
        });
        Then("^the store is not opened on Sunday$", () -> {
            assertEquals(6, schedule1.getOpeningHours().size());

        });
    }
}
