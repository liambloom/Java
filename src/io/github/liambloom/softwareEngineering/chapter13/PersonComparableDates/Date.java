package io.github.liambloom.softwareEngineering.chapter13.PersonComparableDates;

/**
 * Holds a date: month, day, year.
 */

public class Date implements Comparable<Date> {
    private int myMonth;
    private int myDay;
    private int myYear;

    public Date() { 
        // This date doesn't exist on the gregorian calendar
        setDate(0,0,0);
    }
    public Date(int m, int d, int y) {
        setDate(m,d,y);
    }

    public void setDate(int m, int d, int y) {
        setMonth(m);
        setDay(d);
        setYear(y);
    }
    
    // Mutators
    public void setMonth(int m) {
        myMonth = m;   
    }

    public void setDay(int d) {
        myDay = d;   
    }

    public void setYear(int y) {
        myYear = y;   
    }
    
    // Accessor
    public int getMonth() {
        return myMonth;   
    }

    public int getDay() {
         return myDay;   
    }

    public int getYear() {
        return myYear;
    }    

    public int compareTo(Date o) {
        if (myYear != o.myYear)
            return myYear - o.myYear;
        else if (myMonth != o.myMonth)
            return myMonth - o.myMonth;
        else
            return myDay - o.myDay;
    }
    
    public String toString() {   
        return ( myMonth + "/" + myDay +"/" + myYear);
    }
}
