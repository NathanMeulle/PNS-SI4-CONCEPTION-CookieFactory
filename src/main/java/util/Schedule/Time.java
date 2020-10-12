package util.Schedule;

import java.util.Date;

/**
 * @author Asaréel DADIOUARI
 * Custom class to display Time
 */
public class Time {
    private Date openingHours;
    private Date closingHours;

    public Time(int openingHour, int openingMinute, int openingSeconds, int closingHour, int closingMinute, int closingSecond){
        openingHours = new Date();
        closingHours = new Date();

        openingHours.setHours(openingHour);
        openingHours.setMinutes(openingMinute);
        openingHours.setSeconds(openingSeconds);

        closingHours.setHours(closingHour);
        closingHours.setMinutes(closingMinute);
        closingHours.setSeconds(closingSecond);
    }

    /** ********************************************************************************
     *                               GETTERS / SETTERS
     *  ********************************************************************************
     */

    public Date getClosingHours() {
        return closingHours;
    }

    public Date getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(Date openingHours) {
        this.openingHours = openingHours;
    }

    public void setClosingHours(Date closingHours) {
        this.closingHours = closingHours;
    }
}
