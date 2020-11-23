package fr.unice.polytech.si4.conception.l.products;


import fr.unice.polytech.si4.conception.l.exceptions.InvalidNumberIngredient;
import fr.unice.polytech.si4.conception.l.exceptions.InvalidTypeIngredient;
import fr.unice.polytech.si4.conception.l.exceptions.NotContainsIngredient;
import fr.unice.polytech.si4.conception.l.products.composition.*;

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
    Cookie(String name, CookieType cookieType, List<Ingredient> ingredients, Dough dough, Mix mix, Cooking cooking) throws InvalidNumberIngredient, InvalidTypeIngredient {
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

    public Mix getMix() {
        return mix;
    }

    public Dough getDough() { return dough; }

    public void setMix(Mix mix) throws InvalidTypeIngredient {
        this.mix = mix;
        if (this.mix == null)  throw new InvalidTypeIngredient(" The cookie must have a mix type");
    }

    public Cooking getCooking() {
        return cooking;
    }

    public void setCooking(Cooking cooking) throws InvalidTypeIngredient {
        this.cooking = cooking;
        if (this.cooking == null) throw new InvalidTypeIngredient("The cookie must have a cooking type");
    }

    public void setDough(Dough dough) throws InvalidTypeIngredient {
        this.dough = dough;
        if (this.dough == null) throw new InvalidTypeIngredient(" The cookie must have a dough type");
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) throws InvalidNumberIngredient, InvalidTypeIngredient {
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
    public int hashCode() {
        return Objects.hash(getName(), getPrice(), getMix(), getCooking(), getIngredients());
    }

}
