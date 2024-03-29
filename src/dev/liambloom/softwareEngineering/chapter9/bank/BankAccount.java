package dev.liambloom.softwareEngineering.chapter9.bank;

public class BankAccount extends HasValue {
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
abstract class HasValue {
    int value = 0;
}

abstract class Startup extends HasValue {}
abstract class Debit extends HasValue {}
abstract class Credit extends HasValue {}