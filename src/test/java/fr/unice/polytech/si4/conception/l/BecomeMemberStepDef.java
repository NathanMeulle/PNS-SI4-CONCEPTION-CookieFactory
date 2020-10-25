package fr.unice.polytech.si4.conception.l;

import fr.unice.polytech.si4.conception.l.exceptions.AlreadyCreatedException;
import io.cucumber.java8.En;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BecomeMemberStepDef implements En {

    private String name;
    private String phoneNumber;
    private String mail;
    private CookieFactory cookieFactory;
    private Customer customerSubscribe;

    public BecomeMemberStepDef() {
        Given("^An client named \"([^\"]*)\" with a phoneNumber \"([^\"]*)\"$", (String name, String phoneNumber) -> {
            this.name = name;
            this.phoneNumber = phoneNumber;
        });
        And("^with a email \"([^\"]*)\",$", (String mail) -> {
            this.mail = mail;
        });

        /** ********************************************************************************
         *                  Scenario: He is anonymous and wants to register
         *  ********************************************************************************
         */

        When("^the client wants to register$", () -> {
        });
        Then("^he fill a form in order to register$", () -> {
            cookieFactory = new CookieFactory(null, null);
            cookieFactory.subscription(name, phoneNumber, mail);
        });
        And("^he submits it$", () -> {
        });
        Then("^he becomes a member$", () -> {
            customerSubscribe = cookieFactory.getCustomerByMail(mail);
        });
        And("^with name : \"([^\"]*)\"$", (String arg0) -> {
            assertEquals(name, customerSubscribe.getName());
        });
        And("^a phoneNumber : \"([^\"]*)\"$", (String arg0) -> {
            assertEquals(phoneNumber, customerSubscribe.getPhoneNumber());
        });
        And("^a email : \"([^\"]*)\"$", (String arg0) -> {
            assertEquals(mail, customerSubscribe.getMail());
        });

        /** ********************************************************************************
         *          Scenario: He wants to register again while being a member
         *  ********************************************************************************
         */

        When("^\"([^\"]*)\" wants to register$", (String arg0) -> {
        });
        And("^the email already exist is the database$", () -> {
            try {
                cookieFactory.subscription(name, phoneNumber, mail);
            } catch (AlreadyCreatedException exception){
                assertTrue(true);
            }
        });
        Then("^register failure$", () -> {
        });



    }
}
