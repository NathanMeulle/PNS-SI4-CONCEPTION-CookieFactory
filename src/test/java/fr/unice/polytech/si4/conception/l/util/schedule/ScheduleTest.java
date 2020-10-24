package fr.unice.polytech.si4.conception.l.util.schedule;

import io.cucumber.java8.Da;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleTest {
    private Schedule schedule1;
    private Schedule schedule2;


    @BeforeEach
    public void setup(){
        schedule1 = new Schedule();
        schedule2 = new Schedule(new Time(21,15,37,23,59,59));
    }

    @Test
    public void getOpeningHours() {

        assertNotNull(schedule1.getOpeningHours());

        for (Time p : schedule1.getOpeningHours().values()){
            assertEquals(p.getOpeningHours().getHours(), 8);
            assertEquals(p.getOpeningHours().getMinutes(), 0);
            assertEquals(p.getOpeningHours().getSeconds(), 0);

            assertEquals(p.getClosingHours().getHours(), 18);
            assertEquals(p.getClosingHours().getMinutes(), 0);
            assertEquals(p.getClosingHours().getSeconds(), 0);
        }

        Time time2 = schedule2.getOpeningHours().values().iterator().next();

        assertEquals(time2.getOpeningHours().getHours(), 21);
        assertEquals(time2.getOpeningHours().getMinutes(), 15);
        assertEquals(time2.getOpeningHours().getSeconds(), 37);

        assertEquals(time2.getClosingHours().getHours(), 23);
        assertEquals(time2.getClosingHours().getMinutes(), 59);
        assertEquals(time2.getClosingHours().getSeconds(), 59);
    }

    @Test
    public void setOpeningHours() {
        EnumMap<Day, Time> hours = new EnumMap<Day, Time>(Day.class);
        hours.put(Day.SATURDAY, new Time(15,0,0,20,0,0));
        hours.put(Day.SUNDAY, new Time(15,0,0,20,0,0));


        schedule1.setOpeningHours(hours);
        assertEquals(schedule1.getOpeningHours().size(), 1);
    }

    @Test
    public void alterOpeningHours() {
        schedule1.alterOpeningHours(Day.MONDAY, new Time(0,0,0,0,0,0));
        assertEquals(schedule1.getOpeningHours().values().iterator().next().getOpeningHours().getHours(), 0);
        assertEquals(schedule1.getOpeningHours().values().iterator().next().getOpeningHours().getMinutes(), 0);
        assertEquals(schedule1.getOpeningHours().values().iterator().next().getOpeningHours().getSeconds(), 0);

        assertEquals(schedule1.getOpeningHours().values().iterator().next().getClosingHours().getHours(), 0);
        assertEquals(schedule1.getOpeningHours().values().iterator().next().getClosingHours().getMinutes(), 0);
        assertEquals(schedule1.getOpeningHours().values().iterator().next().getClosingHours().getSeconds(), 0);
    }

    @Test
    public void deleteDayOpeningHours() {
        schedule1.deleteDayOpeningHours(Day.MONDAY);
        assertEquals(schedule1.getOpeningHours().size(), 6);

        schedule2.deleteDayOpeningHours(Day.MONDAY);
        schedule2.deleteDayOpeningHours(Day.TUESDAY);
        schedule2.deleteDayOpeningHours(Day.WEDNESDAY);
        schedule2.deleteDayOpeningHours(Day.THURSDAY);
        schedule2.deleteDayOpeningHours(Day.FRIDAY);

        assertEquals(schedule2.getOpeningHours().size(), 2);
    }
}