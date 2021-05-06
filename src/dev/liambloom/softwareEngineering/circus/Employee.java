package dev.liambloom.softwareEngineering.circus;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public record Employee(String lastname, String firstname, char middleInitial, String idNum, String category, String title) {
    public static final Pattern lastNamePattern = Pattern.compile("", Pattern.CASE_INSENSITIVE);

    private static final Set<String> idNums = new HashSet<>();
    private static final String[] headers = { "Name", "Id", "Category", "Title" };
    private static final int[] columnWidths = new int[headers.length];

    static { setColumnWidths(headers); }

     public Employee {
        idNums.add(idNum);
        setColumnWidths(new String[]{fullname(), idNum, category, title});
    }

    private static void setColumnWidths(String[] s) {
        if (s.length != headers.length)
            throw new IllegalArgumentException("Column widths cannot be set by array of different length than number of columns");
        for (int i = 0; i < headers.length; i++) {
            int l = s[i].length() + 2;
            if (columnWidths[i] < l)
                columnWidths[i] = l;
        }
    }

    public Employee(Scanner s) {
        this(s.next())
    }

    private static String template() {
        return Arrays.stream(columnWidths).mapToObj(w -> " %-" + w + "s ").collect(Collectors.joining("|"));
    }

    @Override
    public String toString() {
        return String.format(template(),
                lastname + ", " + firstname + (middleInitial == '#' ? "" : " " + middleInitial + "."),
                idNum, category, title);
    }

    public String fullname() {
        return lastname + ", " + firstname + (middleInitial == '#' ? "" : " " + middleInitial + ".");
    }

    public static String header() {
        return String.format(template(), (Object[]) headers) + System.lineSeparator()
                + Arrays.stream(columnWidths)
                        .mapToObj(w -> "-".repeat(w + 2))
                        .collect(Collectors.joining("+"));
    }

    public static String requireUniqueIdNum(String idNum) {
        if (idNums.contains(idNum))
            throw new IllegalArgumentException("The id number " + idNum + " is already taken");
        return idNum;
    }

    public static final Comparator<Employee> nameComparator = Comparator.comparing(Employee::lastname)
            .thenComparing(Employee::firstname)
            .thenComparing(Employee::middleInitial);

    public static final Comparator<Employee> idNumComparator = Comparator.comparing(Employee::idNum);
}
