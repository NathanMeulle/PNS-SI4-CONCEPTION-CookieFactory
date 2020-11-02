package fr.unice.polytech.si4.conception.l;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class OrderTest {
    Order order;
    Cookie cookieChocoMock;
    Cookie cookieVanilleMock;

    @BeforeEach
    public void setup() {
        AnonymousCustomer aCustomer = new AnonymousCustomer("Petrovitch","065065045");
        order = new Order();
        order.assignCustomer(aCustomer);
        cookieChocoMock = mock(Cookie.class);
        cookieVanilleMock = mock(Cookie.class);
    }


    @Test
    public void addCookieTest() {
        order.addCookie(cookieChocoMock, 1);
        assertEquals(1, order.getCookies().get(cookieChocoMock));
        assertEquals(1, order.getNbCookies());
    }

    @Test
    public void addSameCookieTest() {
        order.addCookie(cookieChocoMock, 1);
        order.addCookie(cookieChocoMock, 1);
        assertEquals(2, order.getCookies().get(cookieChocoMock));
        assertEquals(2, order.getNbCookies());
    }

    @Test
    public void addDifferentCookieTest() {
        order.addCookie(cookieChocoMock, 1);
        order.addCookie(cookieVanilleMock, 3);
        assertEquals(1, order.getCookies().get(cookieChocoMock));
        assertEquals(3, order.getCookies().get(cookieVanilleMock));
        assertEquals(4, order.getNbCookies());
    }
}
