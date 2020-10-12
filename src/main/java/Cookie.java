import cookie.composition.*;

import java.util.List;

/** Represents a Cookie
 * @author Delmotte Vincent
 */
public class Cookie {

    protected String name;
    protected int price;
    protected Mix mix;
    protected Cooking cooking;
    protected List<Ingredient> ingredients;

    /**
     * Creates a cookie with a name and a price
     * @param name cookie's name
     */
    public Cookie(String name) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
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
}
