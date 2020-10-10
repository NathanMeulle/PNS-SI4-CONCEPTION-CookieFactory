package util.Schedule;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TimeTest {
    Time time;

    public TimeTest(){
        time = new Time(15, 23, 8);
    }

    @Test
    public void getHours() {
        assertEquals(time.getHours(), 15);
    }

    @Test
    public void getMinutes() {
        assertEquals(time.getMinutes(), 23);
    }

    @Test
    public void getSeconds() {
        assertEquals(time.getSeconds(), 8);
    }

    @Test
    public void setHours() {
        time.setHours(7);
        assertNotEquals(time.getHours(), 15);
    }

    @Test
    public void setMinutes() {
        time.setMinutes(59);
        assertNotEquals(time.getHours(), 23);
    }

    @Test
    public void setSeconds() {
        time.setSeconds(0);
        assertNotEquals(time.getHours(), 8);
    }
}