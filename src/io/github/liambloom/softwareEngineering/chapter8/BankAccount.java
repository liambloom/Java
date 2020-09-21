package io.github.liambloom.softwareEngineering.chapter8;

public class BankAccount {
    String name;
    double balance;
    double transactionFee = 0; // Exercise 11

    public void deposit (final double amount) {
        balance += amount;
    }
    public void withdraw (double amount) {
        amount += transactionFee; // Also exercise 11
        if (balance - amount >= 0) deposit(-amount);
    }
    public void transfer (BankAccount to, double amount) { // Exercise 13
        amount = Math.max(Math.min(amount, balance - 5), 0);
        withdraw(amount + 5 - transactionFee);
        to.deposit(amount);
    }

    public String toString () { // Exercise 12
        return name + ", $" + balance;
    }
}
