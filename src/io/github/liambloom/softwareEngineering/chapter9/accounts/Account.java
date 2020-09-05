package io.github.liambloom.softwareEngineering.chapter9.accounts;

import java.util.Random;

public class Account {
    public final Random r = new Random();

    public Account (Client c) {}

    public boolean process (Transaction t) {
        if (t.isApproved()) t.process();
        return t.isApproved();
    }
}