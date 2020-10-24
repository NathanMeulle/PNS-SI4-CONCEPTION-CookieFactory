package fr.unice.polytech.si4.conception.l;

import fr.unice.polytech.si4.conception.l.exceptions.AlreadyCreatedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CookieFactoryTest {
    private CookieFactory cookieFactory;
    private List<Cookie> cookies;
    private List<Store> stores;
    private Cookie cookieMock;
    private Store storeMock;
    private Customer customerMock;


    @BeforeEach
    public void setup() {
        cookies = new ArrayList<>();
        stores = new ArrayList<>();
        cookieFactory = new CookieFactory(cookies, stores);
        cookieMock = mock(Cookie.class);
        storeMock = mock(Store.class);
        customerMock = mock(Customer.class);
    }


    @Test
    public void noCookieTest() {
        assertEquals(0, cookieFactory.getCookies().size());

    }

    @Test
    public void addingCookieTest() {
        try {
            cookieFactory.addCookie(cookieMock);
        } catch (AlreadyCreatedException e) {
            e.printStackTrace();
        }
        assertEquals(1, cookieFactory.getCookies().size());

    }

    @Test
    public void addingSameCookieTest() {
        try {
            cookieFactory.addCookie(cookieMock);
        } catch (AlreadyCreatedException e) {
            e.printStackTrace();
        }
        assertThrows(AlreadyCreatedException.class ,  () ->cookieFactory.addCookie(cookieMock));

    }

    @Test
    public void noStoreTest() {
        assertEquals(0, cookieFactory.getStores().size());

    }

    @Test
    public void addingStoreTest() {
        try {
            cookieFactory.addStore(storeMock);
        } catch (AlreadyCreatedException e) {
            e.printStackTrace();
        }
        assertEquals(1, cookieFactory.getStores().size());

    }

    @Test
    public void addingSameStoreTest() {
        try {
            cookieFactory.addStore(storeMock);
        } catch (AlreadyCreatedException e) {
            e.printStackTrace();
        }
        assertThrows(AlreadyCreatedException.class ,  () ->cookieFactory.addStore(storeMock));

    }

    @Test
    public void nCustomerTest() {
        assertEquals(0, cookieFactory.getCustomers().size());

    }

    @Test
    public void addingCustomerTest() {
        try {
            cookieFactory.addCustomer(customerMock);
        } catch (AlreadyCreatedException e) {
            e.printStackTrace();
        }
        assertEquals(1, cookieFactory.getCustomers().size());

    }

    @Test
    public void addingSameCustomerTest() {
        try {
            cookieFactory.addCustomer(customerMock);
        } catch (AlreadyCreatedException e) {
            e.printStackTrace();
        }
        assertThrows(AlreadyCreatedException.class ,  () ->cookieFactory.addCustomer(customerMock));

    }
}
