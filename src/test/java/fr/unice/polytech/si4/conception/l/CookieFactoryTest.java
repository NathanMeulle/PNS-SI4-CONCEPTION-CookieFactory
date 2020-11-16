package fr.unice.polytech.si4.conception.l;

import fr.unice.polytech.si4.conception.l.customer.Customer;
import fr.unice.polytech.si4.conception.l.exceptions.AlreadyCreatedException;
import fr.unice.polytech.si4.conception.l.products.Cookie;
import fr.unice.polytech.si4.conception.l.store.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class CookieFactoryTest {

    String name;
    String phoneNumber;
    String mail;
    SystemInfo systemInfo;
    Log log;
    private Cookie cookieMock;
    private Store storeMock;
    private Customer customerMock;

    @BeforeEach
    void setUp() {

        name = "Esteve";
        phoneNumber = "0658601237";
        mail = "estevet@hotmail.fr";
        systemInfo = SystemInfo.getInstance();
        log = new Log();

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

        Customer customerSubscribe = systemInfo.getCustomerByMail(mail);

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
            System.out.println(systemInfo.getCustomers());
            systemInfo.addCustomer(new Customer(nameCustomer1, phoneCustomer1, mailCustomer1));
            System.out.println(systemInfo.getCustomers());

        } catch (AlreadyCreatedException e) {
            e.printStackTrace();
        }

        assertEquals(nameCustomer1, systemInfo.getCustomerByMail(mailCustomer1).getName());
        assertEquals(phoneCustomer1, systemInfo.getCustomerByMail(mailCustomer1).getPhoneNumber());
        assertEquals(mailCustomer1, systemInfo.getCustomerByMail(mailCustomer1).getMail());

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

        assertEquals(nameCustomer, systemInfo.getCustomerByTel(phoneCustomer).getName());
        assertEquals(phoneCustomer, systemInfo.getCustomerByTel(phoneCustomer).getPhoneNumber());
        assertEquals(mailCustomer, systemInfo.getCustomerByTel(phoneCustomer).getMail());
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
