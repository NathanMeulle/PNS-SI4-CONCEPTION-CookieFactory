package fr.unice.polytech.si4.conception.l;

import fr.unice.polytech.si4.conception.l.exceptions.InvalidNumberIngredient;
import fr.unice.polytech.si4.conception.l.exceptions.InvalidTypeIngredient;
import fr.unice.polytech.si4.conception.l.exceptions.NotFindException;
import fr.unice.polytech.si4.conception.l.products.Cookie;
import fr.unice.polytech.si4.conception.l.products.CookieFactory;
import fr.unice.polytech.si4.conception.l.products.composition.*;
import fr.unice.polytech.si4.conception.l.store.Store;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Thibaut
 * This class is the Customer side of the CookieFactory, indeed the customer shouldn't
 * get access to all the value of the CookieFactory, so this class serves as a proxy
 * @Patern Proxy
 */
public  class FactoryCustomerSide implements ISystemInfo {

    private SystemInfo systemInfo;
    private List<Ingredient> ingredientList;
    private CookieFactory cookieFactory;

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
    public Cookie createCookiePersonalized(String name, List<Ingredient> ingredientList, Dough dough, Mix mix, Cooking cooking) throws InvalidTypeIngredient, InvalidNumberIngredient {
       return cookieFactory.createPersonnalizedCookie(name, ingredientList, dough, mix, cooking);
    }


    public Cookie addIngredientToCookie(Cookie c, Ingredient ingredient) throws InvalidTypeIngredient, InvalidNumberIngredient {
        List<Ingredient> ingredients = c.getIngredients();
        ingredients.add(ingredient);
        return createCookiePersonalized(c.getName(), ingredients, c.getDough(), c.getMix(), c.getCooking());
    }

    public Cookie removeIngredientToCookie(Cookie c, Ingredient ingredient) throws InvalidTypeIngredient, InvalidNumberIngredient {
        List<Ingredient> ingredients = c.getIngredients();
        ingredients.remove(ingredient);
        return createCookiePersonalized(c.getName(), ingredients, c.getDough(), c.getMix(), c.getCooking());
    }

    @Override
    public List<Cookie> getCookies() {
        return List.copyOf(systemInfo.getCookies());
    }

    public Cookie getCookieByName(String name) throws NotFindException {
        for(Cookie cookie : getCookies()){
            if(cookie.getName().equals(name)){
                return cookie;
            }
        }
        throw new NotFindException("cookie not found");
    }


    @Override
    public List<Store> getStores() {
        return List.copyOf(systemInfo.getStores());
    }

    @Override
    public List<Ingredient> getIngredients() {
        return List.copyOf(systemInfo.getIngredients());
    }

    /**
     * Method to obtain specifically the ingredient
     * of Dough type of the ingredientList
     * @return an unmodifiableList of Dough
     */
    public List<Ingredient> getDough() {
        return getIngredientOfType(IngredientType.DOUGH);
    }

    /**
     * Method to obtain specifically the ingredient
     * of Flavor type of the ingredientList
     * @return an unmodifiableList of Flavor
     */
    public List<Ingredient> getFlavor() {
        return getIngredientOfType(IngredientType.FLAVOR);
    }

    /**
     * Method to obtain specifically the ingredient
     * of Topping type of the ingredientList
     * @return an unmodifiableList of Topping
     */
    public List<Ingredient> getTopping() {
        return getIngredientOfType(IngredientType.TOPPING);
    }

    /**
     * Method to get filter all the ingredients
     * corresponding to the ingredientType in param
     * @param ingredientType : IngredientType requested
     * @return a list of ingredient corresponding to the ingredientType
     */
    public List<Ingredient> getIngredientOfType(IngredientType ingredientType){
        List<Ingredient> listReturned = new ArrayList<>();
        for (Ingredient ingredient : ingredientList){
            if(ingredient.getType().equals(ingredientType)){
                listReturned.add(ingredient);
            }
        }
        return List.copyOf(listReturned);
    }

}
