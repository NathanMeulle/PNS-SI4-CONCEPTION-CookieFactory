package fr.unice.polytech.si4.conception.l.products;

import fr.unice.polytech.si4.conception.l.exceptions.InvalidNumberIngredient;
import fr.unice.polytech.si4.conception.l.exceptions.InvalidTypeIngredient;
import fr.unice.polytech.si4.conception.l.products.composition.*;

import java.util.List;

/**
 * Implémentation de la CookieFactory
 * @author Nathan
 * @Patern Factory
 */

public class CookieFactory implements CookieFactoryInterface {
    @Override
    public Cookie createDefaultCookie(String name, List<Ingredient> ingredients, Dough dough, Mix mix, Cooking cooking) throws InvalidTypeIngredient, InvalidNumberIngredient {
        return new DefaultCookie(name, ingredients, dough, mix, cooking);
    }

    @Override
    public Cookie createPersonnalizedCookie(String name, List<Ingredient> ingredients, Dough dough, Mix mix, Cooking cooking) throws InvalidNumberIngredient, InvalidTypeIngredient {
        return new PersonnalizedCookie(name, ingredients, dough, mix, cooking);
    }

    @Override
    public Cookie createPersonnalizedCookie(String name, Cookie cookie) throws InvalidNumberIngredient, InvalidTypeIngredient {
        return new PersonnalizedCookie(name, cookie);
    }



}
