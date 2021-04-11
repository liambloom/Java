package dev.liambloom.softwareEngineering.chapter9.tickets;

public class WalkupTicket extends Ticket {
    public WalkupTicket (int number) {
        super(number);
    }
    public double getPrice () {
        return 50;
    }
}