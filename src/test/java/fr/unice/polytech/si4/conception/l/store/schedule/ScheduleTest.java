package fr.unice.polytech.si4.conception.l.store.schedule;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.EnumMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ScheduleTest {
    private Schedule schedule1;
    private Schedule schedule2;
    private final Calendar calendarOpening = Calendar.getInstance();
    private final Calendar calendarClosing = Calendar.getInstance();

    @BeforeEach
    public void setup() {
        schedule1 = new Schedule();
        schedule2 = new Schedule(new Time(21, 15, 37, 23, 59, 59));
    }

    @Test
    void getOpeningHours() {

        assertNotNull(schedule1.getOpeningHours());

        for (Time p : schedule1.getOpeningHours().values()) {
            calendarOpening.setTime(p.getOpeningHours());
            calendarClosing.setTime(p.getClosingHours());

            assertEquals(8, calendarOpening.get(Calendar.HOUR_OF_DAY));
            assertEquals(0, calendarOpening.get(Calendar.MINUTE));
            assertEquals(0, calendarOpening.get(Calendar.SECOND));

            assertEquals(18, calendarClosing.get(Calendar.HOUR_OF_DAY));
            assertEquals(0, calendarClosing.get(Calendar.MINUTE));
            assertEquals(0, calendarClosing.get(Calendar.SECOND));
            //System.out.println(calendarClosing.getTime());

        }

        Time time2 = schedule2.getOpeningHours().values().iterator().next();
        calendarOpening.setTime(time2.getOpeningHours());
        calendarClosing.setTime(time2.getClosingHours());

        assertEquals(21, calendarOpening.get(Calendar.HOUR_OF_DAY));
        assertEquals(15, calendarOpening.get(Calendar.MINUTE));
        assertEquals(37, calendarOpening.get(Calendar.SECOND));

        assertEquals(23, calendarClosing.get(Calendar.HOUR_OF_DAY));
        assertEquals(59, calendarClosing.get(Calendar.MINUTE));
        assertEquals(59, calendarClosing.get(Calendar.SECOND));
    }

    @Test
    void setOpeningHours() {
        EnumMap<Day, Time> hours = new EnumMap<>(Day.class);
        hours.put(Day.SATURDAY, new Time(15, 0, 0, 20, 0, 0));
        hours.put(Day.SUNDAY, new Time(15, 0, 0, 20, 0, 0));


        schedule1.setOpeningHours(hours);
        assertEquals(2, schedule1.getOpeningHours().size());
    }

    @Test
    void alterOpeningHours() {
        schedule1.alterOpeningHours(Day.MONDAY, new Time(0, 0, 0, 0, 0, 0));
        calendarOpening.setTime(schedule1.getOpeningHours().values().iterator().next().getOpeningHours());
        calendarClosing.setTime(schedule1.getOpeningHours().values().iterator().next().getClosingHours());


        assertEquals(0, calendarOpening.get(Calendar.HOUR_OF_DAY));
        assertEquals(0, calendarOpening.get(Calendar.MINUTE));
        assertEquals(0, calendarOpening.get(Calendar.SECOND));

        assertEquals(0, calendarClosing.get(Calendar.HOUR_OF_DAY));
        assertEquals(0, calendarClosing.get(Calendar.MINUTE));
        assertEquals(0, calendarClosing.get(Calendar.SECOND));
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
