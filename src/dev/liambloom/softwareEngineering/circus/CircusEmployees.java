package dev.liambloom.softwareEngineering.circus;

import dev.liambloom.softwareEngineering.chapter6.Ask;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CircusEmployees {
    public static final String EMPLOYEES_FILE = "employees.txt";

    private final SortedMap<String, NavigableSet<Employee>> categoryMap = new TreeMap<>();

    public static void main(String[] args) throws FileNotFoundException {
        CircusEmployees circus = new CircusEmployees(new File(EMPLOYEES_FILE));
        Scanner s = new Scanner(System.in);

        // TODO: Read from scanner to initialize categoryMap

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
                case 1:
                    circus.printAlphabetically();
                    break;
                case 2:
                    circus.printByIdNum();
                    break;
                case 3:
                    circus.insertEmployee();
                // TODO
                case 9:
                    return;
            }
        }
    }

    public CircusEmployees(File employees) throws FileNotFoundException {
        Scanner s = new Scanner(employees);
        while (s.hasNext()) {
            Employee newEmployee = new Employee(s);
            insertEmployeeInternal(newEmployee);
            s.skip("(?:\\s*\\r?\\n?)*");
        }
    }

    public void printAlphabetically() {
        System.out.println(Employee.header());

        PriorityQueue<PeekableIterator<Employee>> pq =
                new PriorityQueue<>(Comparator.comparing(PeekableIterator::peek, Employee.nameComparator));

        for (SortedSet<Employee> e : categoryMap.values())
            pq.add(PeekableIterator.from(e.iterator()));

        while (!pq.isEmpty()) {
            PeekableIterator<Employee> iter = pq.poll();
            System.out.println(iter.next());
            if (iter.hasNext())
                pq.add(iter);
        }
    }

    public void printByIdNum() {
        System.out.println(Employee.header());

        categoryMap
                .values()
                .stream()
                .flatMap(Collection::stream)
                .sorted(Employee.idNumComparator)
                .forEachOrdered(System.out::println);
    }

    public void insertEmployee() {
        insertEmployeeInternal(new Employee(
                Ask.forString("Last Name"),
                Ask.forString("First Name"),
                Ask.forChar("Middle Initial"),
                Employee.requireUniqueIdNum(Ask.forString("Id Number")),
                Ask.forString("Category").intern(),
                Ask.forString("Title")
        ));
        System.out.println("Employee inserted");
    }

    public void insertEmployeeInternal(Employee e) {
        categoryMap.computeIfAbsent(e.category(),
                k -> new TreeSet<>(Employee.nameComparator))
                .add(e);
    }
}
