package fr.unice.polytech.si4.conception.l;

import java.util.List;

/**
 * This interface is for the cookieFactory class, to enable access one
 * critical list.
 * implemented in CookieFactory and FactoryCustomerSide
 */
public interface ICookieFactory {

    public List<Cookie> getCookies();

    public List<Store> getStores();

    public List<Ingredient> getIngredients();
}
