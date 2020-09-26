package io.github.liambloom.softwareEngineering.chapter11;

import java.util.*;

import io.github.liambloom.softwareEngineering.chapter7.Ask;

public class Test {
    public static void main(String[] args) {
        /*Set<String> words = new HashSet<>();
        final String a = new String("foo");
        final String b = new String("bar");
        words.add("z");
        words.add("a");
        System.out.println(a == b);
        words.add(a);
        words.add(b);
        System.out.println(words);*/
        System.out.println(Ask.forInt());
        throw new IllegalArgumentException();
        /*LinkedList<String> words = new LinkedList<>("A", "B", "C", "D", "E", "F" );
        Iterator<String> itr = words.iterator();
        while (itr.hasNext()) {
            String s = itr.next();
            if (s.equals("D"))
                itr.remove();
            else {
                System.out.print(s);
                if (itr.hasNext())
                    System.out.print(", ");
            }
        }*/
        /*for (String word : words) 
            System.out.print(word + ", ");*/
    }
}