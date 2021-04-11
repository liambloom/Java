package dev.liambloom.softwareEngineering.chapter17.sort;

import java.util.Comparator;

public final class IdComparator implements Comparator<PersonNode> {
    public int compare(PersonNode p1, PersonNode p2) {
        return p1.getIdNum().compareTo(p2.getIdNum());
    }
}
