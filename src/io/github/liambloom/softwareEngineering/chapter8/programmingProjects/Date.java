package io.github.liambloom.softwareEngineering.chapter8.programmingProjects;

public class Date {
    private long days;

    public Date(int year, int month, int day) {
        days = 365 * year - year / 4 + year / 100 - year / 400;
        if (month < 1 || month > 12)
            throw new IllegalArgumentException("Month must be between 1 and 12, received " + month);
        final boolean isLeapYear = isLeapYear(year);
        for (int i = 1; i < month; i++)
            days += daysInMonth(i, isLeapYear);
        if (day < 1 || day > daysInMonth(month, isLeapYear))
            throw new IllegalArgumentException("Cannot be on day " + day);
        days += day;
    }

    private static int daysInMonth(int month, boolean leapYear) {
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
                return leapYear ? 29 : 28;
            default:
                throw new IllegalArgumentException();
        }
    }

    private static boolean isLeapYear(int year) {
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
    }
}