package fr.unice.polytech.si4.conception.l;

import fr.unice.polytech.si4.conception.l.exceptions.*;
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
    private Cookie cookie;

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
    public Cookie createCookiePersonalized(String name, List<Ingredient> ingredientList, Dough dough, Mix mix, Cooking cooking) throws InvalidTypeIngredient, InvalidNumberIngredient, AlreadyCreatedException {
        return cookieFactory.createPersonnalizedCookie(name, ingredientList, dough, mix, cooking);
    }

    @Override
    public List<Cookie> getCookies() {
        return List.copyOf(systemInfo.getCookies());
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
            if(ingredient.getType().equals(ingredientType)){
                listReturned.add(ingredient);
            }
        }
        return List.copyOf(listReturned);
    }

    public void  createCookiePersonalizedFromAnotherCookie(Cookie cookie) throws CookieDoesNotExist {
        for (Cookie c : getCookies()) {
            if (c.equals(cookie)) this.cookie = cookie;
        }
        throw new CookieDoesNotExist(" The Default Cookie selected does not exist ");
    }

    public void retire(Ingredient ingredient) throws NotContainsIngredient {
        if (this.cookie == null) return;
        if (this.cookie.getIngredients().contains(ingredient))
            this.cookie.getIngredients().remove(ingredient);
        else throw new NotContainsIngredient(" The Cookie must contain the ingredient");
    }

    public void add(Ingredient ingredient){
        this.cookie.getIngredients().add(ingredient);
    }

    public void changeCooking(Cooking cooking) throws InvalidTypeIngredient {
        this.cookie.setCooking(cooking);
    }

    public void changeMix(Mix mix) throws InvalidTypeIngredient {
        this.cookie.setMix(mix);
    }

    public void changeDough(Dough dough) throws InvalidTypeIngredient {
        this.cookie.setDough(dough);
    }

    public Cookie validCookie() throws AlreadyCreatedException, InvalidTypeIngredient, InvalidNumberIngredient {
        if (this.cookie == null) return null;
        return this.createCookiePersonalized(this.cookie.getName(), this.cookie.getIngredients(), this.cookie.getDough(), this.cookie.getMix(), this.cookie.getCooking());
    }
}
