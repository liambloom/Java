package io.github.liambloom.softwareEngineering.chapter16;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DoubleLinkedList<E> extends AbstractLinkedList<E, DoubleLinkedList<E>.Node> {
    public static void main(String[] args) throws Throwable {
        var tests = new ListTests(new io.github.liambloom.softwareEngineering.chapter16.DoubleLinkedList<>());
        for (int i = 0; i < 100; i++)
            tests.runTests();
    }
    
    class Node extends AbstractLinkedList<E, DoubleLinkedList<E>.Node>.Node {
        Node prev = null;

        public Node(final E data) {
            super(data);
        }
    }

    class DoubleLinkedListIterator extends AbstractLinkedList<E, DoubleLinkedList<E>.Node>.AbstractListIterator {
        public E previous() {
            if (prev == null)
                throw new NoSuchElementException();
            else {
                index--;
                next = prev;
                prev = prev.prev;
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
            if (next == null) {
                tail = newNode;
            }
            if (prev == null) {
                next = newNode.next = head;
                prev = head = newNode;
            }
            else {
                newNode.next = next;
                newNode.prev = prev;
                prev.next = newNode;
                if (next != null)
                    next.prev = newNode;
                prev = newNode;
            }
        }

        public void remove() {
            if (!modOk)
                throw new IllegalStateException();
            if (lastReturned == next) {
                size--;
                modOk = false;
                if (lastReturned == head) {
                    next = head = head.next;
                    if (head != null)
                        head.prev = null;
                }
                else {
                    next = prev.next = next.next;
                    if (next != null)
                        next.prev = prev;
                }
                if (lastReturned == tail)
                    tail = prev;
            }
            else {
                previous();
                remove();
            }
        }
    }

    public ListIterator<E> listIterator(final int start) {
        DoubleLinkedListIterator iter = new DoubleLinkedListIterator();
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
}
