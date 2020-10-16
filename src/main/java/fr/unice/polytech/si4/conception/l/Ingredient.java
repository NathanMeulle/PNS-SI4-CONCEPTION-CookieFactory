package fr.unice.polytech.si4.conception.l;



public class Ingredient {

    protected String name;
    protected int price;

    public Ingredient(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }
}
