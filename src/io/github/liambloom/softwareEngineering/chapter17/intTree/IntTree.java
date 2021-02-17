package io.github.liambloom.softwareEngineering.chapter17.intTree;

// Simple binary tree class that includes methods to construct a
// tree of ints, to print the structure, and to print the data
// using a preorder, inorder, or postorder traversal. The trees
// built have nodes numbered starting with 1 and numbered
// sequentially level by level with no gaps in the tree. The
// documentation refers to these as "sequential trees."
// BJP 3rd Edition pg 1020-1023

import io.github.liambloom.tests.Tester;

public class IntTree implements Cloneable {
    public static final IntTree ref1 = new IntTree(new IntTreeNode(3, new IntTreeNode(5, new IntTreeNode(1), null),
            new IntTreeNode(2, new IntTreeNode(4), new IntTreeNode(6))));
    public static final IntTree ref2 = new IntTree(
            new IntTreeNode(2, new IntTreeNode(8, new IntTreeNode(0), null), new IntTreeNode(1,
                    new IntTreeNode(7, new IntTreeNode(4), null), new IntTreeNode(6, null, new IntTreeNode(9)))));
    public static final IntTree ref3 = new IntTree(
            new IntTreeNode(2, new IntTreeNode(3, new IntTreeNode(8), new IntTreeNode(7)), new IntTreeNode(1)));

    public static void main(String[] args) {
        Tester tester = new Tester(Tester.Policy.RunLast);
        try {
            tester
                .test(ref1::countLeftNodes, 3)
                .test(ref1::countEmpty, 7)
                .test(ref1::depthSum, 50)
                .test(ref2::countEvenBranches, 3)
                .testOutput(() -> ref2.printLevel(3), "0\n7\n6\n")
                .testOutput(ref2::printLeaves, "leaves: 9 4 0\n")
                .test(() -> !ref1.isFull() && !ref2.isFull() && ref3.isFull(), true)
                .test(ref2::toString, "(2, (8, 0, empty), (1, (7, 4, empty), (6, empty, 9)))")
                .test(() -> ref1.equals(ref1) && !ref1.equals(ref2), true)
                .testThis(ref1.clone(), IntTree.class.getMethod("doublePositives"), new Object[0], new IntTree(new IntTreeNode(6,
                        new IntTreeNode(10, new IntTreeNode(2), null), new IntTreeNode(4, new IntTreeNode(8),
                        new IntTreeNode(12)))))
                .testThis(ref1.clone(), IntTree.class.getMethod("numberNodes"), new Object[0], new IntTree(new IntTreeNode(1,
                        new IntTreeNode(2, new IntTreeNode(3), null), new IntTreeNode(4, new IntTreeNode(5),
                        new IntTreeNode(6)))))
                .testThis(ref1.clone(), IntTree.class.getMethod("removeLeaves"), new Object[0], new IntTree(new IntTreeNode(3,
                        new IntTreeNode(5), new IntTreeNode(2))))
                .test(ref1::copy, ref1)
                .testThis(ref2.clone(), IntTree.class.getMethod("completeToLevel", int.class), new Object[]{4}, new IntTree(
                        new IntTreeNode(2, new IntTreeNode(8, new IntTreeNode(0), new IntTreeNode(-1)), new IntTreeNode(1,
                        new IntTreeNode(7, new IntTreeNode(4), new IntTreeNode(-1)), new IntTreeNode(6, new IntTreeNode(-1),
                        new IntTreeNode(9))))));
        }
        catch (NoSuchMethodException | IllegalAccessException ignored) {}

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

    // Exercise 6
    public void printLeaves() {
        if (overallRoot == null)
            System.out.println("no leaves");
        else {
            System.out.print("leaves:");
            printLeaves(overallRoot);
            System.out.println();
        }
    }

    private void printLeaves(IntTreeNode root) {
        if (root != null) {
            if (root.left == null && root.right == null)
                System.out.print(" " + root.data);
            else {
                printLeaves(root.right);
                printLeaves(root.left);
            }
        }

    }

    // Exercise 7
    public boolean isFull() {
        return isFull(overallRoot);
    }

    private boolean isFull(IntTreeNode root) {
        return root == null || root.left == null && root.right == null || root.left != null && isFull(root.left) &&  root.right != null && isFull(root.right);
    }

    // Exercise 8
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        toString(builder, overallRoot);
        return builder.toString();
    }

