package fr.unice.polytech.si4.conception.l;

import fr.unice.polytech.si4.conception.l.customer.AnonymousCustomer;
import fr.unice.polytech.si4.conception.l.customer.Customer;
import fr.unice.polytech.si4.conception.l.exceptions.ErrorPreparingOrder;
import fr.unice.polytech.si4.conception.l.order.Order;
import fr.unice.polytech.si4.conception.l.products.Cookie;
import fr.unice.polytech.si4.conception.l.products.CookieFactory;
import fr.unice.polytech.si4.conception.l.products.composition.*;
import fr.unice.polytech.si4.conception.l.store.Kitchen;
import fr.unice.polytech.si4.conception.l.store.Manager;
import fr.unice.polytech.si4.conception.l.store.Store;
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
    Manager managerMock;
    Store store;
    Kitchen kitchen;
    Ingredient chocolate;
    Ingredient mnm;
    List<Ingredient> ingredients;
    List<Ingredient> ingredients2;
    Cookie mnMChocoCookie;
    Cookie chocoCookie;
    AnonymousCustomer vincent;
    CookieFactory cookieFactory;



    @BeforeEach
    void setup() {
        cookieFactory = new CookieFactory();
        storeMock = mock(Store.class);
        when(storeMock.getTax()).thenReturn(1.0);

        AnonymousCustomer aCustomer = new AnonymousCustomer("Petrovitch", "065065045");
        cookieChocoMock = mock(Cookie.class);
        cookieVanilleMock = mock(Cookie.class);
        managerMock = mock(Manager.class);

        store = new Store(1, "", 1.2, "01", "mail", managerMock);

        kitchen = new Kitchen();
        kitchen.assignStore(store);
        store.setKitchen(kitchen);
        chocolate = new Ingredient("Chocolate", 4, IngredientType.FLAVOR);

        mnm = new Ingredient("MnM", 7, IngredientType.FLAVOR);

        kitchen.incrementStock(chocolate, 5);
        kitchen.incrementStock(mnm, 3);

        ingredients = new ArrayList<>();
        ingredients.add(chocolate);
        ingredients.add(mnm);
        mnMChocoCookie = cookieFactory.createDefaultCookie("MnmsChoco", ingredients, new Dough("plain", 0), Mix.TOPPED, Cooking.CRUNCHY);

        ingredients2 = new ArrayList<>();
        ingredients2.add(chocolate);
        chocoCookie = cookieFactory.createDefaultCookie("Choco", ingredients2, new Dough("plain", 0), Mix.TOPPED, Cooking.CRUNCHY);

        vincent = new AnonymousCustomer("vincent", "06");

    }


    @Test
    void addCookieTest() {
        vincent.createOrder(storeMock);
        vincent.addCookie(cookieChocoMock, 1);
        order = vincent.getOrder();
        assertEquals(1, order.getCookies().get(cookieChocoMock));
        assertEquals(1, order.getNbCookies());
    }

    @Test
    void addSameCookieTest() {
        vincent.createOrder(storeMock);
        vincent.addCookie(cookieChocoMock, 1);
        vincent.addCookie(cookieChocoMock, 1);
        order = vincent.getOrder();
        assertEquals(2, order.getCookies().get(cookieChocoMock));
        assertEquals(2, order.getNbCookies());
    }

    @Test
    void addDifferentCookieTest() {
        vincent.createOrder(storeMock);
        vincent.addCookie(cookieChocoMock, 1);
        vincent.addCookie(cookieVanilleMock, 3);
        order = vincent.getOrder();
        assertEquals(1, order.getCookies().get(cookieChocoMock));
        assertEquals(3, order.getCookies().get(cookieVanilleMock));
        assertEquals(4, order.getNbCookies());
    }

    @Test
    void isAchievableTest() {
        vincent.createOrder(store);
        vincent.addCookie(chocoCookie, 7);
        vincent.addCookie(mnMChocoCookie, 1);
        assertThrows(ErrorPreparingOrder.class, () -> vincent.makeOrder());
    }

    @Test
    void isAchievableTest2() {
        vincent.createOrder(store);
        vincent.addCookie(chocoCookie, 4);
        vincent.addCookie(mnMChocoCookie, 1);
        assertDoesNotThrow(() -> vincent.makeOrder());
    }

    @Test
    void isAchievableTest3() {
        vincent.createOrder(store);
        vincent.addCookie(chocoCookie, 4);
        vincent.addCookie(mnMChocoCookie, 2);
        assertThrows(ErrorPreparingOrder.class, () -> vincent.makeOrder());
    }

    @Test
    void calculatePriceTest(){
        when(storeMock.getTax()).thenReturn(1.2);
        vincent.createOrder(storeMock);
        vincent.addCookie(chocoCookie, 1);
        assertEquals(4.8, vincent.getPrice(), 0.01);

        vincent.addCookie(mnMChocoCookie, 1);
        assertEquals(18, vincent.getPrice(), 0.01);
    }

    @Test
    void calculatePriceTest2(){
        when(storeMock.getTax()).thenReturn(1.2);
        vincent.createOrder(storeMock);
        vincent.addCookie(chocoCookie, 7);
        vincent.addCookie(mnMChocoCookie, 4);

        assertEquals(86.4, vincent.getPrice(), 0.01);
    }

    @Test
    void loyaltyProgramTest() {
        Customer customer = new Customer("v", "06", "mail");
        customer.setLoyaltyProgram(true);
        when(storeMock.getTax()).thenReturn(1.2);
        customer.createOrder(storeMock);
        customer.addCookie(chocoCookie, 28);
        customer.addCookie(mnMChocoCookie, 4);

        assertEquals(168.48, customer.getPrice(), 0.01);

    }
}
