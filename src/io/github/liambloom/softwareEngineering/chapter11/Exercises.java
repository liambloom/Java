package io.github.liambloom.softwareEngineering.chapter11;

import java.util.*;

import io.github.liambloom.softwareEngineering.chapter7.Ask;

public class Exercises {
    public static void main(String[] args) {
        final LinkedList<Integer> list = new LinkedList<>(Arrays.asList(new Integer[]{ 0, 0, 2, 0, 4, 0, 6, 0, 8, 0, 10, 0, 12, 0, 14, 0, 16 }));
        exercise3(list, 0, 5, 13);
        System.out.println(list);
    }
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
}

class Sieve {
    public static void main(String[] args) {
        System.out.println("This program will tell you all prime");
        System.out.println("numbers up to a given maximum.");
        System.out.println();
        Ask.seperator = '?';
        final int max = Ask.forInt("Maximum number");
        /*Scanner console = new Scanner(System.in);
        System.out.print("Maximum number? ");
        int max = console.nextInt();*/
        List<Integer> primes = sieve(max);
        System.out.println("Prime numbers up to " + max + ":");
        System.out.println(primes);
    }

    // Returns a list of all prime numbers up to given max
    // using the sieve of Eratosthenes algorithm.
    public static List<Integer> sieve(int max) {
        List<Integer> primes = new LinkedList<Integer>();
        // add all numbers from 2 to max to a list
        List<Integer> numbers = new LinkedList<Integer>();
        // exercise 1
        numbers.add(2);
        for (int i = 3; i <= max; i += 2) {
            numbers.add(i);
        }
        while (!numbers.isEmpty()) {
            // remove a prime number from the front of the list
            int front = numbers.remove(0);
            primes.add(front);
            // remove all multiples of this prime number
            Iterator<Integer> itr = numbers.iterator();
            while (itr.hasNext()) {
                int current = itr.next();
                if (current % front == 0) {
                    itr.remove();
                }
            }
        }
        return primes;
    }
}