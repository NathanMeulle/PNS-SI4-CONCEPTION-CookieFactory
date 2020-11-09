package fr.unice.polytech.si4.conception.l.stepdefs;

import io.cucumber.java8.En;

public class CookieOnDemandStepdefs implements En {
    public CookieOnDemandStepdefs() {
        Given("^a client named \"([^\"]*)\" with email \"([^\"]*)\"$", (String arg0, String arg1) -> {

        });
        When("^\"([^\"]*)\" place an order$", (String arg0) -> {
        });
        Then("^she choose to pick hers cookie at \"([^\"]*)\"$", (String arg0) -> {
        });
        And("^\"([^\"]*)\" order is sent to the store$", (String arg0) -> {
        });
        When("^\"([^\"]*)\" comes in time and retrieve her order$", (String arg0) -> {
        });
        Then("^there is \"([^\"]*)\" order pending$", (String arg0) -> {
        });
        And("^the previous order is added to the order History$", () -> {
        });

    }
}
