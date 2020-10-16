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
    public boolean canDo(List<Ingredient> Recipe) throws OutOfStock {

        for(Ingredient i : Recipe){

            try{
                this.sufficientQuantity(i);
            } catch (Exception e){
                e.getMessage();
            }
        }

        return true;
    }

    /**
     * Indicate if a ingredient is in sufficient quantity or not
     * @param ingredient : ingredient to evaluate
     * @return : a boolean with true if the ingredient is in sufficient quantity and false in contrary
     */
    void sufficientQuantity(Ingredient ingredient) throws OutOfStock {
        if(!(getQuantity(ingredient) > 0)){
            throw new OutOfStock(String.format("Ingredient : %s is out of stock", ingredient.getName()));
        }
    }

    /**
     * Get the quantity of an igredient in the stock
     * @param ingredient : ingredient to evaluate
     * @return : an int of the quantity of the ingredient
     */
    int getQuantity(Ingredient ingredient){
        return stock.get(ingredient);
    }
}
