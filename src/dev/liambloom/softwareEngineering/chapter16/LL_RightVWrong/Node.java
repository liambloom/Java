package dev.liambloom.softwareEngineering.chapter16.LL_RightVWrong;

/**
 * Node
 * 
 * Mr. Marques 3/2/05
 */

public class Node {
    private int value;
    private Node next;

    /*** Constructor for objects of class Node */
    public Node() {
        setValue(0);
        next = null;
    }

    public Node(int k) {
        setValue(k);
        next = null;
    }

    public int getData() {
        return value;
    }

    public Node getNext() {
        return next;
    }

    public void setValue(int k) {
        value = k;
    }

    public void setNext(Node n) {
        next = n;
    }

} // Node
