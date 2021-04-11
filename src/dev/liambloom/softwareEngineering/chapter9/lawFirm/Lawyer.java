package dev.liambloom.softwareEngineering.chapter9.lawFirm;

public class Lawyer extends Employee {
    public int getVacationDays () {
        return 15;
    }
    public String getVacationForm () {
        return "pink";
    }
    public void sue () {
        System.out.println("I'll see you in court!");
    }
}
