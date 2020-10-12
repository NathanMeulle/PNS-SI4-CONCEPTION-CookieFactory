package util.Schedule;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class ScheduleTest {
    public Schedule schedule;

    public ScheduleTest(){
        Time open = new Time(8, 30, 0);
        Time close = new Time(18, 0, 0);
        schedule = new Schedule(Day.MONDAY, open, Day.MONDAY, close);
    }

    @Test
    public void getClosingHours() {

    }

    @Test
    public void getOpeningHours() {
    }

    @Test
    public void setClosingHours() {
    }

    @Test
    public void setOpeningHours() {
    }
}