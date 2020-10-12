import util.Schedule.*;

public class Store {

    final private int id;
    final private String address;
    private double tax;
    private String phone;
    private String mail;
    private Manager manager;
    private Schedule schedule;

    public Store(int id, String address, double tax, String phone, String mail, Manager manager) {
        this.id = id;
        this.address = address;
        this.tax = tax;
        this.phone = phone;
        this.mail = mail;
        manager.assignStore(this);
        this.manager = manager;
        this.schedule = new Schedule();
    }

    /** ********************************************************************************
     *                               GETTERS / SETTERS
     *  ********************************************************************************
     */
    public int getId() {
        return id;
    }
    public String getAdress() {
        return address;
    }
    public double getTax() {
        return tax;
    }
    public String getPhone() {
        return phone;
    }
    public String getMail() {
        return mail;
    }


    public void setTax(double tax) {
        this.tax = tax;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
}
