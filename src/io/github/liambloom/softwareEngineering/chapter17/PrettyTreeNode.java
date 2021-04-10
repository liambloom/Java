package io.github.liambloom.softwareEngineering.chapter17;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public abstract class PrettyTreeNode {
    /**
     * A method that returns an iterator over all children of this node.
     *
     * @return An iterator over all child nodes
     */
    protected abstract Iterator<PrettyTreeNode> childIterator();

    /**
     * A method that returns a string representation of the value of this
     * node.
     *
     * @return This node's value as a string
     */
    protected abstract String valueString();

    /**
     *
     * <pre>
     *
     * </pre>
     *
     * @return A string representation of this node and it's children
     */
    public final String prettyString() {
        StringBuilder builder = new StringBuilder();
        for (String layer : toStringLayers())
            builder.append(layer);
        return builder.toString();
    }

    private String[] toStringLayers() {
        final List<String[]> subtrees = new LinkedList<>();
        int rowCount = 0;
        int colCount = 0;
        Iterator<PrettyTreeNode> childIter = childIterator();
        while (childIter.hasNext()) {
            final String[] subtree = childIter.next().toStringLayers();
            rowCount = Math.max(subtree.length, rowCount);
            colCount += subtree[0].length();
            subtrees.add(subtree);
        }
        if (rowCount == 0)
            rowCount++;
        else
            rowCount += 2;
        final String[] rowStrings = new String[rowCount];
        final String name = valueString();
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

            char centerChar = switch (branchChars[center]) {
                case '\u2500' -> '\u2534';
                case '\u252c' -> '\u253c';
                case ' ' -> '\u2502';
                default -> throw new IllegalStateException("Illegal central character '" + branchChars[center] + "' in \"" + new String(branchChars) + '"');
            };
            branchChars[center] = centerChar;

            rowStrings[1] = new String(branchChars);

            for (i = 0; i < builders.length; i++)
                rowStrings[i + 2] = builders[i].toString();
        }

        return rowStrings;
    }
}
