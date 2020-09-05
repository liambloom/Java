package io.github.liambloom.softwareEngineering.chapter9.market;

import liam.chapter9.lawFirm.Employee;

public class DiscountBill extends GroceryBill {
    private final boolean preferred;

    public DiscountBill (Employee clerk, boolean preferred) {
        super(clerk);
        this.preferred = preferred;
    }

    public int getDiscountCount () {
        if (!preferred) return 0;
        int c = 0;
        for (Item item : items) {
            if (item.getDiscount() > 0) c++;
        }
        return c;
    }
    public double getDiscountAmount () {
        int total = 0;
        for (Item item : items) total += item.getDiscount();
        return total;
    }
    public double getDiscountPercent () {
        return 100 * getDiscountAmount() / super.getTotal();
    }
    public double getTotal () {
        return super.getTotal() - getDiscountAmount();
    }
}
