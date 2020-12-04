package io.github.liambloom.softwareEngineering.chapter16.rotate;

import io.github.liambloom.softwareEngineering.chapter4.Ask;

public class CircularLL extends AbstractCircularLL<CircularLL.Node> {
    public static void main(final String[] args) {
        AbstractCircularLL.main(new CircularLL(Ask.forInt("How long is the list", 0, Integer.MAX_VALUE)));
    }

    public CircularLL(final int length) {
        super(length);
    }

    protected class Node extends AbstractCircularLL<CircularLL.Node>.Node {
        public Node(Integer data) {
            super(data);
        }
    }

    protected void add(Integer data) {
        final Node newNode = new Node(data);
        if (head == null)
            head = newNode;
        else
            tail.next = newNode;
        tail = newNode;
    }
}
