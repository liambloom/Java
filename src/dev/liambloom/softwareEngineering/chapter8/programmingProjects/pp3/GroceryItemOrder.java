package dev.liambloom.softwareEngineering.chapter8.programmingProjects.pp3;

public class GroceryItemOrder {
    String name;
    int quantity;
    double pricePerUnit;

    public GroceryItemOrder(String name, int quantity, double pricePerUnit) {
        this.name = name;
        this.pricePerUnit = pricePerUnit;
        setQuantity(quantity);
    }

    public double getCost() {
        return quantity * pricePerUnit;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}