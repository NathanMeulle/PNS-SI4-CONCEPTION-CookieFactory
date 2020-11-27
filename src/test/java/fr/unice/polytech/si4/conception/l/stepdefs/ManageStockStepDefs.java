package fr.unice.polytech.si4.conception.l.stepdefs;

import fr.unice.polytech.si4.conception.l.Log;
import fr.unice.polytech.si4.conception.l.products.composition.Ingredient;
import fr.unice.polytech.si4.conception.l.products.composition.IngredientType;
import fr.unice.polytech.si4.conception.l.store.Kitchen;
import fr.unice.polytech.si4.conception.l.store.Manager;
import fr.unice.polytech.si4.conception.l.store.Store;
import io.cucumber.java8.En;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ManageStockStepDefs  implements En {
    private Store store;
    private Manager manager;
    private Kitchen kitchen;
    private Ingredient ingredient;
    private int nbIngredients;

    public ManageStockStepDefs() {
        Given("^a manager$", () -> {
            manager = new Manager(1, "Gerard");
        });

        And("^a factory with a store with his kitchen$", () -> {
            store = new Store(2, "a", 1.0, "06","mail.com",0,0, manager);
            kitchen = this.store.getKitchen();
            Log.add(kitchen.toString());

        });

        And("^an ingredient called \"([^\"]*)\"$", (String arg0) -> {
            ingredient = new Ingredient("Chocolate", 2, IngredientType.DOUGH);
        });

        When("^the manager asks for the number of ingredients in stock$", () -> {
            nbIngredients = kitchen.getStock().size();
        });
        Then("^There are (\\d+) Ingredient in the kitchen stock$", (Integer arg0) -> {
            assertEquals(arg0.intValue(), nbIngredients);
        });
        When("^the manager adds (\\d+) \"([^\"]*)\"$", (Integer arg0, String arg1) -> {
            kitchen.incrementStock(ingredient, arg0);
        });
        And("^the manager asks for the number of \"([^\"]*)\"$", (String arg0) -> {
            nbIngredients = kitchen.getStock().get(ingredient);
        });
        Then("^There are (\\d+) in his number of \"([^\"]*)\"$", (Integer arg0, String arg1) -> {
            assertEquals(arg0.intValue(), nbIngredients);
        });



    }
}
