package liam.chapter9.stocks;

public class Cash implements Asset {
    private double amount;

    public Cash(double amount) {
        this.amount = amount;
    }

    public double getMarketValue() {
        return amount;
    }
    public double getProfit() {
        return 0.0;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean equals (Cash o) {
        return this.getMarketValue() == o.getMarketValue();
    }
}
