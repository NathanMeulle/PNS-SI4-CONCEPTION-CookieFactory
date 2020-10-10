package util.Schedule;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Asaréel DADIOUARI
 * Custom class to manage a store's opening/closing hours
 */
public class Schedule {
    private Map<Day, Time> openingHours;
    private Map<Day, Time> closingHours;

    /**
     * Creates a working schedule for a Store
     * @param openDay opening day
     * @param openTime opening hour
     * @param closeDay closing day
     * @param closeTime closing hour
     */

    public Schedule(Day openDay, Time openTime, Day closeDay, Time closeTime){
        openingHours = new HashMap<Day, Time>();
        closingHours = new HashMap<Day, Time>();

        openingHours.put(openDay, openTime);
        closingHours.put(closeDay, closeTime);
    }

    /** ********************************************************************************
     *                               GETTERS / SETTERS
     *  ********************************************************************************
     */

    public Map<Day, Time> getClosingHours() {
        return closingHours;
    }

    public Map<Day, Time> getOpeningHours() {
        return openingHours;
    }

    public void setClosingHours(Map<Day, Time> closingHours) {
        this.closingHours = closingHours;
    }

    public void setOpeningHours(Map<Day, Time> openingHours) {
        this.openingHours = openingHours;
    }

    @Override
    public String toString() {
        return "Open : " + ((Map.Entry<Day, Time>) openingHours.entrySet().iterator().next()).getKey() +
                "," + ((Map.Entry<Day, Time>) openingHours.entrySet().iterator().next()).getValue() +
                " - " +
                ((Map.Entry<Day, Time>) closingHours.entrySet().iterator().next()).getKey() +
                "," + ((Map.Entry<Day, Time>) closingHours.entrySet().iterator().next()).getValue() +
                "\n";
    }
}
