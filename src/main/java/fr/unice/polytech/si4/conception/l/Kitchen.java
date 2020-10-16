package fr.unice.polytech.si4.conception.l;

import jdk.jfr.Unsigned;

import java.util.HashMap;
import java.util.List;


public class Kitchen {

    private HashMap<Ingredient, Integer> stock;

    public Kitchen() {
        stock = new HashMap<>();
    }

    /**
     * Indicate if all the ingredient of a recipe are present in the stock and if there are in quantity
     * @param Recipe : the list of ingredients of the recipe
     * @return a boolean with true if the recipe is feasible and false in contrary
     */
    public boolean canDo(List<Ingredient> Recipe){

        for(Ingredient i : Recipe){
            if(!isPresent(i)) {         // If the ingredient is not present
                //!!! We warn that the recipe is not feasible !!!
                return false;
            }
        }

        return true;
    }

    /**
     * Indicate if a ingredient is present is the stock or not
     * @param ingredient : ingredient to evaluate
     * @return : a boolean with true if the ingredient is present and false in contrary
     */
    boolean isPresent(Ingredient ingredient){
        return stock.containsKey(ingredient);
    }

    int getQuantity(Ingredient ingredient){
        return stock.get(ingredient);
    }
}
