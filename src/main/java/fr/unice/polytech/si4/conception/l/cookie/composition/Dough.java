package fr.unice.polytech.si4.conception.l.cookie.composition;

import fr.unice.polytech.si4.conception.l.Ingredient;

public class Dough extends Ingredient {

    public Dough(String name, int price) {
        super(name, price, IngredientType.DOUGH);
    }
}
