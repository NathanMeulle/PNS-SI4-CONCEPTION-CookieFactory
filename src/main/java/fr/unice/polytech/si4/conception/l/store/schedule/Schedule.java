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

    public void setOpeningHours(EnumMap<Day, Time> openingHours) {
        this.openingHours = openingHours;
    }

    public void alterOpeningHours(Day day, Time time) { openingHours.put(day, time);}

    public void deleteDayOpeningHours(Day day){
        this.openingHours.remove(day);
    }

}
