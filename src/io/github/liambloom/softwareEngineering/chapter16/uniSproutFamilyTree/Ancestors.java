package io.github.liambloom.softwareEngineering.chapter16.uniSproutFamilyTree;

import java.util.NoSuchElementException;

public class Ancestors {
    public final Node grandparent;
    public final Node parent;
    public final Node self;

    public Ancestors(final String name, final Node root) {
        if (root == null)
            throw new IllegalArgumentException("Root cannot be null");
        final Ancestors ancestors = getAncestors(name, root, null, null);
        if (ancestors == null)
            throw new NoSuchElementException("No element named " + name);
        self = ancestors.self;

        if (ancestors.parent == null) {
            parent = new Node("None", self.getGenerationLevel() - 1, null);
            parent.setChildren(self);
        }
        else
            parent = ancestors.parent;

        if (ancestors.grandparent == null) {
            grandparent = new Node("None", parent.getGenerationLevel() - 2, null);
            grandparent.setChildren(parent);
        }
        else
            grandparent = ancestors.grandparent;
    }

    private Ancestors(final Node grandparent, final Node parent, final Node self) {
        this.grandparent = grandparent;
        this.parent = parent;
        this.self = self;
    }

    private static Ancestors getAncestors(final String name, final Node root, final Node grandparent, final Node parent) {
        if (root.getName().equals(name))
            return new Ancestors(grandparent, parent, root);
        for (Node child = root.getChildren(); child != null; child = child.getNext()) {
            final Ancestors sub = getAncestors(name, child, parent, root);
            if (sub != null)
                return sub;
        }
        return null;
    }
}
