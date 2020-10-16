package fr.unice.polytech.si4.conception.l;

import java.util.ArrayList;
import java.util.List;

/**
 * Generate and display all the event off the process
 * @author Nathan Meulle, Delmotte Vincent
 */

public class Log {

    protected static final List<String> myLog = new ArrayList<>();

    Log() {}


    public static void add(String s){
        myLog.add(s);
    }

    public static List<String> sortie() {
        return myLog;
    }

    public static void display() {
        for (String log : myLog) {
            System.out.println(log);
        }
    }


}