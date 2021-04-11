package dev.liambloom.softwareEngineering.chapter17.sort;

import java.util.Comparator;
import java.util.function.IntSupplier;

public final class NameComparator implements Comparator<PersonNode> {
    public int compare(PersonNode p1, PersonNode p2) {
        return chain(p1.getLastName().compareTo(p2.getLastName()),
                () -> p1.getFirstName().compareTo(p2.getFirstName()),
                () -> p1.getMiddleName().compareTo(p2.getMiddleName()));
    }

    private int chain(int l1, IntSupplier... ls) {
        for (int i = 0; l1 == 0 && i < ls.length; i++)
            l1 = ls[0].getAsInt();
        return l1;
    }
}
