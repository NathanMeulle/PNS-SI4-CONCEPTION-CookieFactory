package fr.unice.polytech.si4.conception.l.util.schedule;

import io.cucumber.java8.Da;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TimeTest {
    private Time time;
    @BeforeEach
    void setup(){
        time = new Time(0,0,0,0,0,0);
    }

    @Test
    void getClosingHours() {
        assertEquals(time.getClosingHours().getHours(), 0);
        assertEquals(time.getClosingHours().getMinutes(), 0);
        assertEquals(time.getClosingHours().getSeconds(), 0);
    }

    @Test
    void getOpeningHours() {
        assertEquals(time.getOpeningHours().getHours(), 0);
        assertEquals(time.getOpeningHours().getMinutes(), 0);
        assertEquals(time.getOpeningHours().getSeconds(), 0);
    }

    @Test
    void setOpeningHours() {
        Date date = new Date();
        date.setSeconds(8);
        date.setMinutes(56);
        date.setHours(13);

        time.setOpeningHours(date);

        assertEquals(time.getOpeningHours().getHours(), 13);
        assertEquals(time.getOpeningHours().getMinutes(), 56);
        assertEquals(time.getOpeningHours().getSeconds(), 8);

    }

    @Test
    void setClosingHours() {
        Date date = new Date();
        date.setSeconds(32);
        date.setMinutes(23);
        date.setHours(1);

        time.setClosingHours(date);

        assertEquals(time.getClosingHours().getHours(), 1);
        assertEquals(time.getClosingHours().getMinutes(), 23);
        assertEquals(time.getClosingHours().getSeconds(), 32);
    }
}