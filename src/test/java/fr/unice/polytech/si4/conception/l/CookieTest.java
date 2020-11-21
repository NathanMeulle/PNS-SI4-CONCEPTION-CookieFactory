package fr.unice.polytech.si4.conception.l;

import fr.unice.polytech.si4.conception.l.exceptions.InvalidNumberIngredient;
import fr.unice.polytech.si4.conception.l.exceptions.InvalidTypeIngredient;
import fr.unice.polytech.si4.conception.l.products.Cookie;
import fr.unice.polytech.si4.conception.l.products.CookieFactory;
import fr.unice.polytech.si4.conception.l.products.composition.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CookieTest {

    private Ingredient ingredient1;
    private Ingredient ingredient2;
    private Cookie cookie;
    private List<Ingredient> ingredients;
    private CookieFactory cookieFactory;

    @BeforeEach
    void setup() {
        cookieFactory = new CookieFactory();
        ingredient1 = new Ingredient("ingredient1", 3, IngredientType.FLAVOR);
        ingredient2 = new Ingredient("ingredient2", 4, IngredientType.TOPPING);

        ingredients = new ArrayList<>();
    }


    @Test
    void calculPriceTest0() throws InvalidNumberIngredient, InvalidTypeIngredient {
        cookie = cookieFactory.createDefaultCookie("", ingredients, new Dough("plain", 1), Mix.MIXED, Cooking.CRUNCHY);
        assertEquals(0, cookie.getPrice());
    }

    @Test
    void calculPriceTest() throws InvalidNumberIngredient, InvalidTypeIngredient {
        ingredients.add(ingredient1);
        cookie = cookieFactory.createDefaultCookie("", ingredients, new Dough("plain", 1), Mix.MIXED, Cooking.CRUNCHY);
        assertEquals(4, cookie.getPrice());
    }

    @Test
    void calculPriceTest2() throws InvalidNumberIngredient, InvalidTypeIngredient {
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);
        cookie = cookieFactory.createDefaultCookie("", ingredients, new Dough("plain", 1), Mix.MIXED, Cooking.CRUNCHY);
        assertEquals(8, cookie.getPrice());
    }

    @Test
    void calculPriceTest3() throws InvalidNumberIngredient, InvalidTypeIngredient {
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);
        ingredients.add(ingredient2);
        cookie = cookieFactory.createDefaultCookie("", ingredients, new Dough("plain", 1), Mix.MIXED, Cooking.CRUNCHY);
        assertEquals(12, cookie.getPrice());
    }

}
