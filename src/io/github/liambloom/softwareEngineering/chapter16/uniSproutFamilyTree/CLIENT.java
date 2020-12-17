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
        }
    } // buildFamilyTreeOfUniSprouts()

    private static Node findNode(String name) {
        if (root == null)
            throw new IllegalStateException();
        return new Ancestors(name, root).self;
    }

    // ============== placeNodeInFamilyTreeOfUniSprouts =================
    public static void placeNodeInFamilyTreeOfUniSprouts(Node parent, Node child) {
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
        colCount += subtrees.size() - 1;
        if (name.length() > colCount) {
            if (rowCount > 1)   {
                final String[] firstCol = subtrees.get(0);
                final String firstBorder = " ".repeat((name.length() - colCount) / 2);
                for (int i = 0; i < firstCol.length; i++)
                    firstCol[i] = firstBorder + firstCol[i];
                
                final String[] lastCol = subtrees.get(subtrees.size() - 1);
                final String lastBorder = " ".repeat((name.length() - colCount + 1) / 2);
                for (int i = 0; i < lastCol.length; i++)
                    lastCol[i] += lastBorder;
            }
            colCount = name.length();
        }
        rowStrings[0] = " ".repeat((colCount - name.length()) / 2)
            + name
            + " ".repeat((colCount - name.length() + 1) / 2);
        if (rowCount > 1) {
            final StringBuilder[] builders = new StringBuilder[rowStrings.length - 2];
            for (int i = 0; i < builders.length; i++)
                builders[i] = new StringBuilder(colCount);
            final char[] branchChars = new char[colCount];
            for (int i = 0; i < branchChars.length; i++)
                branchChars[i] = '\u2500';
            final int center = (colCount - 1) / 2;
            int i = 0;
            final ListIterator<String[]> iter = subtrees.listIterator();
            while (iter.hasNext()) {
                final boolean isFirst = !iter.hasPrevious();
                final String[] col = iter.next();
                final int subColSize = col[0].length();
                final boolean isLast = !iter.hasNext();
                final int subCenter = (subColSize - 1) / 2;

                char centerChar = '\u252c';
                if (isFirst ^ isLast) {
                    if (isFirst) {
                        centerChar = '\u250c';
                        for (int j = 0; j < subCenter; j++)
                            branchChars[j] = ' ';
                    }
                    else { // isLast
                        centerChar = '\u2510';
                        for (int j = i + subCenter; j < branchChars.length; j++)
                            branchChars[j] = ' ';
                    }
                }
                else if (isFirst && isLast) {
                    centerChar = ' ';
                    for (int j = 0; j < branchChars.length; j++)
                        branchChars[j] = ' ';
                }
                branchChars[i + subCenter] = centerChar;

                for (int j = 0; j < col.length; j++) {
                    builders[j].append(col[j]);
                    assert col[j].length() == subColSize;
                }
                for (int j = col.length; j < builders.length; j++)
                    builders[j].append(" ".repeat(subColSize));

                if (!isLast) {
                    for (StringBuilder builder : builders)
                        builder.append(' ');
                }
                
                i += subColSize + 1;
            }

            char centerChar;
            switch (branchChars[center]) {
                case '\u2500':
                    centerChar = '\u2534';
                    break;
                case '\u252c':
                    centerChar = '\u253c';
                    break;
                case ' ':
                    centerChar = '\u2502';
                    break;
                default:
                    throw new IllegalStateException("Illegal central character '" + branchChars[center] + "' in \"" + new String(branchChars) + '"'); 
            }
            branchChars[center] = centerChar;

            rowStrings[1] = new String(branchChars);

            for (i = 0; i < builders.length; i++)
                rowStrings[i + 2] = builders[i].toString();
        }

        return rowStrings;
    }

    // ========================= printRelatives ===============================
    public static void printRelatives(String name) {
        Ancestors ancestors;
        try {
            ancestors = new Ancestors(name, root);
        }
        catch (NoSuchElementException e) {
            System.out.println("There is no one named " + name);
            return;
        }

        System.out.println("Grandparent: " + ancestors.grandparent.getName());
        System.out.println("Parent: " + ancestors.parent.getName());

        RelativeListBuilder siblings = new RelativeListBuilder("Sibling");
        RelativeListBuilder cousins = new RelativeListBuilder("Cousin");
        for (Node c = ancestors.grandparent.getChildren(); c != null; c = c.getNext()) {
            RelativeListBuilder cTracker = c == ancestors.parent ? siblings : cousins;

            for (Node d = c.getChildren(); d != null; d = d.getNext()) {
                if (d == ancestors.self)
                    continue;
                cTracker.append(d);
            }
        }
        System.out.println(siblings);
        System.out.println(cousins);

        RelativeListBuilder children = new RelativeListBuilder("Children");
        RelativeListBuilder grandchildren = new RelativeListBuilder("Grandchildren");
        for (Node c = ancestors.self.getChildren(); c != null; c = c.getNext()) {
            children.append(c);

            for (Node g = c.getChildren(); g != null; g = g.getNext())
                grandchildren.append(g);
        }
        System.out.println(children);
        System.out.println(grandchildren);

    } // printRelatives()

} // FamilyTreeOfUniSprouts_CLIENT
