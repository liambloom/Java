package liam.chapter9.market;

// Not the same purpose, but still employees, so close enough
import liam.chapter9.lawFirm.Employee;
import java.util.ArrayList;
import java.util.List;

public class GroceryBill {
    private final Employee clerk;

    List<Item> items = new ArrayList<Item>();

    public GroceryBill (Employee clerk) {
        this.clerk = clerk;
    }

    public void add (Item i) {
        items.add(i);
    }
    public void printReceipt () {
        for (Item item : items) System.out.printf("+$%.2f%n", item.getPrice());
    }

    public double getTotal () {
        int total = 0;
        for (Item item : items) total += item.getPrice();
        return total;
    }
}
