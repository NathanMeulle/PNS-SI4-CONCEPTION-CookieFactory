package fr.unice.polytech.si4.conception.l;


import fr.unice.polytech.si4.conception.l.cookie.composition.Cooking;
import fr.unice.polytech.si4.conception.l.cookie.composition.Mix;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class OrderHistoryTest {

    Store store1;
    Store store2;
    Manager manager1Mock;
    Manager manager2Mock;
    Cookie cookie1;
    Cookie cookie2;
    Cookie cookie3;
    Cookie cookie4;
    OrderHistory orderHistory;
    Order order1;
    Order order2;
    Order order3;

    @BeforeEach
    void setup() {
        orderHistory = new OrderHistory();
        manager1Mock = mock(Manager.class);
        manager2Mock = mock(Manager.class);
        store1 = new Store(1, "nice", 1.2, "06", "mail", manager1Mock);
        store2 = new Store(1, "lyon", 1.2, "07", "mail", manager2Mock);
        cookie1 = mock(Cookie.class);
        cookie2 = mock(Cookie.class);
        cookie3 = mock(Cookie.class);
        cookie4 = mock(Cookie.class);
        order1 = mock(Order.class);
        order2 = mock(Order.class);
        order3 = mock(Order.class);
    }

    @Test
    void getRecentOrdersTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -20);
        Date date = calendar.getTime(); //On crée une date il y à 20 jours

        when(order1.getDate()).thenReturn(date);
        orderHistory.addOrder(order1);
        assertEquals(1, orderHistory.getRecentOrders().size());

    }

    @Test
    void getRecentOrdersTest2() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -29);
        Date date = calendar.getTime();

        when(order1.getDate()).thenReturn(date);
        orderHistory.addOrder(order1);
        assertEquals(1, orderHistory.getRecentOrders().size());
    }

    @Test
    void getRecentOrdersTest3() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -31);
        Date date = calendar.getTime();

        when(order1.getDate()).thenReturn(date);
        orderHistory.addOrder(order1);
        assertEquals(0, orderHistory.getRecentOrders().size());
    }

    @Test
    void getRecentOrdersTest4() {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DATE, -15);
        Date date1 = calendar1.getTime();

        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(Calendar.DATE, -20);
        Date date2 = calendar2.getTime();

        Calendar calendar3 = Calendar.getInstance();
        calendar3.add(Calendar.DATE, -31);
        Date date3 = calendar3.getTime();

        when(order1.getDate()).thenReturn(date1);
        when(order2.getDate()).thenReturn(date2);
        when(order3.getDate()).thenReturn(date3);
        orderHistory.addOrder(order1);
        orderHistory.addOrder(order2);
        orderHistory.addOrder(order3);

        assertEquals(List.of(order1, order2), orderHistory.getRecentOrders());
    }

    @Test
    void CountNationalCookieTest1() {
        Map<Cookie, Integer> cookies1 = new HashMap<>();
        cookies1.put(cookie1, 2);
        cookies1.put(cookie2, 1);
        when(order1.getCookies()).thenReturn(cookies1);
        when(order1.getStore()).thenReturn(store1);

        Map<Cookie, Integer> cookies2 = new HashMap<>();
        cookies2.put(cookie1, 2);
        cookies2.put(cookie2, 12);
        when(order2.getCookies()).thenReturn(cookies2);
        when(order2.getStore()).thenReturn(store2);

        assertEquals(4, orderHistory.countNationalCookie(List.of(order1, order2)).get(cookie1));
        assertEquals(13, orderHistory.countNationalCookie(List.of(order1, order2)).get(cookie2));
    }

    @Test
    void CountStoreCookieTest1() {
        Map<Cookie, Integer> cookies1 = new HashMap<>();
        cookies1.put(cookie1, 2);
        cookies1.put(cookie2, 1);
        when(order1.getCookies()).thenReturn(cookies1);
        when(order1.getStore()).thenReturn(store1);

        Map<Cookie, Integer> cookies2 = new HashMap<>();
        cookies2.put(cookie1, 2);
        cookies2.put(cookie2, 12);
        when(order2.getCookies()).thenReturn(cookies2);
        when(order2.getStore()).thenReturn(store2);

        assertEquals(2, orderHistory.countStoreCookie(List.of(order1, order2), store1).get(cookie1));
        assertEquals(12, orderHistory.countStoreCookie(List.of(order1, order2), store2).get(cookie2));
    }

    @Test
    void CountStoreCookieTest2() {
        Map<Cookie, Integer> cookies1 = new HashMap<>();
        cookies1.put(cookie1, 2);
        cookies1.put(cookie2, 1);
        when(order1.getCookies()).thenReturn(cookies1);
        when(order1.getStore()).thenReturn(store1);

        Map<Cookie, Integer> cookies2 = new HashMap<>();
        cookies2.put(cookie1, 2);
        cookies2.put(cookie2, 12);
        when(order2.getCookies()).thenReturn(cookies2);
        when(order2.getStore()).thenReturn(store1);

        assertEquals(4, orderHistory.countStoreCookie(List.of(order1, order2), store1).get(cookie1));
        assertEquals(13, orderHistory.countStoreCookie(List.of(order1, order2), store1).get(cookie2));
    }


    @Test
    void getNationalBestOfTest() {
        Map<Cookie, Integer> cookies1 = new HashMap<>();
        cookies1.put(cookie1, 3);
        cookies1.put(cookie2, 1);
        when(order1.getCookies()).thenReturn(cookies1);
        when(order1.getStore()).thenReturn(store1);

        Map<Cookie, Integer> cookies2 = new HashMap<>();
        cookies2.put(cookie3, 2);
        cookies2.put(cookie4, 1);
        when(order2.getCookies()).thenReturn(cookies2);
        when(order2.getStore()).thenReturn(store2);


        assertEquals(cookie1, orderHistory.getBestCookie(orderHistory.countNationalCookie(List.of(order1, order2))));
    }

    /**
     * Cas d'égalité
     */
    @Test
    void getNationalBestOfTest2() {
        Map<Cookie, Integer> cookies1 = new HashMap<>();
        cookies1.put(cookie1, 3);
        cookies1.put(cookie2, 1);
        when(order1.getCookies()).thenReturn(cookies1);
        when(order1.getStore()).thenReturn(store1);

        Map<Cookie, Integer> cookies2 = new HashMap<>();
        cookies2.put(cookie3, 2);
        cookies2.put(cookie4, 3);
        when(order2.getCookies()).thenReturn(cookies2);
        when(order2.getStore()).thenReturn(store2);

        when(cookie1.getPrice()).thenReturn(2.0);
        when(cookie4.getPrice()).thenReturn(1.0);

        assertEquals(cookie4, orderHistory.getBestCookie(orderHistory.countNationalCookie(List.of(order1, order2))));
    }





    /**
     * Cas Best of Cookie of a store
     */
    @Test
    void getStoreBestOfTest1() {
        Map<Cookie, Integer> cookies1 = new HashMap<>();
        cookies1.put(cookie1, 2);
        cookies1.put(cookie2, 7);
        when(order1.getCookies()).thenReturn(cookies1);
        when(order1.getStore()).thenReturn(store1);

        Map<Cookie, Integer> cookies2 = new HashMap<>();
        cookies2.put(cookie3, 2);
        cookies2.put(cookie4, 12);
        when(order2.getCookies()).thenReturn(cookies2);
        when(order2.getStore()).thenReturn(store2);

        when(cookie1.getPrice()).thenReturn(2.0);
        when(cookie4.getPrice()).thenReturn(1.0);

        assertEquals(cookie2, orderHistory.getBestCookie(orderHistory.countStoreCookie(List.of(order1, order2), store1)));
    }







}
