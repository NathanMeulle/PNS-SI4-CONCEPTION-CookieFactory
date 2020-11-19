package fr.unice.polytech.si4.conception.l;

import fr.unice.polytech.si4.conception.l.customer.Customer;
import fr.unice.polytech.si4.conception.l.exceptions.AlreadyCreatedException;
import fr.unice.polytech.si4.conception.l.products.composition.Ingredient;
import fr.unice.polytech.si4.conception.l.products.composition.IngredientType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FactoryCustomerSideTest {

    Customer customer;
    FactoryCustomerSide catalogue;
    SystemInfo systemInfo;
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
    void setUp() throws AlreadyCreatedException {
        customer = new Customer("Charlie", "06", "charlie@mail.com");
        systemInfo = SystemInfo.getInstance();
        systemInfo.resetSystemInfo();
        catalogue = systemInfo.generateProxy();
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
        //TODO à revoir
        systemInfo.newIngredient(ingredientList);
    }

    @Test
    void getDoughTest() {
        List<Ingredient> doughList = catalogue.getDough();
        assertEquals(2, doughList.size());
        for(Ingredient i : doughList){
            assertEquals(IngredientType.DOUGH, i.getType());
        }
        systemInfo.resetSystemInfo();
    }

    @Test
    void getFlavorTest() {
        List<Ingredient> flavorList = catalogue.getFlavor();
        System.out.println(flavorList);
        assertEquals(1, flavorList.size());
        for(Ingredient i : flavorList){
            assertEquals(IngredientType.FLAVOR, i.getType());
        }
        systemInfo.resetSystemInfo();
    }

    @Test
    void getToppingTest() {
        List<Ingredient> toppingList = catalogue.getTopping();

        assertEquals(3, toppingList.size());
        for(Ingredient i : toppingList){
            assertEquals(IngredientType.TOPPING, i.getType());
        }
        systemInfo.resetSystemInfo();
    }

}
