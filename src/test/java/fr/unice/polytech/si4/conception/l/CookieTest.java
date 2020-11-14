package fr.unice.polytech.si4.conception.l;

import fr.unice.polytech.si4.conception.l.cookie.composition.Cooking;
import fr.unice.polytech.si4.conception.l.cookie.composition.Dough;
import fr.unice.polytech.si4.conception.l.cookie.composition.Mix;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CookieTest {

    private Ingredient ingredient1;
    private Ingredient ingredient2;
    private Cookie cookie;
    private List<Ingredient> ingredients;

    @BeforeEach
    void setup() {
        ingredient1 = mock(Ingredient.class);
        ingredient2 = mock(Ingredient.class);
        when(ingredient1.getPrice()).thenReturn(3);
        when(ingredient2.getPrice()).thenReturn(4);
        ingredients = new ArrayList<>();
    }


    @Test
    void calculPriceTest0() {
        cookie = new Cookie("", ingredients, new Dough("plain", 1), Mix.MIXED, Cooking.CRUNCHY);
        assertEquals(0, cookie.getPrice());
    }

    @Test
    void calculPriceTest() {
        ingredients.add(ingredient1);
        cookie = new Cookie("", ingredients, new Dough("plain", 1), Mix.MIXED, Cooking.CRUNCHY);
        assertEquals(4, cookie.getPrice());
    }

    @Test
    void calculPriceTest2() {
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);
        cookie = new Cookie("", ingredients, new Dough("plain", 1), Mix.MIXED, Cooking.CRUNCHY);
        assertEquals(8, cookie.getPrice());
    }

    @Test
    void calculPriceTest3() {
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);
        ingredients.add(ingredient2);
        cookie = new Cookie("", ingredients, new Dough("plain", 1), Mix.MIXED, Cooking.CRUNCHY);
        assertEquals(12, cookie.getPrice());
    }

}
