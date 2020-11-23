package fr.unice.polytech.si4.conception.l;

import fr.unice.polytech.si4.conception.l.exceptions.InvalidNumberIngredient;
import fr.unice.polytech.si4.conception.l.exceptions.InvalidTypeIngredient;
import fr.unice.polytech.si4.conception.l.products.Cookie;
import fr.unice.polytech.si4.conception.l.products.CookieFactory;
import fr.unice.polytech.si4.conception.l.products.composition.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CookieTest {

    private Ingredient ingredient1;
    private Ingredient ingredient2;
    private Cookie cookie;
    private List<Ingredient> ingredients;
    private CookieFactory cookieFactory;
    private Ingredient chocolateFlavor;
    private Ingredient vanillaFlavor;
    private Ingredient kitkatTopping;
    private Ingredient mnmsTopping;
    private Ingredient daimTopping;
    private Ingredient speculosTopping;



    @BeforeEach
    void setup() throws InvalidNumberIngredient, InvalidTypeIngredient {
        cookieFactory = new CookieFactory();
        ingredient1 = new Ingredient("ingredient1", 3, IngredientType.FLAVOR);
        ingredient2 = new Ingredient("ingredient2", 4, IngredientType.TOPPING);

        ingredients = new ArrayList<>();

        chocolateFlavor = new Ingredient("vanille", 1, IngredientType.FLAVOR);
        vanillaFlavor = new Ingredient("chocolate", 1, IngredientType.FLAVOR);
        mnmsTopping = new Ingredient("mnms", 1, IngredientType.TOPPING);
        kitkatTopping = new Ingredient("kitkat", 1, IngredientType.TOPPING);
        daimTopping = new Ingredient("daim", 1, IngredientType.TOPPING);
        speculosTopping = new Ingredient("speculos", 1, IngredientType.TOPPING);
        cookie = cookieFactory.createDefaultCookie("", ingredients, new Dough("plain", 1), Mix.MIXED, Cooking.CRUNCHY);
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

    @Test
    void checkIngredientsValid() {
        cookie.getIngredients().clear();
        cookie.getIngredients().add(chocolateFlavor);
        cookie.getIngredients().add(mnmsTopping);
        assertDoesNotThrow(() -> cookie.checkIngredients());
    }

    @Test
    void checkIngredientsInvalidType() {
        cookie.getIngredients().clear();
        cookie.getIngredients().add(chocolateFlavor);
        cookie.getIngredients().add(mnmsTopping);
        cookie.getIngredients().add(new Ingredient("mauvaisType", 1, IngredientType.DOUGH));
        assertThrows(InvalidTypeIngredient.class, () -> cookie.checkIngredients());
    }

    @Test
    void checkIngredientsInvalidNumberFlavor() {
        cookie.getIngredients().clear();
        cookie.getIngredients().add(chocolateFlavor);
        cookie.getIngredients().add(vanillaFlavor);
        cookie.getIngredients().add(mnmsTopping);
        assertThrows(InvalidNumberIngredient.class, () -> cookie.checkIngredients());
    }

    @Test
    void checkIngredientsInvalidNumberTopping() {
        cookie.getIngredients().clear();
        cookie.getIngredients().add(chocolateFlavor);
        cookie.getIngredients().add(kitkatTopping);
        cookie.getIngredients().add(mnmsTopping);
        cookie.getIngredients().add(daimTopping);
        cookie.getIngredients().add(speculosTopping);
        assertThrows(InvalidNumberIngredient.class, () -> cookie.checkIngredients());
    }

}
