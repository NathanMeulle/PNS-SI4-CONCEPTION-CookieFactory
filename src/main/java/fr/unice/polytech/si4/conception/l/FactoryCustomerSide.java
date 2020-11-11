package fr.unice.polytech.si4.conception.l;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.google.common.collect.ImmutableList;
import fr.unice.polytech.si4.conception.l.cookie.composition.IngredientType;

/**
 * This class is the Customer side of the CookieFactory, indeed the customer shouldn't
 * get access to all the value of the CookieFactory, so this class serves as a proxy
 */
public  class FactoryCustomerSide implements ICookieFactory{

    CookieFactory cookieFactory;
    List<Ingredient> ingredientList;

    public FactoryCustomerSide() {
        this.cookieFactory = CookieFactory.getInstance();
    }

    public void submitCookie(Cookie cookie){
        try {
            cookieFactory.addCookie(cookie);
        } catch (Exception e){}
    }

    @Override
    public List<Cookie> getCookies() {
        return Collections.unmodifiableList(cookieFactory.getCookies());
    }

    @Override
    public List<Store> getStores() {
        return Collections.unmodifiableList(cookieFactory.getStores());
    }

    @Override
    public List<Ingredient> getIngredients() {
        return Collections.unmodifiableList(cookieFactory.getIngredients());
    }

    /**
     * Method to obtain specifically the ingredient
     * of Dough type of the ingredientList
     * @return an unmodifiableList of Dough
     */
    public List<Ingredient> getDough() {
        return ingredientOfType(IngredientType.DOUGH);
    }

    /**
     * Method to obtain specifically the ingredient
     * of Flavor type of the ingredientList
     * @return an unmodifiableList of Flavor
     */
    public List<Ingredient> getFlavor() {
        return ingredientOfType(IngredientType.FLAVOR);
    }

    /**
     * Method to obtain specifically the ingredient
     * of Topping type of the ingredientList
     * @return an unmodifiableList of Topping
     */
    public List<Ingredient> getTopping() {
        return ingredientOfType(IngredientType.TOPPING);
    }

    /**
     * Method to get filter all the ingredients
     * corresponding to the ingredientType in param
     * @param ingredientType : IngredientType requested
     * @return a list of ingredient corresponding to the ingredientType
     */
    public List<Ingredient> ingredientOfType(IngredientType ingredientType){
        if(ingredientList == null){
            ingredientList = getIngredients();
        }

        List<Ingredient> listReturned = new ArrayList<>();
        for (Ingredient ingredient : ingredientList){
            if(ingredient.getType() == ingredientType){
                listReturned.add(ingredient);
            }
        }

        return Collections.unmodifiableList(listReturned);
    }

}
