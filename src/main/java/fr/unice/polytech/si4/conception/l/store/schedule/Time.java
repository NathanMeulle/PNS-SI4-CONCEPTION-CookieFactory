package fr.unice.polytech.si4.conception.l.store.schedule;

import java.util.Calendar;
import java.util.Date;


/**
 * @author Asaréel DADIOUARI
 * Custom class to display Time
 */
public class Time {
    private final Calendar openingHours;
    private final Calendar closingHours;

    public Time( int openingHour, int openingMinute, int openingSeconds, int closingHour, int closingMinute, int closingSecond){
        this.openingHours = Calendar.getInstance();
        this.openingHours.setFirstDayOfWeek(Calendar.MONDAY);
        this.closingHours = Calendar.getInstance();
        this.closingHours.setFirstDayOfWeek(Calendar.MONDAY);

        openingHours.set(Calendar.HOUR_OF_DAY, openingHour);
        openingHours.set(Calendar.MINUTE, openingMinute);
        openingHours.set(Calendar.SECOND, openingSeconds);

        closingHours.set(Calendar.HOUR_OF_DAY, closingHour);
        closingHours.set(Calendar.MINUTE, closingMinute);
        closingHours.set(Calendar.SECOND, closingSecond);
    }

    public Time(Time time){
        this.openingHours = Calendar.getInstance();
        this.closingHours = Calendar.getInstance();
        this.openingHours.setTime(time.getOpeningHours());
        this.closingHours.setTime(time.getClosingHours());
    }

    /** ********************************************************************************
     *                               GETTERS / SETTERS
     *  ********************************************************************************
     */

    public Date getClosingHours() {
        return closingHours.getTime();
    }

    public Date getOpeningHours() {
        return openingHours.getTime();
    }

    public void setOpeningHours(Date openingHours) {
        this.openingHours.setTime(openingHours);
    }

    public void setClosingHours(Date closingHours) {
        this.closingHours.setTime(closingHours);
    }

    public void setDayOfTheWeek(int day){
        openingHours.add(Calendar.DAY_OF_WEEK,  day);
        closingHours.add(Calendar.DAY_OF_WEEK,  day);
    }
}
