package io.github.liambloom.softwareEngineering.chapter16;

import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Comparator;

public class LinkedList<E> extends AbstractLinkedList<E, LinkedList<E>.Node> {
    class Node extends AbstractLinkedList<E, LinkedList<E>.Node>.Node {
        public Node(final E data) {
            super(data);
        }/*

        public Node(final Node node) {
            super(node.data);
        }*/
    }

    class LinkedListIterator extends AbstractLinkedList<E, LinkedList<E>.Node>.AbstractListIterator {
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

        public void add(E e) {
            modOk = false;
            index++;
            size++;
            Node newNode = new Node(e);
            if (next == null)
                tail = newNode;
            if (prev == null) {
                next = newNode.next = head;
                prev = head = newNode;
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
                size--;
                modOk = false;
                if (lastReturned == head)
                    next = head = head.next;
                else 
                    next = prev.next = next.next;
                if (lastReturned == tail)
                    tail = prev;
                assert prev == null ? next == head : prev.next == next;
            }
            else {
                previous();
                remove();
            }
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

    @Override
    public int lastIndexOf(final Object o) {
        int index = -1;
        Iterator<E> iter = iterator();
        for (int i = 0; iter.hasNext(); i++) {
            if (o.equals(iter.next()))
                index = i;
        }
        return index;
    }

    // This method took me like 10 minutes
    public void reverse() {
        if (isEmpty())
            return;
        Node current = tail = head;
        while (tail.next != null) {
            final Node temp = tail.next;
            tail.next = temp.next;
            temp.next = current;
            current = temp;
        }
        head = current;
    }

    @Override
    public void addSorted(final E data, final Comparator<E> comparator) {
        size++;
        Node newNode = new Node(data);
        Node prev = null;
        Node next = head;
        while (comparator.compare(next.data, data) < 0) {
            prev = next;
            next = next.next;
        }
        newNode.next = next;
        if (prev == null)
            head = newNode;
        else
            prev.next = newNode;
    }

    //@Override
    @SuppressWarnings("unused")
    private void removeNode(LinkedList<E>.Node node) {
        if (head == null)
            return;
        final E n = node.data;
        if (head.data == n)
            head = head.next;
        Node prev = head;
        Node current = head.next;
        while (current != null) {
            if (current == n)
                prev.next = current.next;
            else {
                prev = current;
                current = current.next;
            }
        }
    }
}
