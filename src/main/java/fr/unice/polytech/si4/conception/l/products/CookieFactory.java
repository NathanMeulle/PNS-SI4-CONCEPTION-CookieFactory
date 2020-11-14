package fr.unice.polytech.si4.conception.l.products;

import fr.unice.polytech.si4.conception.l.products.composition.Cooking;
import fr.unice.polytech.si4.conception.l.products.composition.Dough;
import fr.unice.polytech.si4.conception.l.products.composition.Ingredient;
import fr.unice.polytech.si4.conception.l.products.composition.Mix;

import java.util.List;

/**
 * Implémentation de la CookieFactory
 * @author Nathan
 * @Patern Factory
 */

public class CookieFactory implements CookieFactoryInterface {
    @Override
    public Cookie createCookie(String name, List<Ingredient> ingredients, Dough dough, Mix mix, Cooking cooking) {
        return new DefaultCookie(name, ingredients, dough, mix, cooking);
    }

    @Override
    public Cookie createPersonnalizedCookie(String name, List<Ingredient> ingredients, Dough dough, Mix mix, Cooking cooking) {
        return new PersonnalizedCookie(name, ingredients, dough, mix, cooking);
    }
}
