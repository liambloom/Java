package dev.liambloom.softwareEngineering.chapter9.tickets;

public abstract class Ticket {
    public final int number;
    public Ticket (int number) {
        this.number = number;
    }
    public abstract double getPrice ();
    public String toString () {
        return "Number: " + number + ", Price: " + getPrice();
    }
}