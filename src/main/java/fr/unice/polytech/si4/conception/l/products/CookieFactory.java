package fr.unice.polytech.si4.conception.l.products;

import fr.unice.polytech.si4.conception.l.SystemInfo;
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
        checkIngredients(ingredients);
        return new DefaultCookie(name, ingredients, dough, mix, cooking);
    }

    @Override
    public Cookie createPersonnalizedCookie(String name, List<Ingredient> ingredients, Dough dough, Mix mix, Cooking cooking) throws InvalidNumberIngredient, InvalidTypeIngredient {
        checkIngredients(ingredients);
        return new PersonnalizedCookie(name, ingredients, dough, mix, cooking);
    }

    public void checkIngredients(List<Ingredient> ingredients) throws InvalidTypeIngredient, InvalidNumberIngredient {
        int cptFlavor = 0;
        int cptTopping = 0;
        if (ingredients.size() != 0) {
            for (Ingredient i : ingredients) {
                if(!SystemInfo.getInstance().getIngredients().contains(i))
                    throw new InvalidTypeIngredient("Ingredient does not exist in the Factory");
                if (i.getType().equals(IngredientType.FLAVOR)) {
                    cptFlavor += 1;
                } else if (i.getType().equals(IngredientType.TOPPING)) {
                    cptTopping += 1;
                } else {
                    throw new InvalidTypeIngredient("There is only Flavor and Topping in the list of ingredients");
                }
            }

            if (cptFlavor > 1) {
                throw new InvalidNumberIngredient("You can put a maximum of 1 flavor");
            }
            if (cptTopping > 3) {
                throw new InvalidNumberIngredient("You can put a maximum of 3 topping");
            }
        }

    }
}
