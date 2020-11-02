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
    private Customer otherCustomer;
    private String otherName;
    private String otherPhone;
    private String otherMail;

    public BecomeMemberStepDef() {
        Given("^a cookieFactory$", () -> {
            cookieFactory = new CookieFactory(null, null);
        });

        And("^A client named \"([^\"]*)\" with a phoneNumber \"([^\"]*)\" and with an email \"([^\"]*)\",$", (String arg0, String arg1, String arg2) -> {
            this.name = arg0;
            this.phoneNumber = arg1;
            this.mail = arg2;
        });


        /** ********************************************************************************
         *                  Scenario: He is anonymous and wants to register
         *  ********************************************************************************
         */
        When("^he fill a form in order to register and he submits it$", () -> {
            cookieFactory.subscription(name, phoneNumber, mail);
        });
        Then("^he becomes a member$", () -> {
            assertNotNull(cookieFactory.getCustomerByMail(mail));
            customerSubscribe = cookieFactory.getCustomerByMail(mail);
        });
        And("^there is \"([^\"]*)\" in the list of customers$", (Integer arg0) -> {
            assertEquals(arg0, cookieFactory.getCustomers().size());
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

        Then("^the email already exist is the database$", () -> {
            assertTrue(cookieFactory.getCustomers().contains(cookieFactory.getCustomerByMail(otherMail)));
        });

        When("^A client named \"([^\"]*)\" with a \"([^\"]*)\" and with a \"([^\"]*)\" wants to register$", (String arg0, String arg1, String arg2) -> {
            this.otherName = arg0;
            this.otherPhone = arg1;
            this.otherMail = arg2;
        });

        Then("^register \"([^\"]*)\"$", (String arg0) -> {
            if (arg0.equals("failure")) {
                assertThrows(AlreadyCreatedException.class, () -> cookieFactory.subscription(otherName, otherPhone, otherMail));
            } else {
                assertDoesNotThrow(() -> cookieFactory.subscription(otherName, otherPhone, otherMail));
            }
        });


    }
}
