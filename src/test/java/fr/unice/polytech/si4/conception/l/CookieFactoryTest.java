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
            log.add("Customer already exist");
        }

        Customer customerSubscribe = cookieFactory.getCustomerByMail(mail);

        assertEquals(name, customerSubscribe.getName());
        assertEquals(phoneNumber, customerSubscribe.getPhoneNumber());
        assertEquals(mail, customerSubscribe.getMail());
    }

    @Test
    void addStore() {
    }

    @Test
    void addCookie() {
    }

    @Test
    void getCustomers() {
    }

    @Test
    void getCustomerByMail() {
        try{
            cookieFactory.addCustomer(new Customer(name, phoneNumber, mail));
        } catch (AlreadyCreatedException e){
            e.printStackTrace();
        }

        assertEquals(name, cookieFactory.getCustomerByMail(mail).getName());
        assertEquals(phoneNumber, cookieFactory.getCustomerByMail(mail).getPhoneNumber());
        assertEquals(mail, cookieFactory.getCustomerByMail(mail).getMail());
    }

    @Test
    void getCustomerByTel() {
        try{
            cookieFactory.addCustomer(new Customer(name, phoneNumber, mail));
        } catch (AlreadyCreatedException e){
            e.printStackTrace();
        }

        assertEquals(name, cookieFactory.getCustomerByTel(phoneNumber).getName());
        assertEquals(phoneNumber, cookieFactory.getCustomerByTel(phoneNumber).getPhoneNumber());
        assertEquals(mail, cookieFactory.getCustomerByTel(phoneNumber).getMail());
    }

    @Test
    void getCookies() {
    }

    @Test
    void getStores() {
    }
}