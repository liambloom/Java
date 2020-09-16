package io.github.liambloom.softwareEngineering.chapter11;

import java.util.*;

public class SelfChecks {
    public static void sc4() {
        Integer[] a = { 1, 1, 3, 5, 5, 5, 5, 7, 7, 11 };
        LinkedList<Integer> list = new LinkedList<>(Arrays.asList(a));
        int duplicates = 0;
        int previous;
        Iterator<Integer> itr = list.iterator();
        previous = itr.next();
        while (itr.hasNext()) {
            Integer current = itr.next();
            if (previous == current)
                duplicates++;
            previous = current;
        }
    }
    public static void sc5() {
        
    }
}
