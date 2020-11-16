package fr.unice.polytech.si4.conception.l;

import fr.unice.polytech.si4.conception.l.cookie.composition.Cooking;
import fr.unice.polytech.si4.conception.l.cookie.composition.IngredientType;
import fr.unice.polytech.si4.conception.l.cookie.composition.Mix;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class Customer extends AnonymousCustomer{


    private String mail;
    private boolean loyaltyProgramn;
    private int nbCookieOrdered;
    private FactoryCustomerSide catalogue;


    public Customer(String name, String phoneNumber, String mail) {
        super(name,phoneNumber);
        this.mail = mail;
        this.loyaltyProgramn = false;
        this.nbCookieOrdered = 0;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return getMail().equals(customer.getMail()) || getPhoneNumber().equals(customer.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getMail());
    }

    /** ********************************************************************************
     *                               Create cookie
     *  ********************************************************************************
     */

    /**
     * Method for that the customer could create a cookie. As the composition of the cookie depend of
     * the will of the customer, we determine a default comportment for this customer (For the tests).
     * By deafault this customer will choose the first ingredients available for each type (except
     * for the Topping ingredients where, if it's possible, he will choose the 3 first topping ingredients or
     * the 2 first topping ingredients, etc...). Also, he will choose the Mixed Mix and the Crunchy Cooking.
     * After that, the customer will submit his cookie to the cookieFactory by the FactoryCustomerSide (catalogue)
     * @param factoryCustomerSide : the proxy of the CookieFactory
     */
    public void createCookie(FactoryCustomerSide factoryCustomerSide){
        if(catalogue == null){
            catalogue = factoryCustomerSide;
        }
        Cookie cookie = new Cookie(nameChoice(), ingredientsChoice(), mixChoice(), cookingChoice());
        catalogue.submitCookie(cookie);
    }

    public List<Ingredient> ingredientsChoice(){

        List<Ingredient> ingredientsChoose = new ArrayList<>();
        if(catalogue.getDough().size() > 0) ingredientsChoose.add(doughChoice());
        if(catalogue.getFlavor().size() > 0) ingredientsChoose.add(flavorChoice());
        if(catalogue.getTopping().size() > 0) ingredientsChoose.addAll(toppingChoice());

        return ingredientsChoose;
    }

    private Ingredient doughChoice() {
        List<Ingredient> doughList = catalogue.getDough();
        ////////////////////////////// Choice first by default //////////////////////////////
        return doughList.get(0);
    }

    private Ingredient flavorChoice() {
        List<Ingredient> flavorList = catalogue.getFlavor();
        ////////////////////////////// Choice first by default //////////////////////////////
        return flavorList.get(0);
    }

    private List<Ingredient> toppingChoice() {
        List<Ingredient> toppingList = catalogue.getTopping();
        ////////////////////////////// Choice the 3 first by default //////////////////////////////
        if(toppingList.size() >= 3){
            return toppingList.subList(0,3);
        } else if(toppingList.size() >= 2){
            return toppingList.subList(0,2);
        } else {
            return toppingList.subList(0,1);
        }
    }

    private String nameChoice() {
        return new String("CookiePersoByDefault");
    }

    private Mix mixChoice() {
        ////////////////////////////// Choice MIXED by default //////////////////////////////
        return Mix.MIXED;
    }

    private Cooking cookingChoice() {
        ////////////////////////////// Choice MIXED by default //////////////////////////////
        return Cooking.CRUNCHY;
    }

    /** ********************************************************************************
     *                               GETTERS / SETTERS
     *  ********************************************************************************
     */

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public boolean isLoyaltyProgramn() {
        return loyaltyProgramn;
    }

    public void setLoyaltyProgramn(boolean loyaltyProgramn) {
        this.loyaltyProgramn = loyaltyProgramn;
    }

    public int getNbCookieOrdered() {
        return nbCookieOrdered;
    }

    public void setNbCookieOrdered(int nbCookieOrdered) {
        this.nbCookieOrdered = nbCookieOrdered;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "mail='" + mail + '\'' +
                ", loyaltyProgramn=" + loyaltyProgramn +
                ", nbCookieOrdered=" + nbCookieOrdered +
                '}';
    }
}
