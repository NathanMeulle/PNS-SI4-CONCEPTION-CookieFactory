package fr.unice.polytech.si4.conception.l.products;


import fr.unice.polytech.si4.conception.l.products.composition.Cooking;
import fr.unice.polytech.si4.conception.l.products.composition.Dough;
import fr.unice.polytech.si4.conception.l.products.composition.Ingredient;
import fr.unice.polytech.si4.conception.l.products.composition.Mix;

import java.util.List;
import java.util.Objects;


/** Represents a Cookie
 * @author Delmotte Vincent
 * @author Nathan
 */
public abstract class Cookie {

    private String name;
    private double price;
    private CookieType cookieType;
    private Dough dough;
    private Mix mix;
    private Cooking cooking;
    private List<Ingredient> ingredients;


    /**
     * Creates a cookie with :
     * @param name name of the cookie
     * @param cookieType Default or Personnalized
     * @param ingredients  list of ingredient FLAVOR or TOPPING
     * @param dough a dough
     * @param mix a mix  MIXED or TOPPED
     * @param cooking a cooking     CRUNCHY or CHEWY
     */
    Cookie(String name, CookieType cookieType, List<Ingredient> ingredients, Dough dough, Mix mix, Cooking cooking) {
        this.name = name;
        this.cookieType = cookieType;
        this.ingredients = ingredients;
        this.dough = dough;
        this.price = calculPrice();
        this.mix = mix;
        this.cooking = cooking;
    }

    public int calculPrice() {
        if (this.ingredients.isEmpty())
            return 0;
        int priceTmp = 0;
        for (Ingredient i : this.ingredients) {
            priceTmp += i.getPrice();
        }
        priceTmp += this.dough.getPrice();
        return priceTmp;
    }

    /** ********************************************************************************
     *                               GETTERS / SETTERS
     *  ********************************************************************************
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Mix getMix() {
        return mix;
    }

    public void setMix(Mix mix) {
        this.mix = mix;
    }

    public Cooking getCooking() {
        return cooking;
    }

    public void setCooking(Cooking cooking) {
        this.cooking = cooking;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cookie)) return false;
        Cookie cookie = (Cookie) o;
        return getPrice() == cookie.getPrice() &&
                Objects.equals(getName(), cookie.getName()) &&
                getMix() == cookie.getMix() &&
                getCooking() == cookie.getCooking() &&
                Objects.equals(getIngredients(), cookie.getIngredients());
    }

    @Override
    public String toString() {
        return "Cookie{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", mix=" + mix +
                ", cooking=" + cooking +
                ", ingredients=" + ingredients +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPrice(), getMix(), getCooking(), getIngredients());
    }
}
