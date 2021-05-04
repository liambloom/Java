package dev.liambloom.softwareEngineering.circus;

import dev.liambloom.softwareEngineering.chapter6.Ask;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.function.Predicate;

public class Circus {
    public static final String EMPLOYEES_FILE = "employees.txt";

    private final SortedMap<String, NavigableSet<Employee>> categoryMap = new TreeMap<>();

    public static void main(String[] args) throws FileNotFoundException {
        Circus circus = new Circus(new File(EMPLOYEES_FILE));
        Scanner s = new Scanner(System.in);

        while (true) {
            System.out.println("""
                Menu:
                    (1) Print list alphabetically
                    (2) Print list by IdNum
                    (3) Insert new Employee
                    (4)	Delete an Employee
                    (5)	Print only a particular category list of employees alphabetically
                    (6)	Print entire list of all employees by category alphabetically
                    (7)	Add a category
                    (8)	Delete a category (and ALL corresponding employees)
                    (9)	Quit""");
            switch (Ask.forInt("Selection", 1, 9, "That's not a valid menu option")) {
                // TODO
                case 9:
                    return;
            }
        }
    }

    public Circus(File employees) throws FileNotFoundException {
        new BufferedReader(new FileReader(employees)).lines()
                .sequential()
                .filter(((Predicate<String>) String::isBlank).negate())
                .map(Employee::new)
                .forEach(e -> categoryMap.computeIfAbsent(e.category(), k -> new TreeSet<>(Employee.nameComparator)).add(e));
    }
}
