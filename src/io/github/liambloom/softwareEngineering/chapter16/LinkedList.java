package io.github.liambloom.softwareEngineering.chapter16;

import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Comparator;

public class LinkedList<E> extends AbstractLinkedList<E, LinkedList<E>.Node> {
    class Node extends AbstractLinkedList<E, LinkedList<E>.Node>.Node {
        public Node(final E data) {
            super(data);
        }
    }

    class LinkedListIterator extends AbstractLinkedList<E, LinkedList<E>.Node>.AbstractListIterator {
        Node prev2 = null;

        @Override
        public E next() {
            prev2 = prev;
            return super.next();
        }

        public E previous() {
            index--;
            if (prev == null)
                throw new NoSuchElementException();
            else {
                next = prev;
                if (prev2 != null)
                    prev = prev2;
                else
                    prev = getNode(index - 1);
                prev2 = null;
                modOk = true;
                lastReturned = next;
                return next.data;
            }
        }

        public void add(E e) {
            modOk = false;
            index++;
            size++;
            prev2 = prev;
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
            assert prev2 == null;
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
