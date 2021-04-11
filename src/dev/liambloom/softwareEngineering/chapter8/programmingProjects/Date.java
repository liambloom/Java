package dev.liambloom.softwareEngineering.chapter8.programmingProjects;

public class Date { // PP2
    public static void main(String[] args) {
        Date d = new Date(2007, 7, 22);
        System.out.println(d);
        d.addDays(365);
        System.out.println(d);
    }

    private int year;
    private int month;
    private int day;

    public Date(int year, int month, int day) {
        this.year = year;
        if (month < 1 || month > 12)
            throw new IllegalArgumentException("Month must be between 1 and 12, received " + month);
        else
            this.month = month;
        if (day < 1 || day > daysInMonth())
            throw new IllegalArgumentException("Cannot be on day " + day);
        else
            this.day = day;
    }

    private int daysInMonth() {
        return daysInMonth(month);
    }

    private int daysInMonth(int month) {
        switch (month) {
            case 1:
            case 3:
            case 5: 
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                return isLeapYear() ? 29 : 28;
            default:
                throw new IllegalArgumentException();
        }
    }

    private long getTotalDays() {
        long days = 365 * year - year / 4 + year / 100 - year / 400;
        for (int i = 1; i < month; i++)
            days += daysInMonth(i);
        days += day;
        return days;
    }

    public boolean isLeapYear() {
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public void addDays(int days) {
        if (days < 0)
            throw new IllegalArgumentException("Cannot move date backwards");
        else if (daysInMonth() - day - days < 0) {
            days -= daysInMonth() - day;
            day = 0;
            if (month == 12) {
                month = 1;
                year++;
            }
            else {
                month++;
                for (int dInM; month <= 12 && days > (dInM = daysInMonth()); month++, days -= dInM);
                if (month == 13) {
                    month = 1;
                    year++;
                }
            }
            for (int daysInYear; days > (daysInYear = isLeapYear() ? 365 : 366); days -= daysInYear, year++);
            for (int dInM; days > (dInM = daysInMonth()); month++, days -= dInM);    
        }
        day += days;
    }

    public void addWeeks(int weeks) {
        addDays(weeks * 7);
    }

    public int daysTo(Date other) {
        return (int) (other.getTotalDays() - this.getTotalDays());
    }

    public String toString() {
        return String.format("%d/%02d/%02d", year, month, day);
    }
}