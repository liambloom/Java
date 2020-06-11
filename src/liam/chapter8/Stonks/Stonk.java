package liam.chapter8.Stonks;

public class Stonk { // Yes, I am fully aware that this is not how the word "Stock" is spelled, but it's funny, so I don't care
    public final String symbol;
    private int totalShares = 0;
    private double totalCost = 0;

    public Stonk (String symbol) {
        if (symbol == null) throw new NullPointerException("Symbol is null");
        this.symbol = symbol;
    }

    public double getProfit (double currentPrice) {
        if (currentPrice < 0) throw new IllegalArgumentException("Negative price not allowed");
        return (/* market value */ totalShares * currentPrice) - totalCost;
    }

    public void purchase (int shares, double pricePerShare) {
        if (shares < 0) throw new IllegalArgumentException("Cannot have negative number of shares");
        if (pricePerShare < 0) throw new IllegalArgumentException("Negative price per share is not allowed");

        totalShares += shares;
        totalCost += shares * pricePerShare;
    }
    public void clear () { // Exercise 10
        totalShares = 0;
        totalCost = 0;
    }
}