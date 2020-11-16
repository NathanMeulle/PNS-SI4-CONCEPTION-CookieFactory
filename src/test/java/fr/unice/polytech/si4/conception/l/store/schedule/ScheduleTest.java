package fr.unice.polytech.si4.conception.l.store.schedule;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ScheduleTest {
    private Schedule schedule1;
    private Schedule schedule2;


    @BeforeEach
    public void setup() {
        schedule1 = new Schedule();
        schedule2 = new Schedule(new Time(21, 15, 37, 23, 59, 59));
    }

    @Test
    void getOpeningHours() {

        assertNotNull(schedule1.getOpeningHours());

        for (Time p : schedule1.getOpeningHours().values()) {
            assertEquals(8, p.getOpeningHours().getHours());
            assertEquals(0, p.getOpeningHours().getMinutes());
            assertEquals(0, p.getOpeningHours().getSeconds());

            assertEquals(18, p.getClosingHours().getHours());
            assertEquals(0, p.getClosingHours().getMinutes());
            assertEquals(0, p.getClosingHours().getSeconds());
        }

        Time time2 = schedule2.getOpeningHours().values().iterator().next();

        assertEquals(21, time2.getOpeningHours().getHours());
        assertEquals(15, time2.getOpeningHours().getMinutes());
        assertEquals(37, time2.getOpeningHours().getSeconds());

        assertEquals(23, time2.getClosingHours().getHours());
        assertEquals(59, time2.getClosingHours().getMinutes());
        assertEquals(59, time2.getClosingHours().getSeconds());
    }

    @Test
    void setOpeningHours() {
        EnumMap<Day, Time> hours = new EnumMap<Day, Time>(Day.class);
        hours.put(Day.SATURDAY, new Time(15, 0, 0, 20, 0, 0));
        hours.put(Day.SUNDAY, new Time(15, 0, 0, 20, 0, 0));


        schedule1.setOpeningHours(hours);
        assertEquals(2, schedule1.getOpeningHours().size());
    }

    @Test
    void alterOpeningHours() {
        schedule1.alterOpeningHours(Day.MONDAY, new Time(0, 0, 0, 0, 0, 0));
        assertEquals(0, schedule1.getOpeningHours().values().iterator().next().getOpeningHours().getHours());
        assertEquals(0, schedule1.getOpeningHours().values().iterator().next().getOpeningHours().getMinutes());
        assertEquals(0, schedule1.getOpeningHours().values().iterator().next().getOpeningHours().getSeconds());

        assertEquals(0, schedule1.getOpeningHours().values().iterator().next().getClosingHours().getHours());
        assertEquals(0, schedule1.getOpeningHours().values().iterator().next().getClosingHours().getMinutes());
        assertEquals(0, schedule1.getOpeningHours().values().iterator().next().getClosingHours().getSeconds());
    }

    @Test
    void deleteDayOpeningHours() {
        schedule1.deleteDayOpeningHours(Day.MONDAY);
        assertEquals(6, schedule1.getOpeningHours().size());

        schedule2.deleteDayOpeningHours(Day.MONDAY);
        schedule2.deleteDayOpeningHours(Day.TUESDAY);
        schedule2.deleteDayOpeningHours(Day.WEDNESDAY);
        schedule2.deleteDayOpeningHours(Day.THURSDAY);
        schedule2.deleteDayOpeningHours(Day.FRIDAY);

        assertEquals(2, schedule2.getOpeningHours().size());
    }
}
