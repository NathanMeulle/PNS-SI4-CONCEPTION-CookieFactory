public class Manager {

    final private int idManager;
    final private String name;
    private Store store;

    public Manager(int idManager, String name) {
        this.idManager = idManager;
        this.name = name;
    }

    /** ********************************************************************************
     *                                    GETTERS
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
