package io.github.liambloom.softwareEngineering.chapter8.starWarsNames;

/*
 * Mr Marques MadeUp Problem: Name_StarWars
 * 12/16/2016
 * 
 * Above  defined is the Name class. Complete the below code in Main( ).. 
 * Note: the middle initial is either "R" for Republic (good guys)
 *       or "E" for Empire (bad guys).
 *       
    OUTPUT     
        n1 = Skywalker R Luke
        n2 = Skywalker E Anakin
        n3 = Skywalker E Anakin
       
        Original List: 
            Skywalker R Luke
            Skywalker E Anakin
            Skywalker R Leia
            Solo R Han
            Solo E Kylo Ren
             R Yoda
            Droid R R2D2
            Droid R C3PO
            Wookie R Chewbacca
            Vader E Darth
            Fett E Boba
        
        
        
        Sorted List: 
            R Yoda
            Droid R C3PO
            Droid R R2D2
            Fett E Boba
            Skywalker E Anakin
            Skywalker R Leia
            Skywalker R Luke
            Solo E Kylo Ren
            Solo R Han
            Vader E Darth
            Wookie R Chewbacca

        Most common last name = Skywalker
        Shortest total name =  R Yoda
        Largest group in Names = Republic: 7 
*/


public class CLIENT {
    public static void main(String[] args) {
        Name n1 = new Name("Luke", "R", "Skywalker");
        Name n2 = new Name("Anakin", "E", "Skywalker");
        Name n3 = n2;

        // Output n1-n3
        System.out.println("   n1 = " + n1);
        System.out.println("   n2 = " + n2);
        System.out.println("   n3 = " + n3);
        System.out.println();

        // Fill array with names.
        Name names[] = { n1, n2, new Name("Leia", "R", "Skywalker"), new Name("Han", "R", "Solo"),
                new Name("Kylo Ren", "E", "Solo"), new Name("Yoda", "R", ""), new Name("R2D2", "R", "Droid"),
                new Name("C3PO", "R", "Droid"), new Name("Chewbacca", "R", "Wookie"), new Name("Darth", "E", "Vader"),
                new Name("Boba", "E", "Fett") };

        // call printList and output names array
        System.out.println("Original List: ");
        printList(names);
        System.out.println("\n\n");

        // sort list by last then first name
        System.out.println("Sorted List: ");
        sortList(names);
        printList(names);
        System.out.println("\n\n");

        System.out.println("Most common last name = " + mostCommonLastName(names));
        System.out.println("Shortest total name = " + shortestTotalName(names));
        System.out.println("Largest group in Names = " + largestMembersInNames(names));

    } // main

    // ===========================================================================
    // Print the entire array
    public static void printList(Name names[]) {
        System.out.print('[');
        if (names.length > 0)
            System.out.print(names[0]);
        for (int i = 1; i < names.length; i++)
            System.out.print(", " + names[i]);
        System.out.println(']');
    }

    // ===========================================================================
    // Sort the list by last/first name via BubbleSort OR any sort you want,
    // but YOU write the code….and NOT using Arrays.sort(nameList)
    public static void sortList(Name nameList[]) {
        if (nameList.length <= 1)
            return;
        Name[] l1 = new Name[(nameList.length + 1) / 2];
        Name[] l2 = new Name[nameList.length / 2];
        System.arraycopy(nameList, 0, l1, 0, l1.length);
        System.arraycopy(nameList, l1.length, l2, 0, l2.length);
        sortList(l1);
        sortList(l2);
        int i = 0, i1 = 0, i2 = 0;
        while (i1 < l1.length && i2 < l2.length) {
            if (l1[i1].compareTo(l2[i2]) < 0)
                nameList[i++] = l1[i1++];
            else
                nameList[i++] = l2[i2++];
        }
        System.arraycopy(l1, i1, nameList, i, l1.length - i1);
        System.arraycopy(l2, i2, nameList, i, l2.length - i2);
    }

    // ===========================================================================
    // Return the most common last name found,null if none.If more than one exists
    // common, pick the first one.
    public static String mostCommonLastName(Name list[]) {
        // TODO
        // He expects the O(n^2) way from most people, but I can do it
        // using a map (O(n)) if I want.
    }

    // ==============================================================================
    // Find and return the Name that is the shortest in total.If more than one
    // exists
    // common, pick the first one.
    public static Name shortestTotalName(Name n[]) {
        if (n.length == 0)
            throw new IllegalArgumentException();
        Name shortest = n[0];
        for (int i = 1; i < n.length; i++) {
            if (n[i].toString().length() < shortest.toString().length())
                shortest = n[i];
        }
        return shortest;
    }

    // ===========================================================================
    // Return the group that has the largest number of members (R or E)
    public static String largestMembersInNames(Name nl[]) {
        int rc = 0;
        int ec = 0;
        for (Name n : nl) {
            switch (n.middleInitial) {
                case "R":
                    rc++;
                    break;
                case "E":
                    ec++;
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }
        if (rc > ec)
            return "R";
        else if (ec > rc)
            return "E";
        else
            return "Tie";
    }

} // Name_StarWars_CLIENT
