package dev.liambloom.softwareEngineering.chapter8.programmingProjects.pp3;

public class GroceryList {
    GroceryItemOrder[] items = new GroceryItemOrder[10];
    int listLength = 0;

    public void add(GroceryItemOrder item) {
        // The book doesn't say what to do if there's more than 10 items
        items[listLength] = item;
        listLength++;
    }

    public double getTotalCost() {
        double cost = 0;
        for (int i = 0; i < listLength; i++)
            cost += items[i].getCost();
        return cost;
    }
}
