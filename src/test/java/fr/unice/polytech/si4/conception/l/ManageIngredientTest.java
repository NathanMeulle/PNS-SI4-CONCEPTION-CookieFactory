package fr.unice.polytech.si4.conception.l;

import fr.unice.polytech.si4.conception.l.cookie.composition.IngredientType;
import fr.unice.polytech.si4.conception.l.exceptions.AlreadyCreatedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class ManageIngredientTest {

    Log log;
    CookieFactory cookieFactory;
    Store storeOne;
    Store storeTwo;
    Store storeThree;
    Map<Ingredient, Integer> stockOne;
    Map<Ingredient, Integer> stockTwo;
    Map<Ingredient, Integer> stockThree;
    Manager manager;
    Ingredient ingredientOne;
    Ingredient ingredientTwo;
    Ingredient ingredientThree;

    @BeforeEach
    void setUp() throws AlreadyCreatedException {
        log = new Log();
        cookieFactory = CookieFactory.getInstance();
        manager = mock(Manager.class);
        storeOne = new Store(1, "add1", 3.6, "0683225177", "mail1", manager);
        storeTwo = new Store(2, "add2", 1.2, "0456758877", "mail2", manager);
        storeThree = new Store(3, "add3", 1.5, "0656489577", "mail3", manager);
        stockOne = storeOne.getStock();
        stockTwo = storeTwo.getStock();
        stockThree = storeThree.getStock();
        cookieFactory.addStore(storeOne);
        cookieFactory.addStore(storeTwo);
        cookieFactory.addStore(storeThree);
        ingredientOne = new Ingredient("Banana", 2, IngredientType.FLAVOR);
        ingredientTwo = new Ingredient("Dough gluten free", 5, IngredientType.DOUGH);
        ingredientThree = new Ingredient("student's tears", 1, IngredientType.TOPPING);
    }

    @Test
    void newIngredientTest() {
        cookieFactory.newIngredient(ingredientOne);

        assertTrue(stockOne.containsKey(ingredientOne));
        assertTrue(stockTwo.containsKey(ingredientOne));
        assertTrue(stockThree.containsKey(ingredientOne));

        cookieFactory.newIngredient(ingredientTwo);
        cookieFactory.newIngredient(ingredientThree);

        assertTrue(stockOne.containsKey(ingredientTwo) && stockOne.containsKey(ingredientThree));
        assertTrue(stockTwo.containsKey(ingredientTwo) && stockTwo.containsKey(ingredientThree));
        assertTrue(stockThree.containsKey(ingredientTwo) && stockThree.containsKey(ingredientThree));
    }

    @Test
    void newIngredientTestList() {
        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(ingredientOne);
        ingredientList.add(ingredientTwo);
        ingredientList.add(ingredientThree);

        cookieFactory.newIngredient(ingredientList);

        assertTrue(stockOne.containsKey(ingredientOne) && stockOne.containsKey(ingredientTwo) && stockOne.containsKey(ingredientThree));
        assertTrue(stockTwo.containsKey(ingredientOne) && stockTwo.containsKey(ingredientTwo) && stockTwo.containsKey(ingredientThree));
        assertTrue(stockThree.containsKey(ingredientOne) && stockThree.containsKey(ingredientTwo) && stockThree.containsKey(ingredientThree));

    }
}

