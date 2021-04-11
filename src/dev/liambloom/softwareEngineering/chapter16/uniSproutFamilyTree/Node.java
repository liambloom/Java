package dev.liambloom.softwareEngineering.chapter16.uniSproutFamilyTree;

public class Node {
    private int generationLevel;
    private String name;
    private Node previous, next, children;

    public Node() {
        generationLevel = -1;
        name = "";
        previous = next = children = null;
    }

    public Node(String n, int gl, Node p) {
        name = n;
        generationLevel = gl;
        previous = p;
        children = null;
        next = null;
    }

    // ================== Accessor Methods ==================
    public int getGenerationLevel() {
        return generationLevel;
    }

    public String getName() {
        return name;
    }

    public Node getNext() {
        return next;
    }

    public Node getPrevious() {
        return previous;
    }

    public Node getChildren() {
        return children;
    }

    // ================== Mutator Methods ==================
    public void setChildren(Node child) {
        children = child;
    }

    public void setNext(Node n) {
        next = n;
    }

} // Node
