package dev.liambloom.softwareEngineering.chapter9.lawFirm;

public abstract class Employee {
    public int getHours () {
        return 40;
    }
    public double getSalary () {
        return 40000.0;
    }
    public int getVacationDays () {
        return 10;
    }
    public String getVacationForm () {
        return "yellow";
    }
    @Override
    public boolean equals(Object o) {
        return o instanceof Employee && this.getSalary() == ((Employee) o).getSalary();
    }
}
