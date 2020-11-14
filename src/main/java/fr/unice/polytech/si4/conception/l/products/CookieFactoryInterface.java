package fr.unice.polytech.si4.conception.l.products;

import fr.unice.polytech.si4.conception.l.products.composition.Cooking;
import fr.unice.polytech.si4.conception.l.products.composition.Dough;
import fr.unice.polytech.si4.conception.l.products.composition.Ingredient;
import fr.unice.polytech.si4.conception.l.products.composition.Mix;

import java.util.List;

/**
 * Interface de la CookieFactory
 * @author Nathan
 * @Patern Factory
 */
public interface CookieFactoryInterface {

    Cookie createCookie(String name, List<Ingredient> ingredients, Dough dough, Mix mix, Cooking cooking);

    Cookie createPersonnalizedCookie(String name,List<Ingredient> ingredients, Dough dough, Mix mix, Cooking cooking);

}
