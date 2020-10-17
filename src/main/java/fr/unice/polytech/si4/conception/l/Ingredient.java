package fr.unice.polytech.si4.conception.l;


import fr.unice.polytech.si4.conception.l.cookie.composition.IngredientType;

public class Ingredient {

    private String name;
    private int price;
    private IngredientType type;

    public Ingredient(String name, int price, IngredientType type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public IngredientType getType() {
        return type;
    }

    public void setType(IngredientType type) {
        this.type = type;
    }
}
