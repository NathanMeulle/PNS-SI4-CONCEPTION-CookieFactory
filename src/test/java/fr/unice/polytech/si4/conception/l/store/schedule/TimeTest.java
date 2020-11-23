package fr.unice.polytech.si4.conception.l.store.schedule;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimeTest {
    private Time time;
    private final Calendar calendarOpen = Calendar.getInstance();
    private final Calendar calendarClose = Calendar.getInstance();

    @BeforeEach
    void setup(){
        time = new Time(0,0,0,0,0,0);
        calendarOpen.setTime(time.getOpeningHours());
        calendarClose.setTime(time.getClosingHours());
    }

    @Test
    void getClosingHours() {
        assertEquals( 0, calendarClose.get(Calendar.HOUR_OF_DAY));
        assertEquals( 0, calendarClose.get(Calendar.MINUTE));
        assertEquals( 0, calendarClose.get(Calendar.SECOND));
    }

    @Test
    void getOpeningHours() {
        assertEquals( 0, calendarOpen.get(Calendar.HOUR_OF_DAY));
        assertEquals( 0, calendarOpen.get(Calendar.MINUTE));
        assertEquals( 0, calendarOpen.get(Calendar.SECOND));
    }

    @Test
    void setOpeningHours() {
        Date date = new Date(calendarOpen.getWeekYear(), calendarOpen.get(Calendar.MONTH), calendarOpen.get(Calendar.DAY_OF_WEEK), 13, 56, 8);
        time.setOpeningHours(date);
        calendarOpen.setTime(time.getOpeningHours());

        assertEquals( 13, calendarOpen.get(Calendar.HOUR_OF_DAY));
        assertEquals( 56, calendarOpen.get(Calendar.MINUTE));
        assertEquals( 8, calendarOpen.get(Calendar.SECOND));
    }

    @Test
    void setClosingHours() {
        Date date = new Date(calendarClose.getWeekYear(), calendarClose.get(Calendar.MONTH), calendarClose.get(Calendar.DAY_OF_WEEK), 1, 23, 32);
        time.setClosingHours(date);
        calendarClose.setTime(time.getClosingHours());

        assertEquals( 1, calendarClose.get(Calendar.HOUR_OF_DAY));
        assertEquals( 23, calendarClose.get(Calendar.MINUTE));
        assertEquals( 32, calendarClose.get(Calendar.SECOND));
    }
}
