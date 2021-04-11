package dev.liambloom.softwareEngineering.chapter16;

import java.util.List;
import dev.liambloom.softwareEngineering.chapter4.Ask;

public class Sorts {
    public static void main(String[] args) {
        // Get List
        List<Integer> list = new LinkedList<>();
        Integer last;
        do {
            last = Ask.forInt("Enter a number (-1 to quit)");
            if (last != -1)
                list.add(last);
        }
        while (last != -1);

        bucketSort(list);

        System.out.println(list);
    }

    public static void bucketSort(List<Integer> list) {
        @SuppressWarnings("unchecked")
        final List<Integer>[] buckets = new LinkedList[10];
        for (int i = 0; i < 10; i++)
            buckets[i] = new LinkedList<Integer>();
        boolean sorted = false;
        for (int i = 1; !sorted; i *= 10) {
            sorted = true;
            for (Integer e : list) {
                final List<Integer> bucket = buckets[e / i % 10];
                if (e / i != 0)
                    sorted = false;
                bucket.add(e);
            }
            list.clear();
            for (int j = 0; j < 10; j++) {
                for (Integer e : buckets[j]) {
                    list.add(e);
                }
                buckets[j].clear();
            }
        }
    }
}
