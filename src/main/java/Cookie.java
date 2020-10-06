/** Represents a Cookie
 * @author Delmotte Vincent
 */
public class Cookie {

    protected String name;
    protected int price;
    protected Recipe recipe;

    /**
     * Creates a cookie with a name and a price
     * @param name cookie's name
     * @param price cookie"s price
     */
    public Cookie(String name, int price, Recipe recipe) {
        this.name = name;
        this.price = price;
        this.recipe = recipe;
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

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
