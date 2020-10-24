package fr.unice.polytech.si4.conception.l;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class KitchenTest {
    Kitchen kitchen;
    Ingredient ingredient;
    List<Ingredient> ingredients;
    HashMap<Cookie, Integer> cookies;
    Cookie cookie;

    @BeforeEach
    public void setup() {
        kitchen = new Kitchen();
        ingredient = mock(Ingredient.class);
        cookie = mock(Cookie.class);
        cookies = new HashMap<>();
        ingredients = new ArrayList<>();
        ingredients.add(ingredient);
        when(cookie.getIngredients()).thenReturn(ingredients);
    }


    @Test
    public void incrementStockTest(){
        assertEquals(0, kitchen.getQuantity(ingredient));
        kitchen.incrementStock(ingredient, 0);
        assertEquals(0, kitchen.getQuantity(ingredient));
        kitchen.incrementStock(ingredient, 4);
        assertEquals(4, kitchen.getQuantity(ingredient));
        kitchen.incrementStock(ingredient, 5);
        assertEquals(9, kitchen.getQuantity(ingredient));
    }

    @Test
    public void decrementeStockTest(){
        kitchen.incrementStock(ingredient, 5);
        assertEquals(5, kitchen.getQuantity(ingredient));
        kitchen.decrementStock(ingredient, 3);
        assertEquals(2, kitchen.getQuantity(ingredient));
    }

    @Test
    public void canDoTest(){
        kitchen.incrementStock(ingredient, 4);
        cookies.put(cookie, 3);
        assertTrue(kitchen.canDo(cookies));
    }


    @Test
    public void canDoTest2(){
        kitchen.incrementStock(ingredient, 4);
        cookies.put(cookie, 4);
        assertTrue(kitchen.canDo(cookies));
    }

    @Test
    public void canNotDoTest(){
        kitchen.incrementStock(ingredient, 4);
        cookies.put(cookie, 5);
        assertFalse(kitchen.canDo(cookies));
    }

    @Test
    public void prepareCookiesTest(){
        kitchen.incrementStock(ingredient, 4);
        cookies.put(cookie, 2);
        kitchen.prepareCookies(cookies);
        assertEquals(2, kitchen.getQuantity(ingredient));

    }



}
