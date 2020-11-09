package fr.unice.polytech.si4.conception.l;

import fr.unice.polytech.si4.conception.l.exceptions.AlreadyCreatedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class CookieFactoryTest {

    String name;
    String phoneNumber;
    String mail;
    CookieFactory cookieFactory;
    private List<Cookie> cookies;
    private List<Store> stores;
    private Cookie cookieMock;
    private Store storeMock;
    private Customer customerMock;

    @BeforeEach
    void setUp() {
        name = "Esteve";
        phoneNumber = "0658601237";
        mail = "estevet@hotmail.fr";
        cookieFactory = new CookieFactory(null, null);
        cookies = new ArrayList<>();
        stores = new ArrayList<>();
        cookieFactory = new CookieFactory(cookies, stores);
        cookieMock = mock(Cookie.class);
        storeMock = mock(Store.class);
        customerMock = mock(Customer.class);
    }

    @Test
    void subscription() {
        try{
            cookieFactory.subscription(name, phoneNumber, mail);
        } catch (AlreadyCreatedException ignored){}

        assertEquals(1, cookieFactory.getCustomers().size());

        Customer customerSubscribe = cookieFactory.getCustomerByMail(mail);

        assertEquals(name, customerSubscribe.getName());
        assertEquals(phoneNumber, customerSubscribe.getPhoneNumber());
        assertEquals(mail, customerSubscribe.getMail());
    }

    @Test
    void getCustomerByMail() {
        try{
            cookieFactory.addCustomer(new Customer(name, phoneNumber, mail));
        } catch (AlreadyCreatedException e){
            e.printStackTrace();
        }

        assertEquals(name, cookieFactory.getCustomerByMail(mail).getName());
        assertEquals(phoneNumber, cookieFactory.getCustomerByMail(mail).getPhoneNumber());
        assertEquals(mail, cookieFactory.getCustomerByMail(mail).getMail());
    }

    @Test
    void getCustomerByTel() {
        try{
            cookieFactory.addCustomer(new Customer(name, phoneNumber, mail));
        } catch (AlreadyCreatedException e){
            e.printStackTrace();
        }

        assertEquals(name, cookieFactory.getCustomerByTel(phoneNumber).getName());
        assertEquals(phoneNumber, cookieFactory.getCustomerByTel(phoneNumber).getPhoneNumber());
        assertEquals(mail, cookieFactory.getCustomerByTel(phoneNumber).getMail());
    }

    @Test
    void noCookieTest() {
        assertEquals(0, cookieFactory.getCookies().size());

    }

    @Test
    void addingCookieTest() {
        try {
            cookieFactory.addCookie(cookieMock);
        } catch (AlreadyCreatedException e) {
            e.printStackTrace();
        }
        assertEquals(1, cookieFactory.getCookies().size());

    }

    @Test
    void addingSameCookieTest() {
        try {
            cookieFactory.addCookie(cookieMock);
        } catch (AlreadyCreatedException e) {
            e.printStackTrace();
        }
        assertThrows(AlreadyCreatedException.class ,  () ->cookieFactory.addCookie(cookieMock));

    }

    @Test
    void noStoreTest() {
        assertEquals(0, cookieFactory.getStores().size());

    }

    @Test
    void addingStoreTest() {
        try {
            cookieFactory.addStore(storeMock);
        } catch (AlreadyCreatedException e) {
            e.printStackTrace();
        }
        assertEquals(1, cookieFactory.getStores().size());

    }

    @Test
    void addingSameStoreTest() {
        try {
            cookieFactory.addStore(storeMock);
        } catch (AlreadyCreatedException e) {
            e.printStackTrace();
        }
        assertThrows(AlreadyCreatedException.class ,  () ->cookieFactory.addStore(storeMock));

    }

    @Test
    void nCustomerTest() {
        assertEquals(0, cookieFactory.getCustomers().size());

    }

    @Test
    void addingCustomerTest() {
        try {
            cookieFactory.addCustomer(customerMock);
        } catch (AlreadyCreatedException e) {
            e.printStackTrace();
        }
        assertEquals(1, cookieFactory.getCustomers().size());

    }

    @Test
    void addingSameCustomerTest() {
        try {
            cookieFactory.addCustomer(customerMock);
        } catch (AlreadyCreatedException e) {
            e.printStackTrace();
        }
        assertThrows(AlreadyCreatedException.class ,  () ->cookieFactory.addCustomer(customerMock));

    }
}
