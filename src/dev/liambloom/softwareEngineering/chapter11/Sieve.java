package dev.liambloom.softwareEngineering.chapter11;

import java.util.*;
import dev.liambloom.softwareEngineering.chapter7.Ask;

public class Sieve {
    public static void main(String[] args) {
        System.out.println("This program will tell you all prime");
        System.out.println("numbers up to a given maximum.");
        System.out.println();
        Ask.seperator = '?';
        final int max = Ask.forInt("Maximum number");
        /*
         * Scanner console = new Scanner(System.in);
         * System.out.print("Maximum number? "); int max = console.nextInt();
         */
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