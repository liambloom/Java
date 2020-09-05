package io.github.liambloom.softwareEngineering.chapter8;

public class TimeSpan {
    private int minutes = 0; // Here, it makes sense to be private, as it makes the code simpler

    public TimeSpan (int hours, int minutes) {
        add(hours, minutes);
    }

    public int getHours () {
        return minutes / 60;
    }
    public int getMinutes () {
        return minutes % 60;
    }
    public int getTotalMinutes () {
        return minutes;
    }

    public void add (int minutes) {
        if (minutes < 0) throw new IllegalArgumentException("Cannot have negative time");
        this.minutes += minutes;
    }
    public void add (int hours, int minutes) {
        add(60 * hours + minutes);
    }
    public void add (TimeSpan span) { // Exercise 7
        add(span.getTotalMinutes());
    }
    public void subtract (int minutes) {
        add(-minutes);
    }
    public void subtract (int hours, int minutes) {
        add(-hours, -minutes);
    }
    public void subtract (TimeSpan span) { // Exercise 8
        add(-span.getTotalMinutes());
    }
    public void scale (int k) { // Exercise 9
        minutes *= k;
    }

    public String toString () {
        return getHours() + "h " + getMinutes() + "m";
    }
    public boolean equals (TimeSpan span) {
        return this.minutes == span.minutes;
    }
}
