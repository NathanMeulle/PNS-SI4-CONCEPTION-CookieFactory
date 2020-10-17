package fr.unice.polytech.si4.conception.l.util.schedule;

import java.util.EnumMap;
import java.util.Map;

/**
 * @author Asaréel DADIOUARI
 * Custom class to manage a store's opening/closing hours
 */
public class Schedule {
    private EnumMap<Day, Time> openingHours;

    public Schedule(){
        openingHours = new EnumMap<>(Day.class); //TODO : faire un constructeur par défaut 8-18h
    }
    /**
     * Creates a working schedule for a Store
     * @param openDay opening day
     * @param openTime opening hour
     */

    public Schedule(Day openDay, Time openTime){ //TODO constructeur permettant d'initialiser un seul jour... #useless
        openingHours = new EnumMap<>(Day.class);
        openingHours.put(openDay, openTime);
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

    public void addOpeningHours(Day day, Time time) { openingHours.put(day, time);}

//TODO : supprimer jour d'ouverture ?

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
