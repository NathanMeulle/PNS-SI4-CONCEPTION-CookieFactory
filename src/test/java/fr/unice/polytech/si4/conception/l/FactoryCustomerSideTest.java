package fr.unice.polytech.si4.conception.l;

import com.google.common.collect.ImmutableList;

import fr.unice.polytech.si4.conception.l.cookie.composition.IngredientType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FactoryCustomerSideTest {

    Customer customer;
    FactoryCustomerSide catalogue;
    CookieFactory cookieFactory;
    Ingredient ingredientOne;
    Ingredient ingredientTwo;
    Ingredient ingredientThree;

    Ingredient ingredientDoughMock1;
    Ingredient ingredientDoughMock2;
    Ingredient ingredientFlavorMock1;
    Ingredient ingredientToppingMock1;
    Ingredient ingredientToppingMock2;
    Ingredient ingredientToppingMock3;
    List<Ingredient> ingredientList;

    @BeforeEach
    void setUp() {
        customer = new Customer("Charlie", "06", "charlie@mail.com");
        cookieFactory = CookieFactory.getInstance();
        cookieFactory.resetFactory();
        catalogue = cookieFactory.generateProxy();
        ingredientOne = new Ingredient("Banana", 2, IngredientType.FLAVOR);
        ingredientTwo = new Ingredient("Dough gluten free", 5, IngredientType.DOUGH);
        ingredientThree = new Ingredient("student's tears", 1, IngredientType.TOPPING);

        ingredientDoughMock1 = mock(Ingredient.class);
        when(ingredientDoughMock1.getType()).thenReturn(IngredientType.DOUGH);
        ingredientDoughMock2 = mock(Ingredient.class);
        when(ingredientDoughMock2.getType()).thenReturn(IngredientType.DOUGH);
        ingredientFlavorMock1 = mock(Ingredient.class);
        when(ingredientFlavorMock1.getType()).thenReturn(IngredientType.FLAVOR);
        ingredientToppingMock1 = mock(Ingredient.class);
        when(ingredientToppingMock1.getType()).thenReturn(IngredientType.TOPPING);
        ingredientToppingMock2 = mock(Ingredient.class);
        when(ingredientToppingMock2.getType()).thenReturn(IngredientType.TOPPING);
        ingredientToppingMock3 = mock(Ingredient.class);
        when(ingredientToppingMock3.getType()).thenReturn(IngredientType.TOPPING);
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
    void getDoughTest() {
        List<Ingredient> doughList = catalogue.getDough();
        assertEquals(2, doughList.size());
        for(Ingredient i : doughList){
            assertEquals(IngredientType.DOUGH, i.getType());
        }
    }

    @Test
    void getFlavorTest() {
        List<Ingredient> flavorList = catalogue.getFlavor();
        assertEquals(1, flavorList.size());
        for(Ingredient i : flavorList){
            assertEquals(IngredientType.FLAVOR, i.getType());
        }
    }

    @Test
    void getToppingTest() {
        List<Ingredient> toppingList = catalogue.getTopping();
        assertEquals(3, toppingList.size());
        for(Ingredient i : toppingList){
            assertEquals(IngredientType.TOPPING, i.getType());
        }
    }

    /*
    @Test
    void Test5() {
        cookieFactory.resetFactory();
        cookieFactory.newIngredient(ingredientOne);
        cookieFactory.newIngredient(ingredientTwo);
        cookieFactory.newIngredient(ingredientThree);
        System.out.println(cookieFactory.getIngredients());
        customer.accessCatalogue(cookieFactory.generateProxy());
        customer.createCookie();
        Cookie cookie = cookieFactory.getCookies().get(0);
        System.out.println(cookie);
    }



    @Test
    void Test4() {


        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(ingredientOne);
        ingredientList.add(ingredientTwo);
        ingredientList.add(ingredientThree);

        List<Ingredient> immutableList = Collections.unmodifiableList(ingredientList);

        List<Ingredient> firstList = immutableList;

        List<Ingredient> secondList = firstList;

        System.out.println(secondList);

        secondList.add(ingredientThree);


    }



    @Test
    void Test3() {
        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(ingredientOne);
        ingredientList.add(ingredientTwo);
        ingredientList.add(ingredientThree);

        List<Ingredient> immutableList = Collections.unmodifiableList(ingredientList);


        System.out.println(ingredientList);
        System.out.println(immutableList);

        Ingredient ing = immutableList.get(2);

        System.out.println(ing);
        ing.setType(IngredientType.FLAVOR);
        System.out.println(ing);



    }



    @Test
    void Test() {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(8);
        integerList.add(34);
        integerList.add(0);
        integerList.add(2);


        System.out.println(integerList);


    }

    @Test
    void Test2() {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(8);
        integerList.add(34);
        integerList.add(0);
        integerList.add(2);


        System.out.println(integerList);

        List<Integer> immutableList = List.copyOf(integerList);

        System.out.println(immutableList);

        integerList.remove(2);

        System.out.println(integerList);
        System.out.println(immutableList);

        integerList.add(676);
        integerList.add(6644);

        System.out.println(integerList);
        System.out.println(immutableList);

    }


     */


    @AfterEach
    void tearDown() {
    }
}