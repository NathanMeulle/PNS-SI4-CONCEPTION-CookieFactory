package fr.unice.polytech.si4.conception.l;


import fr.unice.polytech.si4.conception.l.exceptions.OutOfStock;

import java.util.HashMap;

/**
 * Class representing the kitchen in charge of stocks, receiving orders and preparing cookies
 * @author Thibaut
 */
public class Kitchen {

    static private HashMap<Ingredient, Integer> stock;

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
    static public boolean achievableCookie(HashMap<Cookie, Integer> cookieList) {
        for (Cookie c : cookieList.keySet()) {           //On itère sur chaque cookie de la HashMap cookieList
            for (int i = 0; i < cookieList.get(c); i++) {  //Puis pour chaque cookie on boucle sur le nombre indiqué dans cookielist
                if (!canDo(c)) {      // On vérfie si les ingrédients du cookie sont disponibles en stock avec la méthode canDo
                    return false;   // Et on retourne false si ce n'est pas le cas.
                } // TODO : A CORRIGER : don't work : ici la quantité n'est pas décrémentée des stocks, on ne peut donc pas savoir si l'on peut faire 2 cookies
            }
        }
        return true;  //Sinon, on retourne true
    }

    /**
     * Indicate if all the ingredients of a cookie are present in the stock and if there are in quantity
     *
     * @param cookie : a cookie
     * @return a boolean with true if the recipe is feasible and false in contrary
     */
    static public boolean canDo(Cookie cookie) {
        for (Ingredient i : cookie.getIngredients()) {
            try {
                sufficientQuantity(i);
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
    static void sufficientQuantity(Ingredient ingredient) throws OutOfStock {
        if (getQuantity(ingredient) <= 0) {
            throw new OutOfStock(String.format("Ingredient : %s is out of stock", ingredient.getName()));
        }
    }

    /**
     * Get the quantity of an ingredient in the stock
     *
     * @param ingredient : ingredient to evaluate
     * @return : an int of the quantity of the ingredient
     */
    static int getQuantity(Ingredient ingredient) {
        return stock.get(ingredient);
    }
}



//TODO : other methods ?
