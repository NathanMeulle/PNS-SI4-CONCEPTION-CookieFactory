package fr.unice.polytech.si4.conception.l.stepdefs;

import fr.unice.polytech.si4.conception.l.SystemInfo;
import fr.unice.polytech.si4.conception.l.customer.Customer;
import fr.unice.polytech.si4.conception.l.products.composition.Ingredient;
import fr.unice.polytech.si4.conception.l.store.Kitchen;
import fr.unice.polytech.si4.conception.l.store.Manager;
import fr.unice.polytech.si4.conception.l.store.Store;
import io.cucumber.java8.En;

import static org.mockito.Mockito.mock;

public class CreatePersonalizedRecipeStepDef implements En {

    Customer jhon;
    Store store;
    Manager managerMock;
    SystemInfo systemInfo;
    Kitchen kitchen;
    Ingredient vanilla;
    Ingredient flour;


    public CreatePersonalizedRecipeStepDef() {

        ////////////////////////////// Given //////////////////////////////

        Given("^Client named \"([^\"]*)\" with a phone number \"([^\"]*)\" and with a mail \"([^\"]*)\"$", (String arg0, String arg1, String arg2) -> {
            jhon = new Customer(arg0, arg1, arg2);
        });

        And("^a store, located in \"([^\"]*)\" with a tax of \"([^\"]*)\"$", (String arg0, String arg1) -> {
            managerMock = mock(Manager.class);
            store = new Store(1, arg0, Double.parseDouble(arg1),"01", "mail", managerMock);
            kitchen = new Kitchen();
            kitchen.assignStore(store);
            store.setKitchen(kitchen);
            systemInfo = SystemInfo.getInstance();
            systemInfo.addStore(store);
        });

        /*
        And("an ingredient which is called \"([^\"]*)\" which is a \"FLAVOR\", costing (\\d+)", (String name, String type, Integer cost) -> {
            vanilla = new Ingredient(name, cost, IngredientType.valueOf(type));
        });
        And("another ingredient called \"([^\"]*)\" which is a \"DOUGH\", costing (\\d+)", (String name, String type, Integer cost) -> {
            flour = new Ingredient(name, cost, IngredientType.valueOf(type));
        });

        ////////////////////////////// Scenario_1 //////////////////////////////

        When("We add (\\d+) vanilla and (\\d+) wholemeal flour to the stock at store \"([^\"]*)\"", (Integer nbVanilla, Integer nbFlour, String storeLocation) -> {
            store = cookieFactory.getStorerByAddress(storeLocation);
            kitchen.incrementStock(vanilla, nbVanilla);
            kitchen.incrementStock(flour, nbFlour);
        });
         */




/*
  Background:
    Given A client named "Jhon" with a phone number "0658601237" and with a mail "jhon@gmail.com"
    And a store located in "Avignon" with a tax of "2.5"
    And an ingredient called "vanilla" which is a "FLAVOR", costing 4
    And another ingredient called "wholemeal flour" which is a "DOUGH", costing 2

  Scenario: Jhon create his recipe
    When Add 8 vanilla and 10 wholemeal flour to the stock at store "Avignon"
    And "Jhon" create his recipe named "HeavyVanilla" with the ingredients "vanilla" and "wholemeal flour"
    Then the recipe "HeavyVanilla" is present in the cookieFactory
    And the cookie "HeavyVanilla" is composed by the ingredients "vanilla" and "wholemeal flour"

  Scenario: Jhon order a Personalized cookie
    When "Jhon" order a Personalized cookie named "HeavyVanilla"
    Then the cookie cost 25% more than a standard cookie with the same ingredient
 */

    }
}
