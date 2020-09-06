package io.github.liambloom.softwareEngineering.chapter8.Stonks;

import io.github.liambloom.softwareEngineering.Globals;
import io.github.liambloom.softwareEngineering.chapter7.Ask;

public class IO {
    @SuppressWarnings("unused")
    public static void main (String[] args) {
        Stonk stock1 = new Stonk(Ask.forToken("First stock's symbol").toUpperCase());

    }
    public static double makePurchase (Stonk stock) {
        for (int i = 0; i < Ask.forInt("# of purchases made",0, (int) Globals.Math.Infinity); i++) {
            System.out.printf("Stock #%d%n", i + 1);
            stock.purchase(Ask.forInt("# of shares"), Ask.forDouble("Price / share"));
            System.out.println();
        }

        final double profit = stock.getProfit(Ask.forDouble("Price per share"));
        System.out.printf("Net %s: $%f%n%n", profit < 0 ? "loss" : "gain", Math.abs(profit));
        return profit;
    }
}