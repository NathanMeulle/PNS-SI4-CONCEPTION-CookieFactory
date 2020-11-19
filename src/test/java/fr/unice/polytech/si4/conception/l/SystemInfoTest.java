package fr.unice.polytech.si4.conception.l;

import fr.unice.polytech.si4.conception.l.customer.Customer;
import fr.unice.polytech.si4.conception.l.exceptions.AlreadyCreatedException;
import fr.unice.polytech.si4.conception.l.exceptions.NotFindException;
import fr.unice.polytech.si4.conception.l.products.Cookie;
import fr.unice.polytech.si4.conception.l.store.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SystemInfoTest {

    String name;
    String phoneNumber;
    String mail;
    SystemInfo systemInfo;
    private Cookie cookieMock;
    private Store storeMock;
    private Customer customerMock;

    @BeforeEach
    void setUp() {

        name = new String("Esteve");
        phoneNumber = new String("0658601237");
        mail = new String("estevet@hotmail.fr");
        systemInfo = SystemInfo.getInstance();
        systemInfo.resetSystemInfo();
        cookieMock = mock(Cookie.class);
        storeMock = mock(Store.class);
        customerMock = mock(Customer.class);
    }

    @Test
    void subscription() {
        try {
            systemInfo.subscription(name, phoneNumber, mail);
        } catch (AlreadyCreatedException ignored) {
        }

        assertEquals(1, systemInfo.getCustomers().size());

        Customer customerSubscribe = null;
        try {
            customerSubscribe = systemInfo.getCustomerByMail(mail);
        } catch (NotFindException e) {
            e.printStackTrace();
        }
        assertNotNull(customerSubscribe);
        assertEquals(name, customerSubscribe.getName());
        assertEquals(phoneNumber, customerSubscribe.getPhoneNumber());
        assertEquals(mail, customerSubscribe.getMail());
    }

    @Test
    void getCustomerByMail() {
        systemInfo.resetSystemInfo();
        String nameCustomer1 = "name1";
        String phoneCustomer1 = "phone1";
        String mailCustomer1 = "mail1";
        try {
            systemInfo.addCustomer(new Customer(nameCustomer1, phoneCustomer1, mailCustomer1));
        } catch (AlreadyCreatedException e) {
            e.printStackTrace();
        }
        Customer c = null;
        try {
            c = systemInfo.getCustomerByMail(mailCustomer1);
        } catch (NotFindException e) {
            e.printStackTrace();
        }
        assertNotNull(c);
        assertEquals(nameCustomer1, c.getName());
        assertEquals(phoneCustomer1, c.getPhoneNumber());
        assertEquals(mailCustomer1, c.getMail());

        assertThrows(NotFindException.class, () -> systemInfo.getCustomerByMail("null"));


    }

    @Test
    void getCustomerByTel() {
        systemInfo.resetSystemInfo();
        String nameCustomer = "name";
        String phoneCustomer = "phone";
        String mailCustomer = "mail";
        Customer customer = new Customer(nameCustomer, phoneCustomer, mailCustomer);
        try {
            systemInfo.addCustomer(customer);
        } catch (AlreadyCreatedException e) {
            e.printStackTrace();
        }
        Customer c = null;
        try {
            c = systemInfo.getCustomerByTel(phoneCustomer);
        } catch (NotFindException e) {
            e.printStackTrace();
        }
        assertNotNull(c);
        assertEquals(nameCustomer, c.getName());
        assertEquals(phoneCustomer,c.getPhoneNumber());
        assertEquals(mailCustomer, c.getMail());

        assertThrows(NotFindException.class, () -> systemInfo.getCustomerByTel("09"));

    }


    @Test
    void noCookieTest() {
        systemInfo.resetSystemInfo();
        assertEquals(0, systemInfo.getCookies().size());
    }

    @Test
    void addingCookieTest() {
        systemInfo.resetSystemInfo();

        try {
            systemInfo.addCookie(cookieMock);
        } catch (AlreadyCreatedException e) {
            e.printStackTrace();
        }
        assertEquals(1, systemInfo.getCookies().size());
    }

    @Test
    void addingSameCookieTest() {
        try {
            systemInfo.addCookie(cookieMock);
        } catch (AlreadyCreatedException e) {
            e.printStackTrace();
        }
        assertThrows(AlreadyCreatedException.class, () -> systemInfo.addCookie(cookieMock));

    }

    @Test
    void noStoreTest() {
        systemInfo.resetSystemInfo();
        assertEquals(0, systemInfo.getStores().size());
    }

    @Test
    void addingStoreTest() {
        try {
            systemInfo.addStore(storeMock);
        } catch (AlreadyCreatedException e) {
            e.printStackTrace();
        }
        assertEquals(1, systemInfo.getStores().size());

    }

    @Test
    void addingSameStoreTest() {
        try {
            systemInfo.addStore(storeMock);
        } catch (AlreadyCreatedException e) {
            e.printStackTrace();
        }
        assertThrows(AlreadyCreatedException.class, () -> systemInfo.addStore(storeMock));

    }

    @Test
    void nCustomerTest() {
        assertEquals(0, systemInfo.getCustomers().size());

    }

    @Test
    void addingCustomerTest() {
        systemInfo.resetSystemInfo();
        try {
            systemInfo.addCustomer(customerMock);
        } catch (AlreadyCreatedException e) {
            e.printStackTrace();
        }
        assertEquals(1, systemInfo.getCustomers().size());
    }

    @Test
    void addingSameCustomerTest() {
        try {
            systemInfo.addCustomer(customerMock);
        } catch (AlreadyCreatedException e) {
            e.printStackTrace();
        }
        assertThrows(AlreadyCreatedException.class, () -> systemInfo.addCustomer(customerMock));

    }
}
