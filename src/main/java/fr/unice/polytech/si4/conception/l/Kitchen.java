package fr.unice.polytech.si4.conception.l;


import fr.unice.polytech.si4.conception.l.exceptions.OutOfStockException;

import java.util.HashMap;

/**
 * Class representing the kitchen in charge of stocks, receiving orders and preparing cookies
 * @author Thibaut
 */
public class Kitchen {

    private HashMap<Ingredient, Integer> stock;
    private Store store;

    public Kitchen() {
        stock = new HashMap<>();
    }

    /**
     * Method to know if all ingredients and their quantity
     *
     * @param cookieList : A hashmap representing the cookies to be realized with their number to realize
     * @return : a HasMap of all ingredients and the required quantity
     */
    private HashMap<Ingredient, Integer> requireIngredients(HashMap<Cookie, Integer> cookieList){
        HashMap<Ingredient, Integer> requests = new HashMap<>();
        for (Cookie c : cookieList.keySet()) {
            for (Ingredient i : c.getIngredients()) {
                requests.put(i, cookieList.get(c));
            }
        }
        return requests;
    }

    /**
     * Method to know if all cookies in an order are feasible or not
     *
     * @param cookieList : A hashmap representing the cookies to be realized with their number to realize
     * @return : a boolean indicating true if all cookies are feasible and false
     * if at least one cookie is not feasible
     */
    public boolean canDo(HashMap<Cookie, Integer> cookieList) {
        HashMap<Ingredient, Integer> ingredientsMap = requireIngredients(cookieList);
        for (Ingredient i : ingredientsMap.keySet()) {
            try {
                sufficientQuantity(i, ingredientsMap.get(i));
            } catch (Exception e) {
                Log.add(e.getMessage());
                return false;
            }
        }
        return true;
    }

    /**
     * Indicate if a ingredient is in sufficient quantity or not
     *
     * @param ingredient : ingredient to evaluate
     * @return : a boolean with true if the ingredient is in sufficient quantity and false in contrary
     */
    private void sufficientQuantity(Ingredient ingredient, int nbre) throws OutOfStockException {
        if (getQuantity(ingredient) < nbre) {
            throw new OutOfStockException(String.format("Ingredient : %s is out of stock", ingredient.getName()));
        }
    }

    void assignStore(Store store){
        this.store = store;
    }

    void prepareCookies(HashMap<Cookie, Integer> cookieList) {
        decrementStock(requireIngredients(cookieList));
    }

     void decrementStock(Ingredient ingredient, int nbre){
        if(this.stock.get(ingredient) > nbre)
            this.stock.put(ingredient, (this.stock.get(ingredient) - nbre));
        else
            this.stock.put(ingredient, 0);
    }

     void decrementStock(HashMap<Ingredient, Integer> ingredientList){
        for (Ingredient i : ingredientList.keySet()) {
            this.stock.put(i, this.stock.get(i) - ingredientList.get(i));
        }
    }

    public HashMap<Ingredient, Integer> getStock() {
        return stock;
    }

    public void setStock(HashMap<Ingredient, Integer> stock) {
        this.stock = stock;
    }

    void incrementStock(Ingredient ingredient, int n){
        if (!this.stock.containsKey(ingredient))
            this.stock.put(ingredient, n);
        else
            this.stock.replace(ingredient, this.stock.get(ingredient) + n);

    }

    void incrementStock(HashMap<Ingredient, Integer> ingredientList){
        for (Ingredient i : ingredientList.keySet()) {
            this.stock.put(i, this.stock.get(i) + ingredientList.get(i));
        }
    }

    /**
     * Get the quantity of an ingredient in the stock
     *
     * @param ingredient : ingredient to evaluate
     * @return : an int of the quantity of the ingredient
     */
    int getQuantity(Ingredient ingredient) {
        return stock.get(ingredient);
    }

    void cook(HashMap<Cookie, Integer> cookieList){
        HashMap<Ingredient, Integer> ingredientsMap = requireIngredients(cookieList);
        for (Ingredient i : ingredientsMap.keySet()) {
            decrementStock(i, ingredientsMap.get(i));
        }
    }

}


