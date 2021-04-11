package dev.liambloom.softwareEngineering.chapter17.intTree;

// Short program that demonstrates the use of the IntTree class.
// BJP 3rd Edition pg 1020-1023

public class Client {
    public static void main(String[] args) {
        IntTree t = new IntTree(12);

        System.out.println("Tree structure on its side:");
        t.printSideways();
        System.out.println();
        t.printPreorder();
        t.printInorder();
        t.printPostorder();
    }
}
