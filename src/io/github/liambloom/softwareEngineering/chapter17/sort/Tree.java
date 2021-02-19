package io.github.liambloom.softwareEngineering.chapter17.sort;

import java.util.Comparator;
import java.util.List;

public class Tree {
    private TreeNode root;
    private final Comparator<PersonNode> cmp;

    public Tree(List<PersonNode> list, Comparator<PersonNode> cmp) {
        this.cmp = cmp;
        for (PersonNode node : list)
            add(node);
    }

    public void add(PersonNode node) {
        root = add(root, node);
    }

    private TreeNode add(TreeNode parent, PersonNode node) {
        if (parent == null)
            return new TreeNode(node);
        else if (cmp.compare(node, parent.value) <= 0)
            parent.left = add(parent.left, node);
        else
            parent.right = add(parent.right, node);
        return parent;
    }

    public void printInOrder() {
        System.out.println("Sorted via " + cmp.getClass().getSimpleName());
        System.out.println(" Last Name   | First Name  | Middle Name | ID Number  ");
        System.out.println("-------------+-------------+-------------+------------");
        printInOrder(root);
        System.out.println();
    }

    private static void printInOrder(final TreeNode node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(' ');
            for (final String column : new String[]{node.value.getLastName(), node.value.getFirstName(), node.value.getMiddleName()}) {
                if (column.length() > 11)
                    System.out.print(column.substring(0, 8) + "...");
                else
                    System.out.print(column + " ".repeat(11 - column.length()));
                System.out.print(" | ");
            }
            System.out.println(node.value.getIdNum());
            printInOrder(node.right);
        }
    }
}
