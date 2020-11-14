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
    public Cookie createDefaultCookie(String name, List<Ingredient> ingredients, Dough dough, Mix mix, Cooking cooking) {
        //TODO method check(...) qui vérifie nb correct d'ingrédient (à appeler ici et implementer dans DefaultCookie)
        return new DefaultCookie(name, ingredients, dough, mix, cooking);
    }

    @Override
    public Cookie createPersonnalizedCookie(String name, List<Ingredient> ingredients, Dough dough, Mix mix, Cooking cooking) {
        //TODO method check(...) qui vérifie que les ingrédients existent bien dans le SI + nb correct d'ingrédient (à appeler ici et implementer dans PersonnalizedCookie)
        return new PersonnalizedCookie(name, ingredients, dough, mix, cooking);
    }
}
