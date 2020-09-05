package io.github.liambloom.softwareEngineering.chapter9.accounts;

public interface Transaction {
    boolean isApproved();
    int value();
    void process();
}
