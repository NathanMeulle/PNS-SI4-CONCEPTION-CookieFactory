package fr.unice.polytech.si4.conception.l;

import io.cucumber.java8.En;

public class CreateRecipeStepdefs implements En {

    CookieFactory cookieFactory;
    Cookie cookie;

    public CreateRecipeStepdefs() {
        Given("^a factory with (\\d+) cookie and (\\d+) store$", (Integer arg0, Integer arg1) -> {
        });
        And("^a cookie of name \"([^\"]*)\"$", (String arg0) -> {
        });
        When("^the factory requests his number of cookies$", () -> {
        });
        Then("^There is (\\d+) in his number of cookies$", (Integer arg0) -> {
        });
        When("^the factory adds the cookie \"([^\"]*)\"$", (String arg0) -> {
        });
        And("^The cookie \"([^\"]*)\" can't be add a second time$", (String arg0) -> {
        });
    }
}
