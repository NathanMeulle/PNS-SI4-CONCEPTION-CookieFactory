package fr.unice.polytech.si4.conception.l;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CustomerTest {

    Customer customer1;
    Customer customer2;


    /**
     * Dès que des attributs (mail OU phone) sont identiques, les clients sont identiques
     */

    @Test
    public void equalTest(){
        customer1 = new Customer("Charlie", "06", "charlie@mail.com");
        customer2 = new Customer("Charlie", "06", "charlie@mail.com");
        assertEquals(customer1, customer2);
    }


    @Test
    public void equalTest2(){
        customer1 = new Customer("Charlie", "06", "charlie@mail.com");
        customer2 = new Customer("Charlo", "06", "charlie@mail.com");
        assertEquals(customer1, customer2);
    }

    @Test
    public void equalTest3(){
        customer1 = new Customer("Charlie", "06", "charlie@mail.com");
        customer2 = new Customer("Charlie", "07", "charlie@mail.com");
        assertEquals(customer1, customer2);
    }

    @Test
    public void equalTest4(){
        customer1 = new Customer("Charlie", "06", "charlie@mail.com");
        customer2 = new Customer("Charlie", "06", "charlo@mail.com");
        assertEquals(customer1, customer2);
    }

    @Test
    public void equalTest5(){
        customer1 = new Customer("Charlie", "06", "charlie@mail.com");
        customer2 = new Customer("Charlie", "07", "charlo@mail.com");
        assertNotEquals(customer1, customer2);
    }

}
