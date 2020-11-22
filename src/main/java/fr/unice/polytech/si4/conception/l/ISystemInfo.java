package fr.unice.polytech.si4.conception.l;

import fr.unice.polytech.si4.conception.l.products.Cookie;
import fr.unice.polytech.si4.conception.l.products.composition.Ingredient;
import fr.unice.polytech.si4.conception.l.store.Store;

import java.util.List;

/**
 * This interface is for the cookieFactory class, to enable access one
 * critical list.
 * implemented in CookieFactory and FactoryCustomerSide
 */
public interface ISystemInfo {

    List<Cookie> getCookies();

    List<Store> getStores();

    List<Ingredient> getIngredients();
}
