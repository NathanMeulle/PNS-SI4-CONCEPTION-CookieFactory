public class Store {

    final private int id;
    final private String adress;
    private double tax;
    private int phone;
    private String mail;
    private Manager manager;

    public Store(int id, String adress, double tax, int phone, String mail, Manager manager) {
        this.id = id;
        this.adress = adress;
        this.tax = tax;
        this.phone = phone;
        this.mail = mail;
        manager.attributStore(this);
        this.manager = manager;
    }

    /** ********************************************************************************
     *                               GETTERS / SETTERS
     *  ********************************************************************************
     */
    public int getId() {
        return id;
    }
    public String getAdress() {
        return adress;
    }
    public double getTax() {
        return tax;
    }
    public int getPhone() {
        return phone;
    }
    public String getMail() {
        return mail;
    }


    public void setTax(double tax) {
        this.tax = tax;
    }
    public void setPhone(int phone) {
        this.phone = phone;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
}
