package dev.liambloom.softwareEngineering.chapter13.programmingPojects.pp3;

import java.util.Comparator;

public class Student {
    public String lastName, firstName;
    public String idNum;
    public Double average;
    public char letterGrade;

    public Student() {
        this("", "", "", 0.0, ' ');
    }

    public Student(String lastName, String firstName, String idNum, double average, char letterGrade) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.idNum = idNum;
        this.average = average;
        this.letterGrade = letterGrade;
    }

    public String toString() {
        return lastName + " " + firstName + " " + idNum + " " + average + " " + letterGrade;
    }

    public static class Comparators {
        /*
         * You said you wanted them sorted by five things, but the CLIENT
         * code only sorts by 4 things, and you only listed 4 things (which
         * were not the same 4 things the CLIENT sorted by). Since I don't
         * know what you meant by "grade percentage," so I just went with 
         * the original Programming Project 3, which was to be able to sort
         * by each of the 5 fields. I hope that's what you wanted.
         */

        private Comparators () { }

        public final static class LastName implements Comparator<Student> {
            public int compare(Student s1, Student s2) {
                int cln = s1.lastName.compareTo(s2.lastName);
                if (cln == 0) {
                    int cfn = s1.firstName.compareTo(s2.firstName);
                    return cfn == 0 ? s1.idNum.compareTo(s2.idNum) : cfn;
                }
                else
                    return cln;
            }
        }

        public final static class FirstName implements Comparator<Student> {
            public int compare(Student s1, Student s2) {
                int cfn = s1.firstName.compareTo(s2.firstName);
                if (cfn == 0) {
                    int cln = s1.lastName.compareTo(s2.lastName);
                    return cln == 0 ? s1.idNum.compareTo(s2.idNum) : cln;
                } 
                else
                    return cfn;
            }
        }

        public final static class ID implements Comparator<Student> {
            public int compare(Student s1, Student s2) {
                return s1.idNum.compareTo(s2.idNum);
            }
        }

        public final static class Average implements Comparator<Student> {
            public int compare(Student s1, Student s2) {
                final Double diff = s1.average - s2.average;
                return diff > 0 ? 1 : diff < 0 ? -1 : s1.idNum.compareTo(s2.idNum);
            }
        }

        public final static class LetterGrade implements Comparator<Student> {
            public int compare(Student s1, Student s2) {
                int c = s1.letterGrade - s2.letterGrade;
                return c == 0 ? c : s1.idNum.compareTo(s2.idNum);
            }
        }
    }
} // Student
