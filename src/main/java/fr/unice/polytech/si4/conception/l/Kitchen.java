package fr.unice.polytech.si4.conception.l;


import fr.unice.polytech.si4.conception.l.exceptions.OutOfStockException;

import java.util.HashMap;

/**
 * Class representing the kitchen in charge of stocks, receiving orders and preparing cookies
 * @author Thibaut
 */
public class Kitchen {

    private HashMap<Ingredient, Integer> stock;

    public Kitchen() {
        stock = new HashMap<>();
    }


    /**
     * Method to know if all cookies in an order are feasible or not
     *
     * @param cookieList : A hashmap representing the cookies to be realized with their number to realize
     * @return : a boolean indicating true if all cookies are feasible and false
     * if at least one cookie is not feasible
     */
    public boolean achievableCookie(HashMap<Cookie, Integer> cookieList) {
        HashMap<Ingredient, Integer> requests = new HashMap<>();
        for (Cookie c : cookieList.keySet()) {
            for (Ingredient i : c.getIngredients()) {
                requests.put(i, cookieList.get(c));
            }
        }
        return canDo(requests);
    }

    /**
     * Indicate if all the ingredients of a cookie are present in the stock and if there are in quantity
     *
     * @param ingredientsMap : a cookie
     * @return a boolean with true if the recipe is feasible and false in contrary
     */
    public boolean canDo(HashMap<Ingredient, Integer> ingredientsMap) {
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
    void sufficientQuantity(Ingredient ingredient, int n) throws OutOfStockException {
        if (getQuantity(ingredient) < n) {
            throw new OutOfStockException(String.format("Ingredient : %s is out of stock", ingredient.getName()));
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

}



//TODO : other methods ?
