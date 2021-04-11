package dev.liambloom.softwareEngineering.chapter9.tickets;

public class StudentAdvanceTicket extends AdvanceTicket {
    public StudentAdvanceTicket (int number, int days) {
        super(number, days);
    }
    public double getPrice () {
        return days >= 10 ? 15 : 20;
    }
    public String toString () {
        return super.toString() + " (ID required)";
    }
}