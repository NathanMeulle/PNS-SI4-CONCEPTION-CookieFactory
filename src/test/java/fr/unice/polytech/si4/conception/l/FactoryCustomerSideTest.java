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

class FactoryCustomerSideTest {

    Customer customer;
    FactoryCustomerSide catalogue;
    CookieFactory cookieFactory;
    Ingredient ingredientOne;
    Ingredient ingredientTwo;
    Ingredient ingredientThree;

    @BeforeEach
    void setUp() {
        customer = new Customer("Charlie", "06", "charlie@mail.com");
        catalogue = new FactoryCustomerSide();
        cookieFactory = CookieFactory.getInstance();
        ingredientOne = new Ingredient("Banana", 2, IngredientType.FLAVOR);
        ingredientTwo = new Ingredient("Dough gluten free", 5, IngredientType.DOUGH);
        ingredientThree = new Ingredient("student's tears", 1, IngredientType.TOPPING);

    }

    @Test
    void Test5() {
        cookieFactory.resetFactory();
        cookieFactory.newIngredient(ingredientOne);
        cookieFactory.newIngredient(ingredientTwo);
        cookieFactory.newIngredient(ingredientThree);
        customer.accessCatalogue(cookieFactory.generateProxy());
        customer.createCookie();
        Cookie cookie = cookieFactory.getCookies().get(0);
        System.out.println(cookie);
    }

    /*
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

        List<Integer> immutableList = Collections.unmodifiableList(integerList);

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