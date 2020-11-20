package fr.unice.polytech.si4.conception.l.store.schedule;

public enum Day {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;

    public static Day getDayFromInt(int i) {
       return Day.values()[i];
    }
}
