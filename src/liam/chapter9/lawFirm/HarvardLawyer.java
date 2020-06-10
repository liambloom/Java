package liam.chapter9.lawFirm;

import liam.Globals;

public class HarvardLawyer extends Lawyer {
    public double getSalary () {
        return super.getSalary() * 1.2;
    }
    public int getVacationDays () {
        return super.getVacationDays() + 3;
    }
    public String getVacationForm () {
        return Globals.repeat(super.getVacationForm(), 4);
    }
}
