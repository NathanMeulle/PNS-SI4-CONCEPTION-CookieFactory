/** Represents a recipe
 * @author Delmotte Vincent
 */
public class Recipe {

    protected Dough dough;
    protected Flavor flavor;
    protected Topping topping;
    protected Mix mix;
    protected Cooking cooking;

    /**
     * Creates a recipe for a specific cookie
     * @param dough Cookie have 1 dough
     * @param flavor Cookie have 1 flavor
     * @param topping Cookie can have up to 3 topping
     * @param mix Cookie have 1 mix
     * @param cooking Cookie 1 cooking
     */
    public Recipe(Dough dough, Flavor flavor, Topping topping, Mix mix, Cooking cooking) {
        this.dough = dough;
        this.flavor = flavor;
        this.topping = topping;
        this.mix = mix;
        this.cooking = cooking;
    }

    public Dough getDough() {
        return dough;
    }

    public void setDough(Dough dough) {
        this.dough = dough;
    }

    public Flavor getFlavor() {
        return flavor;
    }

    public void setFlavor(Flavor flavor) {
        this.flavor = flavor;
    }

    public Topping getTopping() {
        return topping;
    }

    public void setTopping(Topping topping) {
        this.topping = topping;
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
