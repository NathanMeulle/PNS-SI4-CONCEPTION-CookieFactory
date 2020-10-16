package fr.unice.polytech.si4.conception.l;


public class Main {
    public static void main(String[] args) {
        Cookie choco = new Cookie("Chocolate");
        Manager manager = new Manager(10,"Nathan");
        Store store = new Store(1,"adresse", 10,"06.06.06.06.06","nathan.meulle@gmail.com", manager);
        AnonymousCustomer anonymousCustomer = new AnonymousCustomer("0621487598");
        Order order = new Order(store, anonymousCustomer);
        order.addCookie(choco,1);
        Log.display();
    }
}