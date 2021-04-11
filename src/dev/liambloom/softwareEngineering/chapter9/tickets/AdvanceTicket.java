package dev.liambloom.softwareEngineering.chapter9.tickets;

public class AdvanceTicket extends Ticket {
    public final int days;
    AdvanceTicket (int number, int days) {
        super(number);
        this.days = days;
    }
    public double getPrice () {
        return days >= 10 ? 40 : 30;
    }
}