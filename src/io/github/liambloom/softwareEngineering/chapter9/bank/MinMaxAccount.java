package io.github.liambloom.softwareEngineering.chapter9.bank;

public abstract class MinMaxAccount extends BankAccount { // Abstract because I don't want to write a getBalance() method
    private int min;
    private int max;

    public MinMaxAccount (Startup s) {
        super(s);
    }

    public void debit (Debit d) {
        super.debit(d);
        max = Math.max(max, getBalance());
    }
    public void credit (Credit c) {
        super.credit(c);
        min = Math.min(min, getBalance());
    }

    public int getMin () {
        return min;
    }
    public int getMax () {
        return max;
    }
}
