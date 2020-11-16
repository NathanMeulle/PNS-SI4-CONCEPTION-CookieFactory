package fr.unice.polytech.si4.conception.l;

import fr.unice.polytech.si4.conception.l.cookie.composition.Cooking;
import fr.unice.polytech.si4.conception.l.cookie.composition.IngredientType;
import fr.unice.polytech.si4.conception.l.cookie.composition.Mix;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class CustomerTest {

    private Customer customer1;
    private Customer customer2;
    private CookieFactory cookieFactory;

    Ingredient ingredientDoughMock1;
    Ingredient ingredientDoughMock2;
    Ingredient ingredientFlavorMock1;
    Ingredient ingredientToppingMock1;
    Ingredient ingredientToppingMock2;
    Ingredient ingredientToppingMock3;
    List<Ingredient> ingredientList;


    /**
     * Dès que des attributs (mail OU phone) sont identiques, les clients sont identiques
     */

    @BeforeEach
    void setUp(){

        customer1 = new Customer("Charlie", "06", "charlie@mail.com");
        cookieFactory = CookieFactory.getInstance();
        cookieFactory.resetFactory();

        ingredientDoughMock1 = mock(Ingredient.class);
        when(ingredientDoughMock1.getType()).thenReturn(IngredientType.DOUGH);
        when(ingredientDoughMock1.getPrice()).thenReturn(3);
        ingredientDoughMock2 = mock(Ingredient.class);
        when(ingredientDoughMock2.getType()).thenReturn(IngredientType.DOUGH);
        when(ingredientDoughMock2.getPrice()).thenReturn(1);
        ingredientFlavorMock1 = mock(Ingredient.class);
        when(ingredientFlavorMock1.getType()).thenReturn(IngredientType.FLAVOR);
        when(ingredientFlavorMock1.getPrice()).thenReturn(4);
        ingredientToppingMock1 = mock(Ingredient.class);
        when(ingredientToppingMock1.getType()).thenReturn(IngredientType.TOPPING);
        when(ingredientToppingMock1.getPrice()).thenReturn(5);
        ingredientToppingMock2 = mock(Ingredient.class);
        when(ingredientToppingMock2.getType()).thenReturn(IngredientType.TOPPING);
        when(ingredientToppingMock2.getPrice()).thenReturn(1);
        ingredientToppingMock3 = mock(Ingredient.class);
        when(ingredientToppingMock3.getType()).thenReturn(IngredientType.TOPPING);
        when(ingredientToppingMock3.getPrice()).thenReturn(7);
        ingredientList = new ArrayList<>();
        ingredientList.add(ingredientDoughMock1);
        ingredientList.add(ingredientDoughMock2);
        ingredientList.add(ingredientFlavorMock1);
        ingredientList.add(ingredientToppingMock1);
        ingredientList.add(ingredientToppingMock2);
        ingredientList.add(ingredientToppingMock3);

        cookieFactory.newIngredient(ingredientList);
    }

    @Test
    void createCookieDefaultTest() {
        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(ingredientDoughMock1);
        ingredientList.add(ingredientFlavorMock1);
        ingredientList.add(ingredientToppingMock1);
        ingredientList.add(ingredientToppingMock2);
        ingredientList.add(ingredientToppingMock3);
        Cookie cookieExpected = new Cookie("CookiePersoByDefault", ingredientList, Mix.MIXED, Cooking.CRUNCHY);

        customer1.createCookie(cookieFactory.generateProxy());
        Cookie cookieCreated = cookieFactory.getCookies().get(0);

        assertEquals(cookieExpected, cookieCreated);
    }


    @Test
    void equalTest() {
        customer1 = new Customer("Charlie", "06", "charlie@mail.com");
        customer2 = new Customer("Charlie", "06", "charlie@mail.com");
        assertEquals(customer1, customer2);
    }


    @Test
    void equalTest2() {
        customer1 = new Customer("Charlie", "06", "charlie@mail.com");
        customer2 = new Customer("Charlo", "06", "charlie@mail.com");
        assertEquals(customer1, customer2);
    }

    @Test
    void equalTest3() {
        customer1 = new Customer("Charlie", "06", "charlie@mail.com");
        customer2 = new Customer("Charlie", "07", "charlie@mail.com");
        assertEquals(customer1, customer2);
    }

    @Test
    void equalTest4() {
        customer1 = new Customer("Charlie", "06", "charlie@mail.com");
        customer2 = new Customer("Charlie", "06", "charlo@mail.com");
        assertEquals(customer1, customer2);
    }

    @Test
    void equalTest5() {
        customer1 = new Customer("Charlie", "06", "charlie@mail.com");
        customer2 = new Customer("Charlie", "07", "charlo@mail.com");
        assertNotEquals(customer1, customer2);
    }

}
