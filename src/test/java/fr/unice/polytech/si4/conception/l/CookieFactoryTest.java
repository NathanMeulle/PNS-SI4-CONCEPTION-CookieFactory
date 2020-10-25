package fr.unice.polytech.si4.conception.l;

import fr.unice.polytech.si4.conception.l.exceptions.AlreadyCreatedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CookieFactoryTest {

    String name;
    String phoneNumber;
    String mail;
    CookieFactory cookieFactory;
    Log log;

    @BeforeEach
    void setUp() {
        name = new String("Esteve");
        phoneNumber = new String("0658601237");
        mail = new String("estevet@hotmail.fr");
        cookieFactory = new CookieFactory(null, null);
        log = new Log();
    }

    @Test
    void subscription() {
        try{
            cookieFactory.subscription(name, phoneNumber, mail);
        } catch (AlreadyCreatedException exception){
            //log.add("Customer already exist");
        }

        Customer customerSubscribe = cookieFactory.getCustomerByMail("mail");

        assertEquals(name, customerSubscribe.getName());
        assertEquals(phoneNumber, customerSubscribe.getPhoneNumber());
        assertEquals(mail, customerSubscribe.getMail());
    }

}