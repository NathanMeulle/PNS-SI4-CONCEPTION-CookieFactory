package fr.unice.polytech.si4.conception.l;

import fr.unice.polytech.si4.conception.l.*;
import fr.unice.polytech.si4.conception.l.cookie.composition.Cooking;
import fr.unice.polytech.si4.conception.l.cookie.composition.IngredientType;
import fr.unice.polytech.si4.conception.l.cookie.composition.Mix;
import fr.unice.polytech.si4.conception.l.exceptions.ErrorPreparingOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StoreCustomerSideTest {

    private Store store;
    private Kitchen kitchen;
    private Map<Ingredient, Integer> stock;
    private Ingredient ingredient1;
    private Ingredient ingredient2;
    private Ingredient ingredient3;
    private Manager managerMock;

    @BeforeEach
    void setup() {
        kitchen = new Kitchen();

        ingredient1 = new Ingredient("Chocolate", 4, IngredientType.FLAVOR);
        ingredient2 = new Ingredient("vanilla", 5, IngredientType.FLAVOR);
        ingredient3 = new Ingredient("Flour", 2, IngredientType.DOUGH);

        kitchen.incrementStock(ingredient1, 15);
        kitchen.incrementStock(ingredient2, 40);
        kitchen.incrementStock(ingredient3, 100);

        managerMock = mock(Manager.class);

        store = new Store(7985, "70 Rue du cormeau 84000 Avignon", 10.5, "0657868932", "mail", managerMock);
        store.setKitchen(kitchen);
    }


    /**
     * In this test we compare 2 HashMap<Ingredient, Integer> return by the method consultStock of StoreCustomerSide
     * and assert that there are no relation between (deep copy, not shallow copy).
     * For that we modify one of the HashMap (fakeStock) assert that the other has not been modified.
     * WARNING : do not work if the two HashMap have the same content.
     */
    @Test
    void consultStockTest() {
        StoreCustomerSide fakeStore = store.getHisProxy();
        Map<Ingredient, Integer> fakeStoke1 = fakeStore.consultStock();
        fakeStoke1.remove(ingredient1);

        Map<Ingredient, Integer> fakeStoke2 = fakeStore.consultStock();

        assertNotEquals(fakeStoke1, fakeStoke2);
    }

}
