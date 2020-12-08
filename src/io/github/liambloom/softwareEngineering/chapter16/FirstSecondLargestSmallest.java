package io.github.liambloom.softwareEngineering.chapter16;

import io.github.liambloom.softwareEngineering.chapter4.Ask;

public class FirstSecondLargestSmallest {
    public static void main(String[] args) {
        Ask.seperator = ':';
        boolean over = false;
        // This is my own implementation of LinkedList
        LinkedList<Integer> list = new LinkedList<>();
        while (!over) {
            final int n = Ask.forInt("Enter a number");
            if (n == -1)
                over = true;
            else
                list.addSorted(n);
            System.out.printf("L1 = %d, L2 = %d, S1 = %d, S2 = %d%n", 
                list.get(0), list.get(Math.min(1, list.size() - 1)), 
                list.get(list.size() - 1), list.get(Math.max(list.size() - 2, 0)));
        }
        System.out.println("GOOD BYE!!");
    }
}