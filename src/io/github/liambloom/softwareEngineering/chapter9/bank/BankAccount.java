package io.github.liambloom.softwareEngineering.chapter9.bank;

public class BankAccount implements HasValue {
    private int value = 0; // IDK why this is required, but otherwise value is final
    public BankAccount (Startup s) {} // Unfortunately you can't make abstract constructors
    public void debit (Debit d) {
        assert d.value >= 0;
        value += d.value;
    }
    public void credit (Credit c) {
        assert c.value >= 0;
        value -= c.value;
    }
    public int getBalance() {
        return value;
    }
}

// Interfaces because they don't do anything
interface HasValue {
    int value = 0;
}
interface Startup extends HasValue {}
interface Debit extends HasValue {}
interface Credit extends HasValue {}