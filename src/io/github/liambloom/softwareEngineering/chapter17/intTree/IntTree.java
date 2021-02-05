package io.github.liambloom.softwareEngineering.chapter17.intTree;

// Simple binary tree class that includes methods to construct a
// tree of ints, to print the structure, and to print the data
// using a preorder, inorder, or postorder traversal. The trees
// built have nodes numbered starting with 1 and numbered
// sequentially level by level with no gaps in the tree. The
// documentation refers to these as "sequential trees."
// BJP 3rd Edition pg 1020-1023

import io.github.liambloom.tests.Tester;

public class IntTree {
public static final IntTree ref1 = new IntTree(new IntTreeNode(3, new IntTreeNode(5, new IntTreeNode(1), null),
        new IntTreeNode(2, new IntTreeNode(4), new IntTreeNode(6))));
public static final IntTree ref2 = new IntTree(
        new IntTreeNode(2, new IntTreeNode(8, new IntTreeNode(0), null), new IntTreeNode(1,
                new IntTreeNode(7, new IntTreeNode(4), null), new IntTreeNode(6, null, new IntTreeNode(9)))));
public static final IntTree ref3 = new IntTree(
        new IntTreeNode(2, new IntTreeNode(3, new IntTreeNode(8), new IntTreeNode(7)), new IntTreeNode(1)));

    public static void main(String[] args) {
        Tester tester = new Tester(Tester.Policy.RunLast);
        tester
            .test(ref1::countLeftNodes, 3)
            .test(ref1::countEmpty, 7)
            .test(ref1::depthSum, 50)
            .test(ref2::countEvenBranches, 3)
            .testOutput(() -> ref2.printLevel(3), "0\n7\n6\n");

        tester.close();
    }

    private IntTreeNode overallRoot;

    private IntTree(IntTreeNode root) {
        overallRoot = root;
    }

    public IntTree(int max) {
        if (max <= 0) {
            throw new IllegalArgumentException("max: " + max);
        }
        overallRoot = buildTree(1, max);
    }

    // post: returns a sequential tree with n as its root unless
    // n is greater than max, in which case it returns an
    // empty tree
    private IntTreeNode buildTree(int n, int max) {
        if (n > max) {
            return null;
        } else {
            return new IntTreeNode(n, buildTree(2 * n, max), buildTree(2 * n + 1, max));
        }
    }

    // post: prints the tree contents using a preorder traversal
    public void printPreorder() {
        System.out.print("preorder:");
        printPreorder(overallRoot);
        System.out.println();
    }

    // post: prints in preorder the tree with given root
    private void printPreorder(IntTreeNode root) {
        if (root != null) {
            System.out.print(" " + root.data);
            printPreorder(root.left);
            printPreorder(root.right);
        }
    }

    // post: prints the tree contents using an inorder traversal
    public void printInorder() {
        System.out.print("inorder:");
        printInorder(overallRoot);
        System.out.println();
    }

    // post: prints in inorder the tree with given root
    private void printInorder(IntTreeNode root) {
        if (root != null) {
            printInorder(root.left);
            System.out.print(" " + root.data);
            printInorder(root.right);
        }
    }

    // post: prints the tree contents using a postorder traversal
    public void printPostorder() {
        System.out.print("postorder:");
        printPostorder(overallRoot);
        System.out.println();
    }

    // post: prints in postorder the tree with given root
    private void printPostorder(IntTreeNode root) {
        if (root != null) {
            printPostorder(root.left);
            printPostorder(root.right);
            System.out.print(" " + root.data);
        }
    }

    // post: prints the tree contents, one per line, following an
    // inorder traversal and using indentation to indicate
    // node depth; prints right to left so that it looks
    // correct when the output is rotated.
    public void printSideways() {
        printSideways(overallRoot, 0);
    }

    // post: prints in reversed preorder the tree with given
    // root, indenting each line to the given level
    private void printSideways(IntTreeNode root, int level) {
        if (root != null) {
            printSideways(root.right, level + 1);
            for (int i = 0; i < level; i++) {
                System.out.print(" ");
            }
            System.out.println(root.data);
            printSideways(root.left, level + 1);
        }
    }

    // Exercise 1
    public int countLeftNodes() {
        return countLeftNodes(overallRoot);
    }

    private int countLeftNodes(IntTreeNode root) {
        return root == null ? 0 : 1 + countLeftNodes(root.left);
    }

    // Exercise 2
    public int countEmpty() {
        return countEmpty(overallRoot);
    }

    private int countEmpty(IntTreeNode root) {
        return root == null ? 1 : countEmpty(root.left) + countEmpty(root.right);
    }

    // Exercise 3
    public int depthSum() {
        return depthSum(overallRoot, 1);
    }

    private int depthSum(IntTreeNode root, int level) {
        return root == null ? 0 : root.data * level + depthSum(root.left, level + 1) + depthSum(root.right, level + 1);
    }

    // Exercise 4
    public int countEvenBranches() {
        return countEvenBranches(overallRoot);
    }

    private int countEvenBranches(IntTreeNode root) {
        return root == null || root.left == null && root.right == null 
            ? 0 
            : (root.data + 1) % 2 + countEvenBranches(root.left) + countEvenBranches(root.right);
    }

    // Exercise 5
    public void printLevel(int level) {
        if (level < 1)
            throw new IllegalArgumentException();
        printLevel(overallRoot, 1, level);
    }

    private void printLevel(IntTreeNode root, int currentLevel, int targetLevel) {
        if (root == null)
            return;
        if (currentLevel < targetLevel) {
            printLevel(root.left, currentLevel + 1, targetLevel);
            printLevel(root.right, currentLevel + 1, targetLevel);
        }
        else if (currentLevel == targetLevel)
            System.out.println(root.data);
    }
}
