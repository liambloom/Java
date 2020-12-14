package io.github.liambloom.softwareEngineering.chapter8.starWarsNames;

public class Name implements Comparable<Name> {
    public String firstName;
    public String middleInitial;
    public String lastName;

    public Name() {
        this("", "", "");
    }

    public Name(String first, String last) {
        this(first, "", last);
    }

    public Name(String first, String middle, String last) {
        firstName = first;
        middleInitial = middle;
        lastName = last;
    }

    public String toString() {
        return lastName + " " + middleInitial + " " + firstName;
    }

    public int compareTo(Name o) {
        return this.toString().compareTo(o.toString());
    }
}
