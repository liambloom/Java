package io.github.liambloom.softwareEngineering.chapter16.uniSproutFamilyTree;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class CLIENT {
    static Node root = null;
    static String names[][] = { { "Jones", "ROOT-Node" }, { "Bill", "Jones" }, { "Katy", "Jones" }, { "Mike", "Jones" },
            { "Tom", "Jones" }, { "Dave1", "Bill" }, { "Mary", "Bill" }, { "Leo", "Katy" }, { "Betty", "Mike" },
            { "Roger", "Mike" }, { "Larry", "Mary" }, { "Paul", "Mary" }, { "Penny", "Mary" }, { "Don", "Betty" },
            { "Petter", "Paul" }, { "Dave2", "Don" } };

    public static void main(String[] args) {
        int playAgain;
        String name, namesList= "\n"; 
        Scanner sc = new Scanner(System.in);
        
        // Build the Family of UniSprouts Tree       
        buildFamilyTreeOfUniSprouts();
        
        do {
            
            // Gather names
            for (int r=0; r<names.length; r++) {
                namesList += "   " + names[r][0] + "     \t" + names[r][1] + "\n";                
            }
            
            // Output namesList         
            System.out.println(" nameList: \n  Parent Child" + namesList);
          
            printFamilyTreeOfUniSprouts(root);            
            
            // Input name + print all relatives
            System.out.print("\n\n Enter a name from which to get  GrandParent/Parent/Siblings/Cousins/Children/GrandChildren: ");
            name = sc.next();
            printRelatives(name);
                                   
            // Play Again?
            System.out.print("\n\n Play Again? (1==yes, 2==no): ");
            playAgain = sc.nextInt();            
            
        } while (playAgain == 1);

        sc.close();
    } // main

    // ==================== buildFamilyTreeOfUniSprouts ======================
    public static void buildFamilyTreeOfUniSprouts() {
        for (String[] name : names) {
            final Node parent = name[1].equals("ROOT-Node") ? null : findNode(name[1]);
            Node newNode;
            if (parent == null)
                newNode = new Node(name[0], 1, null);
            else {
                Node prev = null;
                for (Node c = parent.getChildren(); c != null; c = c.getNext())
                    prev = c;
                newNode = new Node(name[0], parent.getGenerationLevel() + 1, prev);
            }
            placeNodeInFamilyTreeOfUniSprouts(parent, newNode);
            printFamilyTreeOfUniSprouts(root);
        }
    } // buildFamilyTreeOfUniSprouts()

    private static Node findNode(String name) {
        if (root == null)
            throw new IllegalStateException();
        final Node n = name == root.getName() ? root : findNodeInner(name, root);
        if (n == null)
            throw new NoSuchElementException("No element named " + name);
        return n;
    }

    private static Node findNodeInner(String name, Node under) {
        for (Node current = under.getChildren(); current != null; current = current.getNext()) {
            System.out.println("checked " + current.getName());
            if (current.getName().equals(name))
                return current;
            else {
                final Node n = findNodeInner(name, current);
                if (n != null)
                    return n;
            }
        }
        return null;
    }

    // ============== placeNodeInFamilyTreeOfUniSprouts =================
    public static void placeNodeInFamilyTreeOfUniSprouts(Node parent, Node child) {
        System.out.println("Inserting " + child.getName());
        if ((root == null) != (parent == null))
            throw new IllegalArgumentException();
        if (parent == null) 
            root = child;
        else {
            final Node prev = child.getPrevious();
            if (prev == null)
                parent.setChildren(child);
            else
                prev.setNext(child);
        }
    } // placeNodeInFamilyTreeOfUniSprouts()

    // ==================== printFamilyTreeOfUniSprouts ========================
    public static void printFamilyTreeOfUniSprouts(Node current) {
        for (String row : subtreeToString(current))
            System.out.println(row);
    } // printFamilyTreeOfUniSprouts()

    public static String[] subtreeToString(Node node) {
        final List<String[]> subtrees = new LinkedList<>();
        int rowCount = 0;
        int colCount = 0;
        for (Node c = node.getChildren(); c != null; c = c.getNext()) {
            final String[] subtree = subtreeToString(c);
            rowCount = Math.max(subtree.length, rowCount);
            colCount += subtree[0].length();
            subtrees.add(subtree);
        }
        if (rowCount == 0)
            rowCount++;
        else
            rowCount += 2;
        final String[] rowStrings = new String[rowCount];
        final String name = node.getName();
        colCount = Math.max(colCount + subtrees.size() - 1, name.length());
        rowStrings[0] = " ".repeat((colCount - name.length() - 1) / 2)
            + name
            + " ".repeat((colCount - name.length()) / 2);
        if (rowCount > 1) {
            final StringBuilder[] builders = new StringBuilder[rowStrings.length - 1];
            for (int i = 0; i < builders.length; i++)
                builders[i] = new StringBuilder();
            final int center = (colCount - 1) / 2;
            int i = 0;
            final ListIterator<String[]> iter = subtrees.listIterator();
            while (iter.hasNext()) {
                final boolean isFirst = !iter.hasPrevious();
                final String[] col = iter.next();
                final int subColSize = col[0].length();
                final boolean isLast = !iter.hasNext();
                final boolean containsCenter = center > i && center < i + subColSize;
                final int subCenter = (subColSize - 1) / 2;
                final int sectionCount = containsCenter && center - i != subCenter && !(isFirst && isLast) ? 3 : 2;
                final int[] sectionLengths = new int[sectionCount];
                sectionLengths[0] = subCenter;
                sectionLengths[sectionLengths.length - 1] = subColSize - subCenter - 1;
                final char[] sectionChars = new char[sectionCount];
                for (int j = 0; j < sectionChars.length; j++)
                    sectionChars[j] = '\u2500';
                final char[] dividerChars = new char[sectionCount - 1];
                for (int j = 0; j < dividerChars.length; j++)
                    dividerChars[j] = '\u252c';

                if (isFirst) {
                    sectionChars[0] = ' ';
                    if (isLast) {
                        sectionChars[1] = ' ';
                        dividerChars[0] = '\u2502';
                    }
                    else 
                        dividerChars[0] = '\u250c';
                }
                else if (isLast) {
                    sectionChars[sectionChars.length - 1] = ' ';
                    dividerChars[dividerChars.length - 1] = '\u2510';
                }

                if (containsCenter && !(isFirst && isLast)) {
                    final int c = Integer.valueOf(center).compareTo(subCenter);
                    if (c < 0) {
                        dividerChars[0] = '\u2534';
                        sectionLengths[0] = center - i;
                        sectionLengths[1] = subCenter - (center - i);
                    }
                    else if (c > 0){
                        dividerChars[dividerChars.length - 1] = '\u2534';
                        sectionLengths[sectionLengths.length - 2] = center - i - subCenter;
                        sectionLengths[sectionLengths.length - 1] = subColSize - (center - i);
                    }
                    else if (col.length == 0)
                        dividerChars[0] = '\u2502';
                    else
                        dividerChars[0] = '\u253c';
                }

                for (int j = 0; j < sectionCount; j++) {
                    for (int k = 0; k < sectionLengths[j]; k++)
                        builders[0].append(sectionChars[j]);
                    if (j + 1 != sectionCount)
                        builders[0].append(dividerChars[j]);
                }

                for (int j = 0; j < col.length; j++) {
                    if (i > 0) {
                        builders[j + 1].append(' ');
                        System.out.println("Adding space");
                    }
                    builders[j + 1].append(col[j]);
                }
                
                i += col[0].length();
            }

            for (i = 0; i < builders.length; i++)
                rowStrings[i + 1] = builders[i].toString();
        }

        return rowStrings;
    }

    // ========================= printRelatives ===============================
    public static void printRelatives(String name) {
        // TODO
    } // printRelatives()

} // FamilyTreeOfUniSprouts_CLIENT
