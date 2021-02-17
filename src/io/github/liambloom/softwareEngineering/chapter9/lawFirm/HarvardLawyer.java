package io.github.liambloom.softwareEngineering.chapter9.lawFirm;


public class HarvardLawyer extends Lawyer {
    public double getSalary () {
        return super.getSalary() * 1.2;
    }
    public int getVacationDays () {
        return super.getVacationDays() + 3;
    }
    public String getVacationForm () {
        return super.getVacationForm().repeat(4);
    }
}
