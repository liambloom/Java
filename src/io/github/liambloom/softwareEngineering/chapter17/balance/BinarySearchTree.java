package io.github.liambloom.softwareEngineering.chapter17.balance;

import java.util.ArrayList;

public class BinarySearchTree<E extends Comparable<E>> {
    private TreeNode<E> root = null;

    public static void main(final String[] args) {
        final BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.addAll(50,40,60,30,70,105,20,80,35,5,10,90,100,110,65);
        System.out.println("Unbalanced");
        System.out.println(tree);
        tree.balance();
        System.out.println("Balanced");
        System.out.println(tree);
    }

    public void add(final E e) {
        root = add(root, e);
    }

    private TreeNode<E> add(final TreeNode<E> node, final E e) {
        if (node == null)
            return new TreeNode<>(e);
        else if (node.data.compareTo(e) <= 0)
            node.right = add(node.right, e);
        else
            node.left = add(node.left, e);
        return node;
    }

    @SafeVarargs
    public final void addAll(final E... es) {
        for (final E e : es)
            add(e);
    }

    public void balance() {
        final ArrayList<TreeNode<E>> nodes = new ArrayList<>();
        intoList(nodes, root);
        /*System.out.println(nodes.toString());
        System.out.println(nodes.size());*/
        root = addBalanced(nodes, 0, nodes.size());
    }

    private void intoList(final ArrayList<TreeNode<E>> list, final TreeNode<E> node) {
        if (node != null) {
            intoList(list, node.left);
            list.add(node);
            intoList(list, node.right);
        }
    }

    private TreeNode<E> addBalanced(final ArrayList<TreeNode<E>> list, final int low, final int high) {
        if (low >= high)
            return null;
        final int middle = (high + low) / 2;
        final TreeNode<E> root = list.get(middle);
        root.left = addBalanced(list, low, middle);
        root.right = addBalanced(list, middle + 1, high);
        return root;
    }

    /*@Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        toString(builder, root);
        return builder.toString();
    }

    private void toString(StringBuilder builder, TreeNode<E> root) {
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
    }*/

    @Override
    public String toString() {
        if (root == null)
            return "[Empty Tree]";
        final StringBuilder builder = new StringBuilder();
        for (final String row : toString(root))
            builder.append(row).append(System.lineSeparator());
        return builder.toString();
    }

    private String[] toString(TreeNode<E> node) {
        if (node == null)
            return null;

        String root = node.toString();
        final String[] left = toString(node.left);
        final String[] right = toString(node.right);

        if (left == null && right == null)
            return new String[]{ node.toString() };
        else if (left == null || right == null) {
            final String[] single = left == null ? right : left;
            assert sameLength(single): "Center strings were different lengths";

            final int width = Math.max(single[0].length(), root.length());
            final String[] r = new String[single.length + 2];
            r[0] = center(root, width); // Maybe center(center(root, single[0].length()), width);
            r[1] = center(left == null ? "\u2572" : "\u2571", width);
            for (int i = 0; i < single.length; i++)
                r[i + 2] = center(single[i], width);
            for (String row : r)
                System.out.println(row);
            return r;
        }
        else {
            assert sameLength(left): "Left strings were different lengths";
            assert sameLength(right): "Right strings were different lengths";
            final int subWidth = left[0].length() + right[0].length() + 1;
            final int width = Math.max(subWidth, root.length());
            final String[] r = new String[Math.max(left.length, right.length) + 2];
            r[0] = center(root, width);
            assert r[0].length() == width: "Incorrectly centered";
            final String leftPad = " ".repeat((width - subWidth) / 2);
            final String rightPad = " ".repeat((width - subWidth + 1) / 2);
            if ((width - 1) / 2 - leftPad.length() - 1 < 0) {
                System.out.printf("leftPad: %d, width: %d, subWidth: %d%n", leftPad.length(), width, subWidth);
            }
            final StringBuilder r1 = new StringBuilder()
                    .append(leftPad)
                    .append(" ".repeat(Math.max((left[0].length() - 1) / 2, 0)))
                    .append('\u250c')
                    .append("\u2500".repeat(Math.max(left[0].length() / 2, 0)))
                    .append('\u2500')
                    .append("\u2500".repeat(Math.max((right[0].length() - 1) / 2, 0)))
                    .append('\u2510')
                    .append(" ".repeat(Math.max(right[0].length() / 2, 0)))
                    .append(rightPad);
            r1.setCharAt((width - 1) / 2, '\u2534');
            r[1] = r1.toString();
            //assert r[1].charAt(width / 2) == '\u2534';
            final String spaces = " ".repeat(left.length < right.length ? left[0].length() : right[0].length());
            int i;
            for (i = 0; i < r.length - 2; i++)
                r[i + 2] = leftPad + (i >= left.length ? spaces : left[i]) + " " + (i >= right.length ? spaces : right[i]) + rightPad;
            for (String row : r)
                System.out.println(row);
            return r;
        }
    }

    private static String center(String s, int width) {
        return width > s.length() ? " ".repeat((width - s.length()) / 2) + s + " ".repeat((width - s.length() + 1) / 2) : s;
    }

    private static boolean sameLength(String[] a) {
        if (a.length < 2)
            return true;
        final int l = a[0].length();
        for (String s : a) {
            if (s.length() != l)
                return false;
        }
        return true;
    }
}
