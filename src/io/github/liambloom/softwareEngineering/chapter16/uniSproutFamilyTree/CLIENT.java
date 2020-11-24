package io.github.liambloom.softwareEngineering.chapter16.uniSproutFamilyTree;

import java.util.Scanner;

public class CLIENT {
    static Node root;
    static String names[][] = { { "Jones", "ROOT-Node" }, { "Bill", "Jones" }, { "Katy", "Jones" }, { "Mike", "Jones" },
            { "Tom", "Jones" }, { "Dave", "Bill" }, { "Mary", "Bill" }, { "Leo", "Katy" }, { "Betty", "Mike" },
            { "Roger", "Mike" }, { "Larry", "Mary" }, { "Paul", "Mary" }, { "Penny", "Mary" }, { "Don", "Betty" },
            { "Petter", "Paul" }, { "Dave", "Don" } };

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
            System.out.print("\n\n Enter a name from which to get  GrandParent/Parent/Siblings/Children/GrandChildren: ");
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
        // >>>>>>>>>>>>>>>>>>>>> YOUR CODE HERE <<<<<<<<<<<<<<<<<<<<<<<<<<<
    } // buildFamilyTreeOfUniSprouts()

    // ============== placeNodeInFamilyTreeOfUniSprouts =================
    public static void placeNodeInFamilyTreeOfUniSprouts(Node parent,
            Node child) {
        // >>>>>>>>>>>>>>>>>>>>> YOUR CODE HERE <<<<<<<<<<<<<<<<<<<<<<<<<<<
    } // placeNodeInFamilyTreeOfUniSprouts()

    // ==================== printFamilyTreeOfUniSprouts ========================
    public static void printFamilyTreeOfUniSprouts(Node current) {
        // >>>>>>>>>>>>>>>>>>>>> YOUR CODE HERE <<<<<<<<<<<<<<<<<<<<<<<<<<<
    } // printFamilyTreeOfUniSprouts()

    // ========================= printRelatives ===============================
    public static void printRelatives(String name) {
        // >>>>>>>>>>>>>>>>>>>>> YOUR CODE HERE <<<<<<<<<<<<<<<<<<<<<<<<<<<
    } // printRelatives()

} // FamilyTreeOfUniSprouts_CLIENT
