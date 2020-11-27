package fr.unice.polytech.si4.conception.l;

import fr.unice.polytech.si4.conception.l.customer.Customer;
import fr.unice.polytech.si4.conception.l.exceptions.*;
import fr.unice.polytech.si4.conception.l.products.Cookie;
import fr.unice.polytech.si4.conception.l.products.composition.*;
import fr.unice.polytech.si4.conception.l.store.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FactoryCustomerSideTest {

    Customer customer;
    FactoryCustomerSide catalogue;
    SystemInfo systemInfo;
    Ingredient ingredientOne;
    Ingredient ingredientTwo;
    Ingredient ingredientThree;

    Dough ingredientDoughMock1;
    Dough ingredientDoughMock2;
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

        ingredientDoughMock1 = mock(Dough.class);
        when(ingredientDoughMock1.getType()).thenReturn(IngredientType.DOUGH);
        ingredientDoughMock2 = mock(Dough.class);
        when(ingredientDoughMock2.getType()).thenReturn(IngredientType.DOUGH);
        ingredientFlavorMock1 = mock(Ingredient.class);
        when(ingredientFlavorMock1.getType()).thenReturn(IngredientType.FLAVOR);
        ingredientToppingMock1 = mock(Ingredient.class);
        when(ingredientToppingMock1.getType()).thenReturn(IngredientType.TOPPING);
        ingredientToppingMock2 = mock(Ingredient.class);
        when(ingredientToppingMock2.getType()).thenReturn(IngredientType.TOPPING);
        ingredientToppingMock3 = mock(Ingredient.class);
        when(ingredientToppingMock3.getType()).thenReturn(IngredientType.TOPPING);
        //ingredientList = List.of(ingredientDoughMock1, ingredientDoughMock2, ingredientFlavorMock1, ingredientToppingMock1, ingredientToppingMock2, ingredientToppingMock3);
        ingredientList = List.of(ingredientFlavorMock1, ingredientToppingMock1, ingredientToppingMock2, ingredientToppingMock3);
        systemInfo.addIngredient(ingredientList);
    }

    @Test
    void createCookieUsingProxy() throws AlreadyCreatedException, InvalidTypeIngredient, InvalidNumberIngredient, NotFindException, NoProxyAssociateException {
        customer.associateProxy(catalogue);
        String nameCookie = new String("myCookie");
        customer.createCookieUsingProxy(nameCookie, ingredientList, ingredientDoughMock1, Mix.MIXED, Cooking.CRUNCHY);
        Cookie cookieCreated = systemInfo.getCookieByName(nameCookie);
        assertEquals(nameCookie, cookieCreated.getName());
        assertEquals(ingredientList, cookieCreated.getIngredients());
        assertEquals(ingredientDoughMock1, cookieCreated.getDough());
        assertEquals(Mix.MIXED, cookieCreated.getMix());
        assertEquals(Cooking.CRUNCHY, cookieCreated.getCooking());
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

    @Test
    void getCookiesTest(){
        Cookie cookie = mock(Cookie.class);
        try {
            systemInfo.addCookie(cookie);
        } catch (AlreadyCreatedException e) {
            e.printStackTrace();
        }
        assertEquals(List.of(cookie), catalogue.getCookies());
    }

    @Test
    void getStoresTest(){
        Store store = mock(Store.class);
        try {
            systemInfo.addStore(store);
        } catch (AlreadyCreatedException e) {
            e.printStackTrace();
        }
        assertEquals(List.of(store), catalogue.getStores());
    }

    @Test
    void getIngredientTest(){
        assertEquals(ingredientList, catalogue.getIngredients());
    }
}
