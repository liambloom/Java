package io.github.liambloom.softwareEngineering.chapter18;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.lang.reflect.Array;

import io.github.liambloom.tests.book.bjp3.*;

public class HashSet<E> implements Iterable<E> {
    private static final double MAX_LOAD_FACTOR = 0.75;
    private HashEntry[] elementData;
    private int size;

    public static void main(String[] args) {
        HashSet<Integer> set = new HashSet<>();
        set.addAll(1, 3, 5, 23, 71, 9, 1);
        HashSet<Integer> set2 = new HashSet<>();
        set2.addAll(23, 7, 1, 9, 5);
        //set.remove(5);
        //set.remove(1);
        set.retainAll(set2);
        System.out.println(set);
    }

    private class HashSetIterator implements Iterator<E> {
        private int i = 0;
        private int j = i;
        private HashEntry prev = null;
        private HashEntry current = null;
        private HashEntry next = null;

        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();
            generateNext();
            if (i != j){
                prev = null;
                i = j;
            }
            else if (current != null)
                prev = current;
            current = next;
            next = null;
            return current.data;
        }

        public boolean hasNext() {
            generateNext();
            return next != null;
        }

        private void generateNext() {
            if (next != null)
                return;
            else if (current != null && current.next.next != null)
                next = current.next;
            else {
                for (j = i; j < 9 && next == null;) {
                    if (elementData[++j].next != null)
                        next = elementData[j];
                }
            }
        }

        public void remove() {
            if (current == null)
                throw new IllegalStateException();
            else if (prev == null)
                elementData[i] = elementData[i].next;
            else
                prev.next = current.next;
            current = null;
            size--;
        }
    }

    @SuppressWarnings("unchecked")
    public HashSet() {
        elementData = (HashEntry[]) Array.newInstance(HashEntry.class, 10);
        for (int i = 0; i < elementData.length; i++)
            elementData[i] = new HashEntry();
        size = 0;
    }

    // Hash Function
    private int hashFunction(final E value) {
        return Math.abs(value.hashCode()) % elementData.length;
    }

    // Returns true if the given value is found in this set.     
    public boolean contains(final E value) {
        final int bucket = hashFunction(value);
        HashEntry current = elementData[bucket];

        while (current != null) {
            if (Objects.equals(current.data, value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Exercise(2)
    public boolean containsAll(final HashSet<E> set) {
        for (E entry : set) {
            if (!contains(entry))
                return false;
        }
        return true;
    }

    // Give the loadFactor
    private double loadFactor() {
        return (double) size / elementData.length;
    }

    // Adds the given element to this set, if it was not 
    // already contained in the set. 
    public void add(final E value) {
        if (!contains(value)) {
            if (loadFactor() >= MAX_LOAD_FACTOR) {
                rehash();
            }

            // insert new value at front of list     
            final int bucket = hashFunction(value);
            elementData[bucket] = new HashEntry(value, elementData[bucket]);
            size++;

        }
    }

    @SafeVarargs
    public final void addAll(final E... values) {
        for (E value : values)
            add(value);
    }

    @Exercise(1)
    public void addAll(final HashSet<E> o) {
        for (E value : o)
            add(value);
    }

    // Resize the entire array and rehash it
    @SuppressWarnings("unchecked")
    private void rehash() {
        // replace element data array with a larger empty version 
        final HashEntry[] oldElementData = elementData;

        elementData = (HashEntry[]) Array.newInstance(HashEntry.class, 2 * oldElementData.length);
        size = 0;

        // re-add all of the old data into the new array 
        for (final HashEntry oldElementDatum : oldElementData) {
            HashEntry current = oldElementDatum;


            while (current != null) {
                add(current.data);
                current = current.next;

            }
        }
    }


    // Removes the given value if it is contained in the set. 
    public void remove(final E value) {
        final int bucket = hashFunction(value);
        if (elementData[bucket] != null) {

            // check front of list 

            if (Objects.equals(elementData[bucket].data, value)) {
                elementData[bucket] = elementData[bucket].next;
                size--;
            } else {
                // check rest of list 
                HashEntry current = elementData[bucket];
                while (current.next != null && !Objects.equals(current.next.data, value)) {
                    current = current.next;
                }


                // if the element is found, remove it 
                if (current.next != null) {
                    current.next = current.next.next;
                    size--;
                }
            }
        }
    }

    @Exercise(4)
    public void removeAll(final HashSet<E> o) {
        for (final E e : o)
            remove(e);
    }

    @Exercise(5)
    public void retainAll(final HashSet<E> o) {
        final Iterator<E> iter = iterator();
        while (iter.hasNext()) {
            if (!o.contains(iter.next()))
                iter.remove();
        }
    }

    // ================== HashEntry ==================
    protected class HashEntry {
        private final E data;
        private HashEntry next;

        public HashEntry() {
            this(null, null);
        }

        public HashEntry(final E data) {
            this(data, null);
        }

        public HashEntry(final E data, final HashEntry next) {
            this.data = data;
            this.next = next;
        }

    }  // HashEntry

    @Override
    public Iterator<E> iterator() {
        return new HashSetIterator();
    }
    
    @Override
    @Exercise(3)
    public boolean equals(Object o) {
        if (o instanceof HashSet) {
            final HashSet<Object> other = (HashSet<Object>) o;
            if (this.size != other.size)
                return false;
            for (E e : this) {
                if (!other.contains(e))
                    return false;
            }
            return true;
        }
        else
            return false;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append('{');
        final Iterator<E> iter = iterator();
        if (iter.hasNext()) {
            builder.append(iter.next());
            while (iter.hasNext()) {
                builder.append(", ");
                builder.append(iter.next());
            }
        }
        builder.append('}');
        return builder.toString();
    }
} // HashIntSet


