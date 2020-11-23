package fr.unice.polytech.si4.conception.l.store.schedule;

import java.util.Comparator;
import java.util.Date;
import java.util.EnumMap;
import java.util.Map;

/**
 * @author Asaréel DADIOUARI
 * @author Lemuel DONTO
 * Custom class to manage a store's opening/closing hours
 */
public class Schedule implements Comparator<Date> {
    private Map<Day, Time> openingHours;

    public Schedule(){
        openingHours = new EnumMap<>(Day.class);

        for (int i = 0; i < 7; i++){
            Time t = new Time(8, 0,0, 18, 0,0);
            t.setDayOfTheWeek(i);
            openingHours.put(Day.getDayFromInt(i), t);
        }
    }
    /**
     * Creates a working schedule for a Store
     * @param openTime opening hour
     */
    public Schedule(Time openTime){
        openingHours = new EnumMap<>(Day.class);

        for (int i = 0; i < 7; i++){
            Time openT = new Time(openTime);
            openT.setDayOfTheWeek(i);
            openingHours.put(Day.getDayFromInt(i), openT);
        }
    }

    /** ********************************************************************************
     *                               GETTERS / SETTERS
     *  ********************************************************************************
     */

    public Map<Day, Time> getOpeningHours() {
        return openingHours;
    }
    public Time getOpeningHours(Day day) {
        return openingHours.get(day);
    }

    public boolean checkIsOpen(Date date){
        Time timeOpenning = openingHours.get(getDay(date.getDay()));
        if (compare(date, timeOpenning.getOpeningHours()) < 0)
            return false;

        Time timeClosing = openingHours.get(getDay(date.getDay()));
        if (compare(date, timeClosing.getClosingHours()) > 0)
            return false;

        return true;
    }

    @Override
    public int compare(Date d1, Date d2) {
            return Integer.compare(d1.getHours() * 3600 + d1.getMinutes() * 60 + d1.getSeconds(), d2.getHours() * 3600 + d2.getMinutes() * 60 + d2.getSeconds());
    }

    private Day getDay(int i) { //TODO getDayFromInt à utiliser à la place
        switch(i) {
            case 1: return Day.SUNDAY;
            case 2: return Day.MONDAY;
            case 3: return Day.TUESDAY;
            case 4: return Day.WEDNESDAY;
            case 5: return Day.THURSDAY;
            case 6: return Day.FRIDAY;
            case 7: return Day.SATURDAY;
            default: return Day.SUNDAY;
        }
    }

    public void setOpeningHours(Map<Day, Time> openingHours) {
        this.openingHours = openingHours;
    }

    public void alterOpeningHours(Day day, Time time) { openingHours.put(day, time);}

    public void deleteDayOpeningHours(Day day){
        this.openingHours.remove(day);
    }

}
