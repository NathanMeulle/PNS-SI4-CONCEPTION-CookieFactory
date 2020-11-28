package fr.unice.polytech.si4.conception.l.stepdefs;

import fr.unice.polytech.si4.conception.l.SystemInfo;
import fr.unice.polytech.si4.conception.l.customer.AnonymousCustomer;
import fr.unice.polytech.si4.conception.l.exceptions.ErrorPreparingOrder;
import fr.unice.polytech.si4.conception.l.order.StateOrder;
import fr.unice.polytech.si4.conception.l.products.Cookie;
import fr.unice.polytech.si4.conception.l.products.CookieFactory;
import fr.unice.polytech.si4.conception.l.products.composition.*;
import fr.unice.polytech.si4.conception.l.store.Kitchen;
import fr.unice.polytech.si4.conception.l.store.Manager;
import fr.unice.polytech.si4.conception.l.store.Store;
import io.cucumber.java8.En;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class KitchenBalancingStepdefs implements En {

    AnonymousCustomer toto;
    Manager managerMock;
    Store parisStore;
    Store toulouseStore;
    Store lyonStore;
    Store niceStore;
    Kitchen parisKitchen;
    Kitchen toulouseKitchen;
    Kitchen lyonKitchen;
    Kitchen niceKitchen;
    CookieFactory cookieFactory;
    SystemInfo systemInfo;
    Ingredient chocolate;
    Ingredient mnm;
    Cookie chocoCookie;
    Cookie mnmChocoCookie;
    List<Store> nearByStores;

    public KitchenBalancingStepdefs() {
        Given("^An anonymous client called \"([^\"]*)\"$", (String arg0) -> {
            toto = new AnonymousCustomer(arg0, "0612345678");
        });


        And("^four stores, one store in \"([^\"]*)\", one store in \"([^\"]*)\", one store in \"([^\"]*)\" and one store in \"([^\"]*)\"$", (String arg0, String arg1, String arg2, String arg3) -> {
            managerMock = mock(Manager.class);
            parisStore = new Store(1, arg0, 1.2, "01", "mail1",48.8710492, 2.3063295, managerMock);
            toulouseStore = new Store(2, arg1, 1.5, "02", "mail2",43.6057427, 1.4482542, managerMock);
            lyonStore = new Store(3, arg2, 1.0, "03", "mail3",45.7453212, 4.8491522, managerMock);
            niceStore = new Store(4, arg3, 0.8, "04", "mail4",43.7039332, 7.2656905, managerMock);

            parisKitchen = new Kitchen();
            toulouseKitchen = new Kitchen();
            lyonKitchen = new Kitchen();
            niceKitchen = new Kitchen();

            parisKitchen.assignStore(parisStore);
            toulouseKitchen.assignStore(toulouseStore);
            lyonKitchen.assignStore(lyonStore);
            niceKitchen.assignStore(niceStore);

            parisStore.setKitchen(parisKitchen);
            toulouseStore.setKitchen(toulouseKitchen);
            lyonStore.setKitchen(lyonKitchen);
            niceStore.setKitchen(niceKitchen);


            cookieFactory = new CookieFactory();
            systemInfo = SystemInfo.getInstance();
            systemInfo.resetSystemInfo();
            systemInfo.addStore(parisStore);
            systemInfo.addStore(toulouseStore);
            systemInfo.addStore(lyonStore);
            systemInfo.addStore(niceStore);
        });


        And("^an ingredient called Chocolate,costing (\\d+)$", (Integer arg0) -> {
            chocolate = new Ingredient("Chocolate", arg0, IngredientType.FLAVOR);
            systemInfo.addIngredient(List.of(chocolate));
        });

        And("^an ingredient called MnMs,costing (\\d+)$", (Integer arg0) -> {
            mnm = new Ingredient("MnM", arg0, IngredientType.TOPPING);
            systemInfo.addIngredient(List.of(mnm));
        });


        And("^A recipe \"([^\"]*)\" with ingredient \"([^\"]*)\"$", (String arg0, String arg1) -> {
            List<Ingredient> ingredients = new ArrayList<>();
            ingredients.add(chocolate);
            chocoCookie = cookieFactory.createDefaultCookie(arg0, ingredients, new Dough("plain", 1), Mix.TOPPED, Cooking.CRUNCHY);
            systemInfo.addCookie(chocoCookie);
        });


        And("^Another recipe \"([^\"]*)\" with ingredients \"([^\"]*)\" and \"([^\"]*)\"$", (String arg0, String arg1, String arg2) -> {
            List<Ingredient> ingredients = new ArrayList<>();
            ingredients.add(chocolate);
            ingredients.add(mnm);
            mnmChocoCookie = cookieFactory.createDefaultCookie(arg0, ingredients, new Dough("plain", 1), Mix.TOPPED, Cooking.CRUNCHY);
        });

        When("^Add (\\d+) Chocolate and (\\d+) MnMs to the stock at store located Paris$", (Integer arg0, Integer arg1) -> {
            parisKitchen.incrementStock(chocolate, arg0);
            parisKitchen.incrementStock(mnm, arg1);
        });

        And("^Add (\\d+) Chocolate and (\\d+) MnMs to the stock at store located Lyon$", (Integer arg0, Integer arg1) -> {
            lyonKitchen.incrementStock(chocolate, arg0);
            lyonKitchen.incrementStock(mnm, arg1);
        });
        Then("^Toto try to create an order of (\\d+) Choco and (\\d+) MnMChoco at store located Paris$", (Integer arg0, Integer arg1) -> {
            toto.createOrder(parisStore);
            toto.addCookie(chocoCookie, arg0);
            toto.addCookie(mnmChocoCookie, arg1);
            assertThrows(ErrorPreparingOrder.class, ()-> toto.submitOrder());
        });
        And("^As the store does not have enough ingredients, the latter offers a list of stores in order of proximity that can meet the needs\\.$", () -> {
            nearByStores = parisStore.storesNearby();
            assertFalse(nearByStores.isEmpty());
            assertEquals(3, nearByStores.size());
        });
        And("^The closest store is in \"([^\"]*)\"$", (String arg0) -> {
            assertEquals(nearByStores.get(0), lyonStore);
        });
        Then("^Toto choose a store located \"([^\"]*)\"$", (String arg0) -> {
            toto.changeStore(lyonStore);
            assertEquals(toto.getOrder().getStore(), lyonStore);
            assertDoesNotThrow(()->toto.submit());
        });
        And("^Toto has (\\d+) order$", (Integer arg0) -> {
            assertSame( StateOrder.COOKED, toto.getOrder().getStateOrder());
        });
    }
}
