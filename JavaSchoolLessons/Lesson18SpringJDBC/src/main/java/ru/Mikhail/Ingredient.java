package ru.Mikhail;

public class Ingredient {
    private final int dishId;
    private final String name;
    private final int quantity;

    public Ingredient(int dishId, String name, int quantity) {
        this.dishId = dishId;
        this.name = name;
        this.quantity = quantity;
    }

    public int getDishId() {
        return dishId;
    }

    public String getName() {
        return name;
    }

    public double getQuantity() {
        return quantity;
    }
}
