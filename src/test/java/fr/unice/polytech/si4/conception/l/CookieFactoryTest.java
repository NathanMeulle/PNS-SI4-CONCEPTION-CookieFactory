package fr.unice.polytech.si4.conception.l;

import fr.unice.polytech.si4.conception.l.customer.Customer;
import fr.unice.polytech.si4.conception.l.exceptions.AlreadyCreatedException;
import fr.unice.polytech.si4.conception.l.exceptions.InvalidNumberIngredient;
import fr.unice.polytech.si4.conception.l.exceptions.InvalidTypeIngredient;
import fr.unice.polytech.si4.conception.l.exceptions.NotFindException;
import fr.unice.polytech.si4.conception.l.products.Cookie;
import fr.unice.polytech.si4.conception.l.products.CookieFactory;
import fr.unice.polytech.si4.conception.l.products.composition.Ingredient;
import fr.unice.polytech.si4.conception.l.products.composition.IngredientType;
import fr.unice.polytech.si4.conception.l.store.Manager;
import fr.unice.polytech.si4.conception.l.store.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
    private Ingredient chocolateFlavor;
    private Ingredient vanillaFlavor;
    private Ingredient kitkatTopping;
    private Ingredient mnmsTopping;
    private Ingredient daimTopping;
    private Ingredient speculosTopping;
    private CookieFactory cookieFactory;

    @BeforeEach
    void setUp() {
        cookieFactory = new CookieFactory();
        name = "Esteve";
        phoneNumber = "0658601237";
        mail = "estevet@hotmail.fr";
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

        chocolateFlavor = new Ingredient("vanille", 1, IngredientType.FLAVOR);
        vanillaFlavor = new Ingredient("chocolate", 1, IngredientType.FLAVOR);
        mnmsTopping = new Ingredient("mnms", 1, IngredientType.TOPPING);
        kitkatTopping = new Ingredient("kitkat", 1, IngredientType.TOPPING);
        daimTopping = new Ingredient("daim", 1, IngredientType.TOPPING);
        speculosTopping = new Ingredient("speculos", 1, IngredientType.TOPPING);
        systemInfo.addIngredient(List.of(chocolateFlavor, vanillaFlavor, mnmsTopping, kitkatTopping, daimTopping, speculosTopping));
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
        assertEquals(1, systemInfo.getCustomers().size());

        assertEquals(name, customerSubscribe.getName());
        assertEquals(phoneNumber, customerSubscribe.getPhoneNumber());
        assertEquals(mail, customerSubscribe.getMail());
    }

    @Test
    void getCustomerByMail() {
        try {
            systemInfo.addCustomer(new Customer(name, phoneNumber, mail));
        } catch (AlreadyCreatedException e) {
            e.printStackTrace();
        }
        Customer c = null;
        try {
            c = systemInfo.getCustomerByMail(mail);
        } catch (NotFindException e) {
            e.printStackTrace();
        }
        assertNotNull(c);
        assertEquals(name, c.getName());
        assertEquals(phoneNumber, c.getPhoneNumber());
        assertEquals(mail, c.getMail());
    }

    @Test
    void getCustomerByTel() {
        try {
            systemInfo.addCustomer(new Customer(name, phoneNumber, mail));
        } catch (AlreadyCreatedException e) {
            e.printStackTrace();
        }
        Customer c = null;
        try {
            c = systemInfo.getCustomerByTel(phoneNumber);
        } catch (NotFindException e) {
            e.printStackTrace();
        }
        assertNotNull(c);
        assertEquals(name, c.getName());
        assertEquals(phoneNumber, c.getPhoneNumber());
        assertEquals(mail, c.getMail());
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

    @Test
    void checkIngredientsValid() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(chocolateFlavor);
        ingredients.add(mnmsTopping);
        assertDoesNotThrow(() -> cookieFactory.checkIngredients(ingredients));
    }

    @Test
    void checkIngredientsInvalidType() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(chocolateFlavor);
        ingredients.add(mnmsTopping);
        ingredients.add(new Ingredient("mauvaisType", 1, IngredientType.DOUGH));
        assertThrows(InvalidTypeIngredient.class, () -> cookieFactory.checkIngredients(ingredients));
    }

    @Test
    void checkIngredientsInvalidNumberFlavor() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(chocolateFlavor);
        ingredients.add(vanillaFlavor);
        ingredients.add(mnmsTopping);
        assertThrows(InvalidNumberIngredient.class, () -> cookieFactory.checkIngredients(ingredients));
    }

    @Test
    void checkIngredientsInvalidNumberTopping() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(chocolateFlavor);
        ingredients.add(kitkatTopping);
        ingredients.add(mnmsTopping);
        ingredients.add(daimTopping);
        ingredients.add(speculosTopping);
        assertThrows(InvalidNumberIngredient.class, () -> cookieFactory.checkIngredients(ingredients));
    }
}
