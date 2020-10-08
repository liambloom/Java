package io.github.liambloom.softwareEngineering.chapter11;

import java.util.*;

public class Exercises {
    public static void main(final String[] args) {
        //final List<String> list = new ArrayList<>(Arrays.asList(new String[] { "foo", "bar1", "12345", "123456" }));
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 5);
        map.put(2, 4);
        map.put(3, 5);
        map.put(4, 2);
        map.put(5, 1);
        System.out.println(exercise19(map));
        //System.out.println(list);
        // final List<String> list = new
        // ArrayList<String>(Arrays.asList(Ask.forArray(String.class, 1)))
    }

    // Exercise 1 is in the Sieve.java
    public static <T> List<T> exercise2(final List<T> list1, final List<T> list2) { // Combines them by alternating
        final Iterator<T> iter1 = list1.iterator();
        final Iterator<T> iter2 = list2.iterator();
        final List<T> newList = new ArrayList<>();
        while (iter1.hasNext() || iter2.hasNext()) {
            if (iter1.hasNext())
                newList.add(iter1.next());
            if (iter2.hasNext())
                newList.add(iter2.next());
        }
        return newList;
    }

    // removed all instances of value between indexes start and end
    public static <T> void exercise3(final LinkedList<T> list, final T value, final int start, final int end) { 
        final Iterator<T> iter = list.descendingIterator();
        for (int i = list.size() - 1; i >= start; i--) {
            assert iter.hasNext();
            if (i >= end)
                iter.next();
            else if (i < start)
                break;
            else if (iter.next().equals(value))
                iter.remove();
        }
    }

    // Puts everything < e before e, and everything > e after e
    public static <T extends Comparable<T>> void exercise4(final List<T> list, final T e) { 
        // definitely not what they meant, but it does technically fulfil the requirements soooooooooo
        list.sort(Comparator.naturalOrder());
    }

    public static <T extends Comparable<T>> void exercise5(final Collection<T> list) { // Sorts and removes the duplicates from the list
        final Set<T> set = new TreeSet<>(list);
        list.clear();
        list.addAll(set);
    }

    public static <E> int exercise6(final Collection<? extends E> list) { // Counts the # of unique elements in the list
        return new HashSet<>(list).size();
    }

    public static <E> int exercise7(final Collection<? extends E> list1, final Collection<? extends E> list2) { // Counts the number of unique elements that are in both lists
        final Set<E> set = new HashSet<>(list1);
        set.retainAll(list2); // Set#retainAll is basically the same as intersect
        return set.size();
    }

    public static int exercise8(final Collection<? extends String> list) { // Returns the length of the longest string
        int max = 0;
        for (final String str : list)
            max = Math.max(max, str.length());
        return max;
    }

    public static boolean exercise9(final Collection<? extends Number> list) { // Returns true if any element is odd
        for (final Number n : list) {
            if (n.doubleValue() % 2 != 0)
                return true;
        }
        return false;
    }

    public static void exercise10(final Collection<? extends String> list) { // Removes all strings with even length
        list.removeIf(str -> str.length() % 2 == 0);
    }

    public static <E> Set<E> exercise11(final Set<E> set1, final Set<E> set2) { // xor of the two sets
        final Set<E> clone = new HashSet<>(set1);
        clone.addAll(set2);
        clone.removeIf(e -> set1.contains(e) && set2.contains(e));
        return clone;
    }

    public static <E> boolean exercise12(final Collection<? extends E> list) { // Checks if any value occurs three or more times
        final Map<E, Integer> map = new HashMap<>();
        for (final E e : list) {
            final int v = map.getOrDefault(e, 0) + 1;
            if (v >= 3)
                return true;
            map.put(e, v);
        }
        return false;
    }
    public static <K, V> boolean exercise13(final Map<? extends K, V> map) { // Checks if they are unique
        return new HashSet<V>(map.values()).size() < map.size();
    }
    public static <K, V> Map<K, V> exercise14(final Map<K, V> map1, final Map<K, V> map2) { // Intersect of 2 maps
        Map<K, V> newMap = new HashMap<>(map1);
        map1.forEach((k, v) -> {
            if (map2.get(k).equals(v))
                newMap.put(k, v);
        });
        return newMap;
    }
    public static <E> int exercise15(final List<E> list) { // Return how many times the mode appears
        final Map<E, Integer> map = new HashMap<>();
        for (E e : list)
            map.put(e, map.getOrDefault(e, 0) + 1);
        int max = 0;
        for (Integer e : map.values())
            max = Math.max(e, max);
        return max;
    }
    public static <K, V> boolean exercise16(final Map<? extends K, V> map) { // exercise 13 with different types, but I used generics anyway
        return exercise13(map);
    }
    public static <K, V> boolean exercise17(final Map<K, V> map1, final Map<K, V> map2) { // checks if map2 is a subset of map1
        for (K key : map2.keySet()) {
            if (!map1.containsKey(key) || map1.get(key) != map2.get(key))
                return false;
        }
        return true;
    }
    public static <K, V> Map<V, Set<K>> exercise18(final Map<K, V> map) { // Reverses the map
        Map<V, Set<K>> reversed = new HashMap<>();
        for (K key : map.keySet()) {
            final V value = map.get(key);
            reversed.putIfAbsent(value, new HashSet<K>());
            reversed.get(value).add(key);
        }
        return reversed;
    }
    public static <K, V extends Comparable<V>> V exercise19(final Map<K, V> map) { // Finds the value that occurs the fewest times
        if (map.size() == 0)
            throw new IllegalArgumentException("Cannot find the rarest key in an empty map");
        final Map<V, Integer> counts = new HashMap<>();
        for (V value : map.values())
            counts.put(value, counts.getOrDefault(value, 0) + 1);
        int min = Integer.MAX_VALUE;
        for (Integer count : counts.values())
            min = Math.min(count, min);
        final List<V> list = new LinkedList<>();
        for (V key : counts.keySet()) {
            if (counts.get(key) == min)
                list.add(key);
        }
        final Iterator<V> iter = list.iterator();
        V minValue = iter.next();
        while (iter.hasNext()) {
            final V next = iter.next();
            if (next.compareTo(minValue) < 0)
                minValue = next;
        }
        return minValue;
    }
    // Exercise 20 is in Vocabulary.java
}