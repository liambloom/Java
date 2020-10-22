package io.github.liambloom.softwareEngineering.chapter13.PersonComparableDates;

/**
 * PersonComparableDates_CLIENT: Mr. Marques
 * 
 * This program will help you to understand how the interface Comparable works.
 * Remember, it is a Java "built-in" interface that has one and only one method
 * that you MUST override.
 * 
 * It is ALWAYS of the form:
 * 
 * public int compareTo(Object x) // It ALWAYS takes in an 'Object' and //
 * returns an 'int'. ALWAYS!!! // Remember this!!
 * 
 * This program will have you write a PersonComparableDates class that creates
 * an ArrayList called 'people' that holds the type 'PersonComparableDates'. A
 * 'PersonComparableDates' has a firstName and lastName as well as it hasA
 * Date class that holds the PersonComparableDatess birthdate.
 * 
 * Your task: Find the youngest and oldest PersonComparableDates in the
 * ArrayList 'people'. Create the 'PersonComparableDates' class and have it
 * "implement" Comparable. Thus, you have to write the compareTo() method for
 * PersonComparableDates that compares each 'PersonComparableDates' first by
 * year, then month, then day.
 * 
 * OutPut:
 * 
 * The list of people are: [George Washington(2/22/1732), Paul
 * Marques(3/27/1969), Kermit The Frog(9/27/1976), SquarePants
 * SpongeBob(5/1/1999), Charlie Brown(10/3/1950), Rocky Horror(8/14/1975), Moses
 * RedSea(4/3/-2345), Isaac Newton(1/4/1643), William Shakespeare(4/26/1564),
 * Oprah Winfrey(1/29/1954)]
 * 
 * The oldest PersonComparableDates: Moses RedSea(4/3/-2345)
 * 
 * The youngest PersonComparableDates: SquarePants SpongeBob(5/1/1999)
 * 
 */

import java.util.*;

public class Main {

    public static void main(String[] args) {
        ArrayList<Person> people = new ArrayList<Person>();
        Person theOldest, theYoungest;

        people.add(new Person("George", "Washington", 2, 22, 1732));
        people.add(new Person("Paul", "Marques", 3, 27, 1969));
        people.add(new Person("Kermit", "The Frog", 9, 27, 1976));
        people.add(new Person("SquarePants", "SpongeBob", 5, 1, 1999));
        people.add(new Person("Charlie", "Brown", 10, 3, 1950));
        people.add(new Person("Rocky", "Horror", 8, 14, 1975));
        people.add(new Person("Moses", "RedSea", 4, 3, -2345));
        people.add(new Person("Isaac", "Newton", 1, 4, 1643));
        people.add(new Person("William", "Shakespeare", 4, 26, 1564));
        people.add(new Person("Oprah", "Winfrey", 1, 29, 1954));

        theOldest = findOldest(people);
        theYoungest = findYoungest(people);

        System.out.println("The list of people are: \n" + people + "\n");
        System.out.println(" The oldest PersonComparableDates: " + theOldest + "\n");
        System.out.println(" The youngest PersonComparableDates: " + theYoungest);

    } // main

    public static Person findOldest(ArrayList<Person> PersonComparableDatesList) {
        Iterator<Person> iter = PersonComparableDatesList.iterator();
        if (!iter.hasNext())
            throw new IllegalArgumentException("There is no oldest in an empty list");
        Person oldest = iter.next();
        while (iter.hasNext()) {
            Person e = iter.next();
            if (oldest.compareTo(e) > 0)
                oldest = e;
        }
        return oldest;

    } // findOldest

    public static Person findYoungest(ArrayList<Person> PersonComparableDatesList) {
        // YOUR CODE HERE!!!
        Iterator<Person> iter = PersonComparableDatesList.iterator();
        if (!iter.hasNext())
            throw new IllegalArgumentException("There is no youngest in an empty list");
        Person youngest = iter.next();
        while (iter.hasNext()) {
            Person e = iter.next();
            if (youngest.compareTo(e) < 0)
                youngest = e;
        }
        return youngest;
    } // findYoungest

}
