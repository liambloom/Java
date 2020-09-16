package io.github.liambloom.softwareEngineering.chapter11;

import java.util.Iterator;
import java.util.Collection;

public class Test {
    public static void main(String[] args) {
        LinkedList<String> words = new LinkedList<>("A", "B", "C", "D", "E", "F" );
        Iterator<String> itr = words.iterator();
        while (itr.hasNext()) {
            String s = itr.next();
            if (s.equals("D"))
                itr.remove();
            else {
                System.out.print(s);
                if (itr.hasNext())
                    System.out.print(", ");
            }
        }
        /*for (String word : words) 
            System.out.print(word + ", ");*/
    }
}

class LinkedList<E> extends java.util.LinkedList<E> {
    private static final long serialVersionUID = 876323262645176354L;

    public LinkedList() {
        super();
    }
    public LinkedList(Collection<? extends E> c) {
        super(c);
    }
    public LinkedList(E... nodes) {
        super();
        for (E node : nodes)
            add(node);
    }
}