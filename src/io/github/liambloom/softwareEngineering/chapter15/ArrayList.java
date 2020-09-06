package io.github.liambloom.softwareEngineering.chapter15;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Arrays;
import java.util.Iterator;

/**
 * This is a clone of {@code java.util.ArrayList<E>}.
 * 
 * @author Liam Bloom 
 */

@SuppressWarnings("unchecked")
public class ArrayList<E> implements Iterable<E> {

    /**
     * Used for testing purposes only
     * 
     * @param args args
     */
    public static void main(String[] args) {
        ArrayList<Integer> x = new ArrayList<>();
        x.add(1);
        x.add(2);
        x.add(3);
        x.add(1, 4);
        //x.remove(4);
        System.out.println(x);
    }

    /**
     * The iterator type for {@code ArrayList<E>};
     */
    private final class ArrayListIterator implements Iterator<E> {
        private int position = 0;
        private boolean removeOk = false;

        // The iterator docs already explain what each of these do
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            removeOk = true;
            return elementData[position++];
        }

        public boolean hasNext() {
            return position < size;
        }

        public void remove() {
            if (!removeOk) throw new IllegalStateException();
            ArrayList.this.remove(--position);
            removeOk = false;
        }
    }

    /**
     * The default capacity of an {@code ArrayList}
     */
    public static final int DEFAULT_CAPACITY = 100;

    /**
     * Stores the elements
     */
    private E[] elementData;

    /**
     * Stores the current size of the list
     */
    private int size = 0;

    /**
     * Creates a new {@code ArrayList} with the capacity {@value ArrayList#DEFAULT_CAPACITY}
     */
    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Creates a new {@code ArrayList} with the given capacity
     * 
     * @param capacity The capacity of the new list. Must be &gt;= 0.
     * @throws IllegalArgumentException if capacity is less than 0.
     */
    public ArrayList(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("Capacity must be greater than 0, received " + capacity);
        /* I could avoid the unchecked cast, by not initializing elementData until
            the first value was added to it, and then generating the array using
            java.lang.reflect.Array and <value>.class.arrayType(), but this would
            be difficult and unnecessary, since elementData is never exposed to the
            client anyway*/
        elementData = (E[]) new Object[capacity];
    }
    
    /**
     * Creates a new {@code ArrayList} from an already exiting one
     * 
     * @param list A pre-existing {@code ArrayList}
     */
    public ArrayList(ArrayList<E> list) {
        this(list.toArray());
    }

    /**
     * Creates a new {@code ArrayList} from a {@code List}
     * 
     * @param list The {@code List} that the {@code ArrayList} should be created from
     */
    public ArrayList(List<E> list) {
        this((E[]) list.toArray());
    }

    /**
     * Creates a new {@code ArrayList} from an array
     * 
     * @param list An array of type E
     */
    public ArrayList(E[] list) {
        this(list.length);
        elementData = list;
        size = list.length;
    }

    /**
     * Returns the number of elements in the list
     * 
     * @return the size of the list
     */
    public int size() {
        return size;
    }

    /**
     * Returns an iterator for the list
     * 
     * @return an Iterator over the list
     */
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    /**
     * Adds a value to the end of the list
     * 
     * @param value the value to be added
     */
    public void add(E value) {
        addAll(size, value);
    }

    /**
     * Add a value to the list
     * 
     * @param index the index at which the value should be added
     * @param value the value to be added
     * @throws IndexOutOfBoundsException if index is not between {@code 0} and {@link #size()} (inclusive)
     */
    public void add(int index, E value) {
        addAll(index, value);
    }

    /**
     * Adds every element from another {@code ArrayList} to this end of the list
     * 
     * @param o The {@code ArrayList} of elements to add
     */
    public void addAll(ArrayList<E> o) {
        addAll(size, o.elementData);
    }

    /**
     * Adds every element from another {@code ArrayList} to this list at a specific index
     * 
     * @param index The index at which to add the elements
     * @param o     The {@code ArrayList} of elements to add
     */
    public void addAll(int index, ArrayList<E> o) {
        addAll(index, o.elementData);
    }

    /**
     * Adds every element from a {@code List} to the end of this list
     * 
     * @param o The {@code List} of elements to add
     */
    public void addAll(List<E> o) {
        addAll(size, (E[]) o.toArray());
    }

    /**
     * Adds every element from a {@code List} to this list at a specific index
     * 
     * @param index The index at which to add the elements
     * @param o The {@code List} of elements to add
     */
    public void addAll(int index, List<E> o) {
        addAll(index, (E[]) o.toArray());
    }

    /**
     * Add value(s) to the list
     * 
     * @param index the index at which the value should be added
     * @param values the value(s) to be added
     * @throws IndexOutOfBoundsException if index is not between {@code 0} and {@link #size()} (inclusive)
     */
    // There is no multiAdd without index because then there would be ambiguity as to which method you wanted to call
    public void addAll(int index, E... values) {
        indexBoundChecker(index, true);
        int prevSize = size;
        grow(values.length);
        System.arraycopy(elementData, index, elementData, index + values.length, prevSize - index);
        System.arraycopy(values, 0, elementData, index, values.length);
    }

    /**
     * Removes the value at the given index
     * 
     * @param index the index of the value to be removed
     * @throws IndexOutOfBoundsException if index is not between {@code 0} and {@link #size()} (exclusive)
     */
    public void remove(int index) {
        indexBoundChecker(index, false);
        System.arraycopy(elementData, index + 1, elementData, index, size - index);
        elementData[--size] = null;
    }
    /**
     * Gets a value from the ArrayList
     * 
     * @param index the index to get the value
     * @return the value at the index
     * @throws IndexOutOfBoundsException if index is not between {@code 0} and {@link #size()} (exclusive)
     */
    public E get(int index) {
        indexBoundChecker(index, false);
        return elementData[index];
    }

    /**
     * Sets the value at a particular index
     * 
     * @param index The index at which the value should be set
     * @param value The value
     * @throws IndexOutOfBoundsException if index is not between {@code 0} and {@link #size()} (exclusive)
     */
    public void set(int index, E value) {
        indexBoundChecker(index, false);
        elementData[index] = value;
    }

    /**
     * Replaces an element in the list with another element
     * 
     * @param oldValue The value to be replaced
     * @param newValue The value to replace it with
     */
    public void replaceAll(E oldValue, E newValue) {
        int i;
        while ((i = indexOf(oldValue)) != -1) set(i, newValue);
    }

    /**
     * Returns the index of the given value
     * 
     * @param value the value that will be searched for
     * @return The index of the given value. {@code -1} if the value is not found
     */
    public int indexOf(E value) {
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(value)) return i;
        }
        return -1;
    }

    /**
     * Returns the smallest value in the list
     * 
     * @return the smallest value in the list
     * @throws IllegalStateException if the list is empty
     * @throws ClassCastException if {@code E} does not implement {@code Comparable<E>}
     */
    public E min() {
        if (size == 0) throw new IllegalStateException("There is not minimum value to an empty list");
        E min = elementData[0];
        if (size == 1) ((Comparable<E>) min).compareTo(min); // This checks to make sure E implements Comparable<E> even if size == 1
        for (int i = 1; i < size; i++) {
            E e = elementData[i];
            if (((Comparable<E>) min).compareTo(e) > 0) min = e;
        }
        return min;
    }

    /**
     * Returns the largest value in the list
     * 
     * @return the largest value in the list
     * @throws IllegalStateException if the list is empty
     */
    public E max() {
        if (size == 0) throw new IllegalStateException("There is not minimum value to an empty list");
        E max = elementData[0];
        if (size == 1) ((Comparable<E>) max).compareTo(max); // This checks to make sure E implements Comparable<E> even if size == 1
        for (int i = 1; i < size; i++) {
            E e = elementData[i];
            if (((Comparable<E>) max).compareTo(e) < 0) max = e;
        }
        return max;
    }

    /**
     * Checks if the list contains a value
     * 
     * @param value The value to check for.
     * @return {@code true} if the value is in the list, {@code false} if it's not.
     */
    public boolean contains(E value) {
        return indexOf(value) >= 0;
    }

    /**
     * Checks if the list is empty.
     * 
     * @return {@code true} if the list is empty, {@code false} if it's not.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Sets the list size to {@code 0}
     */
    public void clear() {
        size = 0;
    }

    /**
     * Returns the list as an array
     * 
     * @return The list, but in the form of an array
     */
    public E[] toArray() {
        return Arrays.copyOfRange(elementData, 0, size);
    }

    /**
     * Throws an error if the index is out of bounds
     * 
     * @param index The index to check 
     * @param inclusive If false, it will throw an error if the index is equal to size
     * @throws IndexOutOfBoundsException if the index is not valid for the list's size
     */
    private void indexBoundChecker(int index, boolean inclusive) {
        if (index >= size + (inclusive ? 1 : 0) || index < 0) {
            throw new IndexOutOfBoundsException(index);
        }
    }

    /**
     * Grows the list, increasing the capacity if needed.
     * 
     * @param d How much to grow the list by
     * @throws IllegalArgumentException if d is less than {@code 0}
     */
    private void grow(int d) {
        if (d < 0) throw new IllegalArgumentException("Cannot shrink " + getClass().getSimpleName() + " capacity");
        else ensureCapacity(size += d);
    }

    /**
     * Ensures that the capacity is at least c. More precisely,
     * it sets the capacity to the larger of the c and
     * {@code 1.5 * <the current capacity>}.
     * 
     * @param capacity The capacity it must be
     */
    public void ensureCapacity(int capacity) {
        if (capacity > elementData.length) elementData = Arrays.copyOf(elementData, (int) Math.max(capacity, 1.5 * elementData.length));
    }

    /**
     * Returns a {@code String} representing the list.
     * 
     * @return a {@code String} representing the list.
     */
    public String toString() {
        String r = "";
        if (size > 0) {
            r += elementData[0];
            for (int i = 1; i < size; i++) {
                r += ", " + elementData[i];
            }
        }
        return "[" + r + "]";
    }
}
