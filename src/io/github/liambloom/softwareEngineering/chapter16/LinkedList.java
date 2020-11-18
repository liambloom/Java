package io.github.liambloom.softwareEngineering.chapter16;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Iterator;
import java.nio.channels.IllegalSelectorException;
import java.util.ListIterator;

public class LinkedList<E> extends AbstractLinkedList<E, LinkedList<E>.Node> {
    Node head = null;
    Node tail = null;
    int size = 0;

    class Node extends AbstractLinkedList<E, LinkedList<E>.Node>.Node {
        E data;
        Node next = null;

        public Node(final E data) {
            super(data);
        }

        public Node(final Node node) {
            super(node.data);
        }
    }

    class LinkedListIterator implements ListIterator<E> {
        Node prev = null;
        Node next = head;
        Node lastReturned = null;
        int index = 0;
        boolean modOk = false;

        public E next() {
            if (next == null)
                throw new NoSuchElementException();
            else {
                index++;
                prev = next;
                next = next.next;
                modOk = true;
                lastReturned = prev;
                return prev.data;
            }
        }

        public boolean hasNext() {
            return next != null;
        }

        public int nextIndex() {
            return index;
        }

        public E previous() {
            if (prev == null)
                throw new NoSuchElementException();
            else {
                next = prev;
                prev = getNode(--index - 1);
                modOk = true;
                lastReturned = next;
                return next.data;
            }
        }

        public boolean hasPrevious() {
            return prev != null;
        }

        public int previousIndex() {
            return index - 1;
        }

        public void add(E e) {
            modOk = false;
            index++;
            Node newNode = new Node(e);
            if (prev == null) {
                newNode.next = head;
                head = newNode;
            }
            else {
                newNode.next = next;
                prev.next = newNode;
                prev = newNode;
            }
        }

        public void remove() {
            if (!modOk)
                throw new IllegalStateException();
            if (lastReturned == next) {
                modOk = false;
                prev.next = next.next;
            }
            else {
                previous();
                remove();
            }
        }

        public void set(E e) {
            if (!modOk)
                throw new IllegalSelectorException();
            lastReturned.data = e;
        }
    }

    public ListIterator<E> listIterator(int start) {
        LinkedListIterator iter = new LinkedListIterator();
        if (start < 0 || start > size)
            throw new IndexOutOfBoundsException();
        if (start == size) {
            iter.prev = tail;
            iter.next = null;
            iter.index = size;
        }
        else {
            for (int i = 0; i < start; i++)
                iter.next();
            iter.lastReturned = null;
            iter.modOk = false;
        }
        return iter;
    }

    public int lastIndexOf(final Object o) {
        int index = -1;
        Iterator<E> iter = iterator();
        for (int i = 0; iter.hasNext(); i++) {
            if (o.equals(iter.next()))
                index = i;
        }
        return index;
    }

    public List<E> subList(int start, final int end) {
        if (start < 0 || start > end || end < size)
            throw new IndexOutOfBoundsException();
        List<E> newList = new LinkedList<>();
        for (Node e = getNode(start); start < end; start++) {
            newList.add(e.data);
            e = e.next;
        }
        return newList;
    }
}
