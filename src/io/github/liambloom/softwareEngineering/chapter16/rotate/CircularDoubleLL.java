package io.github.liambloom.softwareEngineering.chapter16.rotate;

import io.github.liambloom.softwareEngineering.chapter4.Ask;

public class CircularDoubleLL extends AbstractCircularLL<CircularDoubleLL.Node> {
    public static void main(final String[] args) {
        AbstractCircularLL.main(new CircularDoubleLL(Ask.forInt("How long is the list", 0, Integer.MAX_VALUE)));
    }

    public CircularDoubleLL(final int length) {
        super(length);
    }

    protected class Node extends AbstractCircularLL<CircularDoubleLL.Node>.Node {
        Node prev;

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
        newNode.prev = tail;
        tail = newNode;
    }

    @Override
    protected void close() {
        if (size != 0)
            head.prev = tail;
        super.close();
    }
}
