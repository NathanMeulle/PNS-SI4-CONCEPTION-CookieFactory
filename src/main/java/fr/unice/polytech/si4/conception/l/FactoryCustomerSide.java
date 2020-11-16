package fr.unice.polytech.si4.conception.l;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.unice.polytech.si4.conception.l.products.Cookie;
import fr.unice.polytech.si4.conception.l.products.CookieFactory;
import fr.unice.polytech.si4.conception.l.products.composition.*;
import fr.unice.polytech.si4.conception.l.store.Store;

/**
 * This class is the Customer side of the CookieFactory, indeed the customer shouldn't
 * get access to all the value of the CookieFactory, so this class serves as a proxy
 */
public  class FactoryCustomerSide implements ISystemInfo {

    SystemInfo systemInfo;
    List<Ingredient> ingredientList;
    CookieFactory cookieFactory;

    public FactoryCustomerSide() {
        this.systemInfo = SystemInfo.getInstance();
        this.ingredientList = systemInfo.getIngredients();
        this.cookieFactory = new CookieFactory();
    }

    /**
     * Method to create a personnalized cookie for the customer using the cookie factory.
     * After created, this method submit the cookie to the SystemInfo by the submitCookie method
     * @param name : the name of the cookie
     * @param ingredientList : the list of ingredient (only ingredient of Topping or Flavor type)
     * @param dough : the dough of the cookie
     * @param mix : the mix of the cookie
     * @param cooking : the way of cooking of the cookie
     */
    public void createCookiePersonalized(String name, List<Ingredient> ingredientList, Dough dough, Mix mix, Cooking cooking){
        Cookie cookieCreated = cookieFactory.createPersonnalizedCookie(name, ingredientList, dough, mix, cooking);
        submitCookie(cookieCreated);
    }

    /**
     * Method to submit the cookie att the SystemInfo.
     * This method catch a AlreadyCreatedException if the cookie is already
     * created and add this exception to the Log.
     * @param cookie : The cookie to submit
     */
    private void submitCookie(Cookie cookie){
        try {
            systemInfo.addCookie(cookie);
        } catch (Exception e){
            Log.add(e.toString());
        }
    }

    @Override
    public List<Cookie> getCookies() {
        return List.copyOf(systemInfo.getCookies());
        //return Collections.unmodifiableList(systemInfo.getCookies());
    }

    @Override
    public List<Store> getStores() {
        return List.copyOf(systemInfo.getStores());
        //return Collections.unmodifiableList(systemInfo.getStores());
    }

    @Override
    public List<Ingredient> getIngredients() {
        return List.copyOf(systemInfo.getIngredients());
        //return Collections.unmodifiableList(systemInfo.getIngredients());
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

        List<Ingredient> listReturned = new ArrayList<>();
        for (Ingredient ingredient : ingredientList){
            if(ingredient.getType() == ingredientType){
                listReturned.add(ingredient);
            }
        }

        return List.copyOf(listReturned);
        //return Collections.unmodifiableList(listReturned);
    }

}