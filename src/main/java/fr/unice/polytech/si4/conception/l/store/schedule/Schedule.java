package fr.unice.polytech.si4.conception.l.store.schedule;

import java.util.EnumMap;
import java.util.Map;

/**
 * @author Asaréel DADIOUARI
 * @author Lemuel DONTO
 * Custom class to manage a store's opening/closing hours
 */
public class Schedule {
    private EnumMap<Day, Time> openingHours;

    public Schedule(){
        openingHours = new EnumMap<>(Day.class);
        Time t = new Time(8, 0,0, 18, 0,0);
        openingHours.put(Day.MONDAY, t);
        openingHours.put(Day.TUESDAY, t);
        openingHours.put(Day.WEDNESDAY, t);
        openingHours.put(Day.THURSDAY, t);
        openingHours.put(Day.FRIDAY, t);
        openingHours.put(Day.SATURDAY, t);
        openingHours.put(Day.SUNDAY, t);
    }
    /**
     * Creates a working schedule for a Store
     * @param openTime opening hour
     */
    public Schedule(Time openTime){
        openingHours = new EnumMap<>(Day.class);
        openingHours.put(Day.MONDAY, openTime);
        openingHours.put(Day.TUESDAY, openTime);
        openingHours.put(Day.WEDNESDAY, openTime);
        openingHours.put(Day.THURSDAY,openTime);
        openingHours.put(Day.FRIDAY, openTime);
        openingHours.put(Day.SATURDAY, openTime);
        openingHours.put(Day.SUNDAY, openTime);
    }

    /** ********************************************************************************
     *                               GETTERS / SETTERS
     *  ********************************************************************************
     */

    public Map<Day, Time> getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(EnumMap<Day, Time> openingHours) {
        this.openingHours = openingHours;
    }

    public void alterOpeningHours(Day day, Time time) { openingHours.put(day, time);}

    public void deleteDayOpeningHours(Day day){
        this.openingHours.remove(day);
    }

    /**
     * @deprecated use Calendar instead
     * @return string
     */
    @Override
    @Deprecated
    public String toString() {

        String str = "";

        for ( Map.Entry<Day, Time> sc : openingHours.entrySet()){
            str +=  "Open :" + sc.getKey() +
                    "," + sc.getValue().getOpeningHours().getHours() +
                    ":" + sc.getValue().getOpeningHours().getMinutes() +
                    ":" + sc.getValue().getOpeningHours().getSeconds() +
                    " - " +
                    sc.getValue().getClosingHours().getHours() +
                    ":" + sc.getValue().getClosingHours().getMinutes() +
                    ":" + sc.getValue().getClosingHours().getSeconds() +
                    "\n";
        }

        return str;
    }
}
