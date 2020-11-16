package fr.unice.polytech.si4.conception.l;

import fr.unice.polytech.si4.conception.l.customer.Customer;
import fr.unice.polytech.si4.conception.l.exceptions.AlreadyCreatedException;
import fr.unice.polytech.si4.conception.l.products.Cookie;
import fr.unice.polytech.si4.conception.l.products.composition.Ingredient;
import fr.unice.polytech.si4.conception.l.products.composition.IngredientType;
import fr.unice.polytech.si4.conception.l.store.Manager;
import fr.unice.polytech.si4.conception.l.store.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class CookieFactoryTest {

    String name;
    String phoneNumber;
    String mail;
    SystemInfo systemInfo;
    private List<Cookie> cookies;
    private List<Store> stores;
    private Cookie cookieMock;
    private Store storeMock;
    private Customer customerMock;
    private Ingredient ingredient1;
    private Ingredient ingredient2;
    private Store realStore1;
    private Store realStore2;

    @BeforeEach
    void setUp() {
        name = new String("Esteve");
        phoneNumber = new String("0658601237");
        mail = new String("estevet@hotmail.fr");
        systemInfo = SystemInfo.getInstance();
        systemInfo.resetSystemInfo();
        cookies = new ArrayList<>();
        stores = new ArrayList<>();
        cookieMock = mock(Cookie.class);
        storeMock = mock(Store.class);
        customerMock = mock(Customer.class);
        ingredient1 = new Ingredient("Nuts", 1, IngredientType.TOPPING);
        ingredient2 = new Ingredient("stanpe", 2, IngredientType.FLAVOR);
        //ingredientMock1 = mock(Ingredient.class);
        //ingredientMock2 = mock(Ingredient.class);
        realStore1 = new Store(1, "addr", 2, "00", "mail", mock(Manager.class));
        realStore2 = new Store(2, "ad", 3, "04", "email", mock(Manager.class));
    }

    @Test
    void newIngredientTest() throws AlreadyCreatedException {
        systemInfo.addStore(realStore1);
        systemInfo.newIngredient(ingredient1);
        assertTrue(realStore1.getStock().containsKey(ingredient1));
    }

    @Test
    void newIngredientTestList() throws AlreadyCreatedException {
        systemInfo.addStore(realStore1);
        systemInfo.addStore(realStore2);
        List<Ingredient> listIngredient = new ArrayList<>();
        listIngredient.add(ingredient1);
        listIngredient.add(ingredient2);
        systemInfo.newIngredient(listIngredient);
        assertTrue(realStore1.getStock().containsKey(ingredient1) && realStore1.getStock().containsKey(ingredient2));
        assertTrue(realStore2.getStock().containsKey(ingredient1) && realStore2.getStock().containsKey(ingredient2));
    }

    @Test
    void subscription() {
        try{
            systemInfo.subscription(name, phoneNumber, mail);
        } catch (AlreadyCreatedException ignored){}

        assertEquals(1, systemInfo.getCustomers().size());

        Customer customerSubscribe = systemInfo.getCustomerByMail(mail);

        assertEquals(name, customerSubscribe.getName());
        assertEquals(phoneNumber, customerSubscribe.getPhoneNumber());
        assertEquals(mail, customerSubscribe.getMail());
    }

    @Test
    void getCustomerByMail() {
        try{
            systemInfo.addCustomer(new Customer(name, phoneNumber, mail));
        } catch (AlreadyCreatedException e){
            e.printStackTrace();
        }

        assertEquals(name, systemInfo.getCustomerByMail(mail).getName());
        assertEquals(phoneNumber, systemInfo.getCustomerByMail(mail).getPhoneNumber());
        assertEquals(mail, systemInfo.getCustomerByMail(mail).getMail());
    }

    @Test
    void getCustomerByTel() {
        try{
            systemInfo.addCustomer(new Customer(name, phoneNumber, mail));
        } catch (AlreadyCreatedException e){
            e.printStackTrace();
        }

        assertEquals(name, systemInfo.getCustomerByTel(phoneNumber).getName());
        assertEquals(phoneNumber, systemInfo.getCustomerByTel(phoneNumber).getPhoneNumber());
        assertEquals(mail, systemInfo.getCustomerByTel(phoneNumber).getMail());
    }

    @Test
    void noCookieTest() {

        assertEquals(0, systemInfo.getCookies().size());

    }

    @Test
    void addingCookieTest() {
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
        assertThrows(AlreadyCreatedException.class ,  () -> systemInfo.addCookie(cookieMock));

    }

    @Test
    void noStoreTest() {
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
        assertThrows(AlreadyCreatedException.class ,  () -> systemInfo.addStore(storeMock));

    }

    @Test
    void nCustomerTest() {
        assertEquals(0, systemInfo.getCustomers().size());

    }

    @Test
    void addingCustomerTest() {
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
        assertThrows(AlreadyCreatedException.class ,  () -> systemInfo.addCustomer(customerMock));

    }
}
