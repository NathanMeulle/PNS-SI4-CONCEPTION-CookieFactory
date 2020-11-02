package fr.unice.polytech.si4.conception.l.stepdefs;

import fr.unice.polytech.si4.conception.l.CookieFactory;
import fr.unice.polytech.si4.conception.l.Customer;
import fr.unice.polytech.si4.conception.l.exceptions.AlreadyCreatedException;
import io.cucumber.java8.En;

import static org.junit.jupiter.api.Assertions.*;

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

        When("^he fill a form in order to register and he submits it$", () -> {
            cookieFactory = new CookieFactory(null, null);
            cookieFactory.subscription(name, phoneNumber, mail);
        });
        Then("^he becomes a member$", () -> {
            assertNotNull(cookieFactory.getCustomerByMail(mail));
            customerSubscribe = cookieFactory.getCustomerByMail(mail);
        });
        And("^there is \"([^\"]*)\" in the list of customers$", (String arg0) -> {
            assertEquals(1, cookieFactory.getCustomers().size());
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
            cookieFactory = new CookieFactory(null, null);
        });
        And("^the email already exist is the database$", () -> {
            assertTrue(cookieFactory.getCustomers().contains(cookieFactory.getCustomerByMail(mail)));
        });
        Then("^register failure$", () -> {
            /** Comme on ne peux pas tester une exception on a été obligés de vérifier
             * si le customer était déja présent dans la liste **/
            assertThrows(AlreadyCreatedException.class, () ->cookieFactory.subscription(name, phoneNumber, mail));
        });

    }
}
