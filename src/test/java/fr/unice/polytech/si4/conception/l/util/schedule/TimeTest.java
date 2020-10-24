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
        assertEquals( 0, time.getClosingHours().getHours());
        assertEquals( 0, time.getClosingHours().getMinutes());
        assertEquals( 0, time.getClosingHours().getSeconds());
    }

    @Test
    void getOpeningHours() {
        assertEquals( 0,time.getOpeningHours().getHours());
        assertEquals( 0, time.getOpeningHours().getMinutes());
        assertEquals( 0, time.getOpeningHours().getSeconds());
    }

    @Test
    void setOpeningHours() {
        Date date = new Date();
        date.setSeconds(8);
        date.setMinutes(56);
        date.setHours(13);

        time.setOpeningHours(date);

        assertEquals( 13,time.getOpeningHours().getHours());
        assertEquals( 56,time.getOpeningHours().getMinutes());
        assertEquals(8,time.getOpeningHours().getSeconds() );

    }

    @Test
    void setClosingHours() {
        Date date = new Date();
        date.setSeconds(32);
        date.setMinutes(23);
        date.setHours(1);

        time.setClosingHours(date);

        assertEquals( 1,time.getClosingHours().getHours());
        assertEquals( 23,time.getClosingHours().getMinutes());
        assertEquals( 32,time.getClosingHours().getSeconds());
    }
}