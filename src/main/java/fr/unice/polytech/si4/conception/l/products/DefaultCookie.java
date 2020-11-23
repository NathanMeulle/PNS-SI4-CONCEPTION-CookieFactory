package fr.unice.polytech.si4.conception.l.products;

import fr.unice.polytech.si4.conception.l.products.composition.Cooking;
import fr.unice.polytech.si4.conception.l.products.composition.Dough;
import fr.unice.polytech.si4.conception.l.products.composition.Ingredient;
import fr.unice.polytech.si4.conception.l.products.composition.Mix;

import java.util.List;

/**
 * Concrete Product created by the Factory
 * @author Nathan
 * @Patern Factory
 */

public class DefaultCookie extends Cookie {

    /**
     * Creates a cookie with :
     *
     * @param name        name of the cookie
     * @param ingredients list of ingredient FLAVOR or TOPPING
     * @param dough       a dough
     * @param mix         a mix  MIXED or TOPPED
     * @param cooking     a cooking     CRUNCHY or CHEWY
     */
    DefaultCookie(String name, List<Ingredient> ingredients, Dough dough, Mix mix, Cooking cooking) {
        super(name, CookieType.DEFAULT, ingredients, dough, mix, cooking);
    }
}
