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
     * Method to know all ingredients necessary for an order
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
     * raise an exception if the ingredient is not in sufficient quantity
     */
    private void sufficientQuantity(Ingredient ingredient, int n) throws OutOfStockException {
        if (getQuantity(ingredient) < n) {
            throw new OutOfStockException(String.format("Ingredient : %s is out of stock", ingredient.getName()));
        }
    }

    public HashMap<Ingredient, Integer> getStock() {
        return stock;
    }

    public void setStock(HashMap<Ingredient, Integer> stock) {
        this.stock = stock;
    }


    /**
     * Get the quantity of an ingredient in the stock
     *
     * @param ingredient : ingredient to evaluate
     * @return : an int of the quantity of the ingredient
     */
    int getQuantity(Ingredient ingredient) {
        if (stock.containsKey(ingredient)) {
            return stock.get(ingredient);
        }
        return 0;
    }

    /**
     * Assign store to the kitchen
     *
     * @param store : store to assign
     * @return : an int of the quantity of the ingredient
     */
    public void assignStore(Store store){
        this.store = store;
    }

    /**
     * Method Overload
     * Add An Ingredient to stock
     *
     * @param ingredient : store to assign
     * @param n : number of ingredient
     */
    public void incrementStock(Ingredient ingredient, int n){
        if (!this.stock.containsKey(ingredient))
            this.stock.put(ingredient, n);
        else
            this.stock.replace(ingredient, getQuantity(ingredient) + n);

    }


    /**
     * Method Overload
     * Remove An Ingredient to stock
     *
     * @param ingredient : store to assign
     * @param n : number of ingredient
     */
    void decrementStock(Ingredient ingredient, int n){
        if(getQuantity(ingredient) > n)
            this.stock.put(ingredient, (getQuantity(ingredient) - n));
        else
            this.stock.put(ingredient, 0);
    }

    /**
     * Method Overload
     * Remove Ingredients in stock
     *
     * @param ingredientList : List of Ingredients
     */
    void decrementStock(HashMap<Ingredient, Integer> ingredientList){
        for (Ingredient i : ingredientList.keySet()) {
            this.stock.put(i, getQuantity(i) - ingredientList.get(i));
        }
    }

    /**
     * Method allowing to decrement the stock after the preparation of an order.
     * Remove Ingredients in stock
     *
     * @param cookieList : List of Ingredients
     */
    void prepareCookies(HashMap<Cookie, Integer> cookieList) {
        decrementStock(requireIngredients(cookieList));
    }

}


