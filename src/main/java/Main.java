import cookie.composition.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Cookie choco = new Cookie("Chocolate");
        Manager manager = new Manager(10,"Nathan");
        Store store = new Store(1,"adresse", 10,"06.06.06.06.06","nathan.meulle@gmail.com", manager);
        Order order = new Order(store);
        order.addCookie(choco,1);
    }
    
}