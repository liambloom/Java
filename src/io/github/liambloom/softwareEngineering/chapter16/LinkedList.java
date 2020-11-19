package io.github.liambloom.softwareEngineering.chapter16;

import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Comparator;

public class LinkedList<E> extends AbstractLinkedList<E, LinkedList<E>.Node> {
    public static void main(String[] args) throws Throwable {
        ///*for (int i = 0; i < 100; i++)
            //new ListTests(new io.github.liambloom.softwareEngineering.chapter16.LinkedList<>()).runTests();

        var list = new LinkedList<>();
        list.addAll(java.util.Arrays.asList(1, 2, 3, 4, 3, 3, 5, 6, 3));
        list.addSorted(3);
        System.out.println(list);
    }

    class Node extends AbstractLinkedList<E, LinkedList<E>.Node>.Node {
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

        public void set(E e) {
            if (!modOk)
                throw new IllegalStateException();
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
}
