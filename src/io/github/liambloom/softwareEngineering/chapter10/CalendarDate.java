package io.github.liambloom.softwareEngineering.chapter10;

public class CalendarDate implements Comparable<CalendarDate> {
    private int month;
    private int day;

    public CalendarDate(int month, int day) {
        this.month = month;
        this.day = day;
    }

    public int getMonth() {
        return month;
    }
    public int getDay() {
        return day;
    }

    public int compareTo(CalendarDate other) {
        if (month != other.month) {
            return month - other.month;
        } else {
            return day - other.day;
        }
    }
    public String toString() {
        return month + "/" + day;
    }
}