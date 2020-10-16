package fr.unice.polytech.si4.conception.l;


/**
 * Class Manager, responsible for a store
 * */
public class Manager {

    final private int idManager;
    final private String name;
    private Store store;

    public Manager(int idManager, String name) {
        this.idManager = idManager;
        this.name = name;
    }

    /** ********************************************************************************
     *                               GETTERS / SETTERS
     *  ********************************************************************************
     */
    public int getIdManager() {
        return idManager;
    }
    public String getName() {
        return name;
    }

    /** ********************************************************************************
     *                                    METHODS
     *  ********************************************************************************
     */
    public void assignStore(Store store) {
        this.store = store;
    }







}