    private void toString(StringBuilder builder, IntTreeNode root) {
        if (root == null)
            builder.append("empty");
        else {
            boolean isLeaf = root.left == null && root.right == null;
            if (!isLeaf)
                builder.append('(');
            builder.append(root.data);
            if (!isLeaf) {
                builder.append(", ");
                toString(builder, root.left);
                builder.append(", ");
                toString(builder, root.right);
                builder.append(')');
            }
        }
    }

    // Exercise 9
    @Override
    public boolean equals(Object o) {
        return o instanceof IntTree && equals(overallRoot, ((IntTree) o).overallRoot);
    }

    private boolean equals(final IntTreeNode self, final IntTreeNode other) {
        return self == null ? other == null : other != null && self.data == other.data && equals(self.left, other.left) && equals(self.right, other.right);
    }

    // Exercise 10
    public void doublePositives() {
        doublePositives(overallRoot);
    }

    private void doublePositives(IntTreeNode root) {
        if (root != null) {
            if (root.data > 0)
                root.data *= 2;
            doublePositives(root.left);
            doublePositives(root.right);
        }
    }

    // Exercise 11
    public void numberNodes() {
        numberNode(overallRoot, new Counter(1));
    }

    private void numberNode(IntTreeNode node, Counter count) {
        if (node != null) {
            node.data = count.get();
            count.increment();
            numberNode(node.left, count);
            numberNode(node.right, count);
        }
    }

    // Exercise 12
    public void removeLeaves() {
        if (overallRoot != null) {
            if (isLeaf(overallRoot))
                overallRoot = null;
            else
                removeLeaves(overallRoot);
        }
    }

    private void removeLeaves(IntTreeNode root) {
        if (root.left != null) {
            if (isLeaf(root.left))
                root.left = null;
            else
                removeLeaves(root.left);
        }
        if (root.right != null) {
            if (isLeaf(root.right))
                root.right = null;
            else
                removeLeaves(root.right);
        }
    }

    // Exercise 13
    public IntTree copy() {
        // I had already done this by the time I got to exercise 12
        return clone();
    }

    // Exercise 14
    public void completeToLevel(int level) {
        if (level < 1)
            throw new IllegalArgumentException();
        completeToLevel(overallRoot, level);
    }

    private void completeToLevel(IntTreeNode root, int level) {
        if (level > 1 && root.left == null ^ root.right == null) {
            if (root.left == null)
                root.left = new IntTreeNode(-1);
            else
                completeToLevel(root.left, level - 1);
            if (root.right == null)
                root.right = new IntTreeNode(-1);
            else
                completeToLevel(root.right, level - 1);
        }
    }

    // Exercise 17 (untested)
    public IntTree combineWith(final IntTree t1, final IntTree t2) {
        return new IntTree(combineWith(t1.overallRoot, t2.overallRoot));
    }

    private IntTreeNode combineWith(final IntTreeNode t1, final IntTreeNode t2) {
        return t1 == null && t2 == null ? null 
            : new IntTreeNode(t1 != null ? t2 != null ? 3 : 1 : 2, 
                combineWith(t1 == null ? null : t1.left, t2 == null ? null : t2.left), 
                combineWith(t1 == null ? null : t1.right, t2 == null ? null : t2.right));
    }

    private boolean isLeaf(IntTreeNode node) {
        return node.left == null && node.right == null;
    }

    @Override
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public IntTree clone() {
        return new IntTree(clone(overallRoot));
    }

    private IntTreeNode clone(IntTreeNode og) {
        if (og == null)
            return null;
        else
            return new IntTreeNode(og.data, clone(og.left), clone(og.right));
    }
}

class Counter {
    int i;

    public Counter(int i) {
        this.i = i;
    }

    public void increment() {
        i++;
    }

    public int get() {
        return i;
    }
}