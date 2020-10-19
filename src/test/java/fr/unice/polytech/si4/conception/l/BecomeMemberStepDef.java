package fr.unice.polytech.si4.conception.l;

import io.cucumber.java8.En;

public class BecomeMemberStepDef implements En {
    public BecomeMemberStepDef() {
        Given("^An client named \"([^\"]*)\" with a phoneNumber \"([^\"]*)\"$", (String arg0, String arg1) -> {
        });
        And("^with a email \"([^\"]*)\",$", (String arg0) -> {
        });
        When("^\"([^\"]*)\" wants to register$", (String arg0) -> {
        });
        Then("^he fill a form in order to register$", () -> {
        });
        And("^he submits it$", () -> {
        });
        Then("^he becomes a member$", () -> {
        });
        And("^with name : \"([^\"]*)\"$", (String arg0) -> {
        });
        And("^a phoneNumber : \"([^\"]*)\"$", (String arg0) -> {
        });
        And("^a email : \"([^\"]*)\"$", (String arg0) -> {
        });
        And("^the email already exist is the database$", () -> {
        });
        Then("^register failure$", () -> {
        });

    }
}
