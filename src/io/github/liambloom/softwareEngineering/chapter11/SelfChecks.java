package io.github.liambloom.softwareEngineering.chapter11;

import java.util.*;

public class SelfChecks {
    public static void main(String[] args) {
        /*final LinkedList<Integer> list = new LinkedList<>(Arrays.asList(new Integer[]{ 1, 2, 3, 4, 5, 6 }));
        sc7(list);
        System.out.println(list);*/
        sc19();
    }
    public static void sc4() {
        // Given variables
        final Integer[] a = { 1, 1, 3, 5, 5, 5, 5, 7, 7, 11 };
        final LinkedList<Integer> list = new LinkedList<>(Arrays.asList(a));
        // Self-check Code
        @SuppressWarnings("unused") // For some reason it think that duplicates is unused
        int duplicates = 0;
        int previous;
        final Iterator<Integer> itr = list.iterator();
        previous = itr.next();
        while (itr.hasNext()) {
            final Integer current = itr.next();
            if (previous == current)
                duplicates++;
            previous = current;
        }
    }
    public static void sc5() {
        // Given variables
        final String[] a = { "Alpha", "Baker", "Foxtrot", "Tango", "Whiskey" };
        final LinkedList<String> list = new LinkedList<>(Arrays.asList(a));
        final String insert = "Charlie";
        // Self-check Code
        final Iterator<String> iter = list.iterator();
        for (int i = 0; iter.hasNext(); i++) {
            if (iter.next().compareTo(insert) > 0) {
                list.add(i, insert);
                System.out.println(list);
                return;
            }
        }
        list.add(insert);
    }
    public static <T> void sc6(LinkedList<T> list, T value) {
        list.removeIf(e -> e.equals(value));
    }
    public static <T> void sc7(LinkedList<T> list) {
        List<T> secondHalf = list.subList(list.size() / 2, list.size());
        List<T> secondHalfClone = List.copyOf(secondHalf);
        secondHalf.clear();
        list.addAll(0, secondHalfClone);
    }

    @SuppressWarnings("all")
    public static void sc18() {
        Map<Integer, String> foo = new HashMap<>();
        foo.remove("hi");
    }
    
    public static void sc19_mystery(Map<String, String> map) {
        Map<String, String> result = new TreeMap<String, String>();
        for (String key : map.keySet()) {
            if (key.compareTo(map.get(key)) < 0) {
                result.put(key, map.get(key));
            } else {
                result.put(map.get(key), key);
            }
        }
        System.out.println(result);
    }

    public static void sc19() {
        Map<String, String> map = new HashMap<>();
        //map.put("")
        sc19_mystery(map);
    }

    public static void sc20() {
        //final SortedMap<Integer, String> reverseWordCountMap = reverseMap(wordCountMap);
        for (Integer key : new TreeSet<>(new TreeMap<Integer, String>().keySet()));
    }
}
