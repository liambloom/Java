package io.github.liambloom.softwareEngineering.chapter11;

import java.util.*;

public class Exercises {
    public static void main(String[] args) {
        final List<String> list = new ArrayList<>(Arrays.asList(new String[]{"foo", "bar1", "12345", "123456"}));
        exercise10(list);
        System.out.println(list);
        //final List<String> list = new ArrayList<String>(Arrays.asList(Ask.forArray(String.class, 1)))
    }
    // Exercise 1 is in the Sieve.java
    public static <T> List<T> exercise2(List<T> list1, List<T> list2) { // Combines them by alternating
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
    public static <T> void exercise3(LinkedList<T> list, T value, int start, int end) { // removed all instances of value between indexes start and end
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
    public static <T extends Comparable<T>> void exercise4(List<T> list, T e) { // Puts everything < e before e, and everything > e after e
        // definitely not what they meant, but it does technically fulfil the requirements soooooooooo
        list.sort(Comparator.naturalOrder());
    }
    public static <T extends Comparable<T>> void exercise5(Collection<T> list) { // Sorts and removes the duplicates from the list
        Set<T> set = new TreeSet<>(list);
        list.clear();
        list.addAll(set);
    }
    public static <E> int exercise6(Collection<? extends E> list) { // Counts the # of unique elements in the list
        return new HashSet<>(list).size();
    }
    public static <E> int exercise7(Collection<? extends E> list1, Collection<? extends E> list2) { // Counts the number of unique elements that are in both lists
        final Set<E> set = new HashSet<>(list1);
        set.retainAll(list2); // Set#retainAll is basically the same as intersect
        return set.size();
    }
    public static int exercise8(Collection<? extends String> list) { // Returns the length of the longest string
        int max = 0;
        for (String str : list)
            max = Math.max(max, str.length());
        return max;
    }
    public static boolean exercise9(Collection<? extends Number> list) { // Returns true if any element is odd
        for (Number n : list) {
            if (n.doubleValue() % 2 != 0) return true;
        }
        return false;
    }
    public static void exercise10(Collection<? extends String> list) { // Removes all strings with even length
        list.removeIf(str -> str.length() % 2 == 0);
    }
    public static <E> Set<E> exercise11(Set<E> set1, Set<E> set2) {
        Set<E> clone = new HashSet<>(set1);
        clone.addAll(set2);
        return clone;
    }
    // Exercise 20 is in Vocabulary.java
}