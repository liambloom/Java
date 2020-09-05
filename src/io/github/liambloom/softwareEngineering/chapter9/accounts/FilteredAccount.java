package io.github.liambloom.softwareEngineering.chapter9.accounts;

public class FilteredAccount extends Account {
    private int total = 0;
    private int filtered = 0;

    public FilteredAccount (Client c) {
        super(c);
    }
    public boolean process (Transaction t) {
        total++;
        if (t.value() == 0) {
            filtered++;
            return true;
        }
        else return super.process(t);
    }
    public double percentFiltered () {
        return 100.0 * filtered / total;
    }
}
