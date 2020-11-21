package fr.unice.polytech.si4.conception.l;


import fr.unice.polytech.si4.conception.l.customer.AnonymousCustomer;
import fr.unice.polytech.si4.conception.l.customer.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class CustomerTest {

    private Customer customer1;
    private Customer customer2;
    private SystemInfo SystemInfo;

    /**
     * Dès que des attributs (mail OU phone) sont identiques, les clients sont identiques
     */

    @BeforeEach
    void setUp() {

        customer1 = new Customer("Charlie", "06", "charlie@mail.com");
        SystemInfo = fr.unice.polytech.si4.conception.l.SystemInfo.getInstance();
        SystemInfo.resetSystemInfo();
    }

    @Test
    void equalTest() {
        customer1 = new Customer("Charlie", "06", "charlie@mail.com");
        customer2 = new Customer("Charlie", "06", "charlie@mail.com");
        assertEquals(customer1, customer2);
    }


    @Test
    void equalTest2() {
        customer1 = new Customer("Charlie", "06", "charlie@mail.com");
        customer2 = new Customer("Charlo", "06", "charlie@mail.com");
        assertEquals(customer1, customer2);
    }

    @Test
    void equalTest3() {
        customer1 = new Customer("Charlie", "06", "charlie@mail.com");
        customer2 = new Customer("Charlie", "07", "charlie@mail.com");
        assertEquals(customer1, customer2);
    }

    @Test
    void equalTest4() {
        customer1 = new Customer("Charlie", "06", "charlie@mail.com");
        customer2 = new Customer("Charlie", "06", "charlo@mail.com");
        assertEquals(customer1, customer2);
    }

    @Test
    void equalTest5() {
        customer1 = new Customer("Charlie", "06", "charlie@mail.com");
        customer2 = new Customer("Charlie", "07", "charlo@mail.com");
        assertNotEquals(customer1, customer2);
    }

    @Test
    void AnonymousEqualTest() {
        AnonymousCustomer customer = new AnonymousCustomer("Charlie", "06");
        AnonymousCustomer customer2 = new AnonymousCustomer("Charlie", "06");
        AnonymousCustomer customer3 = new AnonymousCustomer("Charlie", "07");
        AnonymousCustomer customer4 = new AnonymousCustomer("Charlo", "06");
        assertEquals(customer, customer2);
        assertEquals(customer2, customer4);
        assertNotEquals(customer2, customer3);
    }

}
