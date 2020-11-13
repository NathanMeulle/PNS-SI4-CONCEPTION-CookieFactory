package fr.unice.polytech.si4.conception.l;

import fr.unice.polytech.si4.conception.l.cookie.composition.IngredientType;
import fr.unice.polytech.si4.conception.l.exceptions.AlreadyCreatedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CookieFactoryTest {

    String name;
    String phoneNumber;
    String mail;
    CookieFactory cookieFactory;
    Log log;
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
        cookieFactory = CookieFactory.getInstance();
        log = new Log();
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
        cookieFactory.addStore(realStore1);
        cookieFactory.newIngredient(ingredient1);
        assertTrue(realStore1.getStock().containsKey(ingredient1));
    }

    @Test
    void newIngredientTestList() throws AlreadyCreatedException {
        cookieFactory.addStore(realStore1);
        cookieFactory.addStore(realStore2);
        List<Ingredient> listIngredient = new ArrayList<>();
        listIngredient.add(ingredient1);
        listIngredient.add(ingredient2);
        cookieFactory.newIngredient(listIngredient);
        assertTrue(realStore1.getStock().containsKey(ingredient1) && realStore1.getStock().containsKey(ingredient2));
        assertTrue(realStore2.getStock().containsKey(ingredient1) && realStore2.getStock().containsKey(ingredient2));
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
        cookieFactory.resetFactory();
        String nameCustomer1 = "name1";
        String phoneCustomer1 = "phone1";
        String mailCustomer1 = "mail1";
        try{
            System.out.println(cookieFactory.getCustomers());
            cookieFactory.addCustomer(new Customer(nameCustomer1, phoneCustomer1, mailCustomer1));
            System.out.println(cookieFactory.getCustomers());

        } catch (AlreadyCreatedException e){
            e.printStackTrace();
        }

        assertEquals(nameCustomer1, cookieFactory.getCustomerByMail(mailCustomer1).getName());
        assertEquals(phoneCustomer1, cookieFactory.getCustomerByMail(mailCustomer1).getPhoneNumber());
        assertEquals(mailCustomer1, cookieFactory.getCustomerByMail(mailCustomer1).getMail());

    }

    @Test
    void getCustomerByTel() {
        cookieFactory.resetFactory();
        String nameCustomer = "name";
        String phoneCustomer = "phone";
        String mailCustomer = "mail";
        Customer customer = new Customer(nameCustomer, phoneCustomer, mailCustomer);
        try{
            cookieFactory.addCustomer(customer);
        } catch (AlreadyCreatedException e){
            e.printStackTrace();
        }

        assertEquals(nameCustomer, cookieFactory.getCustomerByTel(phoneCustomer).getName());
        assertEquals(phoneCustomer, cookieFactory.getCustomerByTel(phoneCustomer).getPhoneNumber());
        assertEquals(mailCustomer, cookieFactory.getCustomerByTel(phoneCustomer).getMail());
    }


    @Test
    public void noCookieTest() {
        cookieFactory.resetFactory();
        assertEquals(0, cookieFactory.getCookies().size());
    }

    @Test
    public void addingCookieTest() {
        cookieFactory.resetFactory();
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
        cookieFactory.resetFactory();
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
        cookieFactory.resetFactory();
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
