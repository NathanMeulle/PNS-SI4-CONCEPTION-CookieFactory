package fr.unice.polytech.si4.conception.l.products;

import fr.unice.polytech.si4.conception.l.exceptions.InvalidNumberIngredient;
import fr.unice.polytech.si4.conception.l.exceptions.InvalidTypeIngredient;
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

    Cookie createDefaultCookie(String name, List<Ingredient> ingredients, Dough dough, Mix mix, Cooking cooking) throws InvalidTypeIngredient, InvalidNumberIngredient;

    Cookie createPersonnalizedCookie(String name,List<Ingredient> ingredients, Dough dough, Mix mix, Cooking cooking) throws InvalidNumberIngredient, InvalidTypeIngredient;

}
