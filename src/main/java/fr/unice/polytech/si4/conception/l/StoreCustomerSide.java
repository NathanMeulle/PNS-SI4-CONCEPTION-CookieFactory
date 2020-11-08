package fr.unice.polytech.si4.conception.l;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class is a proxy of the class Store.
 * It just give a visibility on the Stock in the real kitchen (which is placed in the store)
 */
public class StoreCustomerSide {


    private Store store;
    private Map<Ingredient, Integer> stock = new HashMap<>();
    // We create a new hashmap stock which is a copy of stock in kitchen

    public StoreCustomerSide(Store store) {
        this.store = store;
        updateStock();
        // We put the contain of stock in kitchen in the copy of stock in this class
        // With this when a customer would to access to the ingredient to create a cookie
        // he couldn't modify the real stock in kitchen
    }

    /**
     * This methode update the stock in this class
     * according to the stock in the kitchen of Store.
     * It is suppose to being use when a customer
     * wants to consult the ingredient.
     */
    public void updateStock() {

        stock = copy((HashMap<Ingredient, Integer>) store.getStock());

    }

    /**
     * This method return the stock of the kitchen.
     * As there is placed in StoreCustomerSide it return just a copy
     * of the real stock in the kitchen. Then the customer can't modify the real stock
     * @return a HashMap with the ingredients and their number in stock
     */
    public Map<Ingredient, Integer> consultStock() {
        updateStock();
        return stock;
    }

    /**
     * Surcharge of the copy constructor.
     * Indeed, the "classic" copy just make a shallow copy (copy by reference).
     * We need to do a deep copy to make sure that the Hashmap stock in this class is
     * a different entity from the stock in kitchen class.
     * @param original : The original HashMap<Ingredient, Integer> to copy.
     * @return A new HashMap<Ingredient, Integer> with the same value as the original but not reference with the original.
     */
    public static HashMap<Ingredient, Integer> copy(HashMap<Ingredient, Integer> original) {
        HashMap<Ingredient, Integer> copy = new HashMap<Ingredient, Integer>();
        for (Map.Entry<Ingredient, Integer> entry : original.entrySet()) {
            copy.put(entry.getKey(), entry.getValue());
        }

        return copy;
    }

}
