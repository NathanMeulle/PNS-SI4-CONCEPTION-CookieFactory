package fr.unice.polytech.si4.conception.l;

import fr.unice.polytech.si4.conception.l.cookie.composition.Cooking;
import fr.unice.polytech.si4.conception.l.cookie.composition.IngredientType;
import fr.unice.polytech.si4.conception.l.cookie.composition.Mix;
import fr.unice.polytech.si4.conception.l.exceptions.ErrorPreparingOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderTest {
    Order order;
    Cookie cookieChocoMock;
    Cookie cookieVanilleMock;
    Store storeMock;

    @BeforeEach
    void setup() {
        storeMock = mock(Store.class);
        when(storeMock.getTax()).thenReturn(1.0);
        AnonymousCustomer aCustomer = new AnonymousCustomer("Petrovitch", "065065045");
        order = new Order();
        order.setStore(storeMock);
        order.assignCustomer(aCustomer);
        cookieChocoMock = mock(Cookie.class);
        cookieVanilleMock = mock(Cookie.class);
    }


    @Test
    void addCookieTest() {
        order.addCookie(cookieChocoMock, 1);
        assertEquals(1, order.getCookies().get(cookieChocoMock));
        assertEquals(1, order.getNbCookies());
    }

    @Test
    void addSameCookieTest() {
        order.addCookie(cookieChocoMock, 1);
        order.addCookie(cookieChocoMock, 1);
        assertEquals(2, order.getCookies().get(cookieChocoMock));
        assertEquals(2, order.getNbCookies());
    }

    @Test
    void addDifferentCookieTest() {
        order.addCookie(cookieChocoMock, 1);
        order.addCookie(cookieVanilleMock, 3);
        assertEquals(1, order.getCookies().get(cookieChocoMock));
        assertEquals(3, order.getCookies().get(cookieVanilleMock));
        assertEquals(4, order.getNbCookies());
    }

    @Test
    void isAchievableTest() {
        Manager managerMock = mock(Manager.class);
        Store store = new Store(1, "", 1.2, "01", "mail", managerMock);

        Kitchen kitchen = new Kitchen();

        store.setKitchen(kitchen);
        Ingredient chocolate = new Ingredient("Chocolate", 4, IngredientType.FLAVOR);
        Ingredient mnm = new Ingredient("MnM", 7, IngredientType.FLAVOR);

        kitchen.incrementStock(chocolate, 5);
        kitchen.incrementStock(mnm, 3);

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(chocolate);
        ingredients.add(mnm);
        Cookie mnMChocoCookie = new Cookie("MnmsChoco", ingredients, Mix.TOPPED, Cooking.CRUNCHY);

        List<Ingredient> ingredients2 = new ArrayList<>();
        ingredients2.add(chocolate);
        Cookie chocoCookie = new Cookie("Choco", ingredients2, Mix.TOPPED, Cooking.CRUNCHY);

        AnonymousCustomer vincent = new AnonymousCustomer("vincent", "06");
        order = vincent.createOrder(store);
        order.addCookie(chocoCookie, 7);
        order.addCookie(mnMChocoCookie, 1);

        assertThrows(ErrorPreparingOrder.class, () -> vincent.makeOrder());

    }
}
