package io.github.liambloom.softwareEngineering.chapter11.PersonComparableDates;

/**
 * Person_Comparable_Dates class does the following:
 * 
 * (1) implements "Comparable" and thus, YOU write the "compareTo()" method.
 * Rem: public int compareTo(Object x)
 * 
 * (2) has the needed instance variables: myFirstName & myLastName of type
 * String, myBirthDate of type Date (Compositioned inside of Person in other
 * words, Person "hasA" Date) (3) has the needed constructors: default and
 * regular that takes in 2 Strings for the name & 3 ints for the Date object.
 * 
 * (4) has getMyBirthDate() that returns the Person's myBirthDate.
 * 
 * (5) has a toString() method
 * 
 */
public class Person implements Comparable<Person> {
    public final String firstName;
    public final String lastName;
    public final Date birthdate;

    public Person() {
        this("Firstname", "Lastname", 0, 0, 0);
    }

    public Person(String first, String last, int month, int day, int year) {
        this.firstName = first;
        this.lastName = last;
        this.birthdate = new Date(month, day, year);
    }

    public Date getMyBirthDate() {
        return birthdate;
    }

    @Override
    public int compareTo(Person o) {
        return this.birthdate.compareTo(o.birthdate);
    }

    @Override
    public String toString() {
        return String.format("%s %s, born %s", firstName, lastName, birthdate);
    }
}