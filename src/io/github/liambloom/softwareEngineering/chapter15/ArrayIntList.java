package io.github.liambloom.softwareEngineering.chapter15;

import io.github.liambloom.softwareEngineering.chapter7.Parser;
import java.util.List;
import java.util.Arrays;

/**
 * This is a clone of {@code java.util.ArrayList<E>}. It currently only works with ints, but by
 * the end of chapter 15 it will use generics
 * 
 * This implementation isn't exactly like what we did in the chapter. The most notable difference
 * is that the implementation in the chapter throws an error if you try to exceed the list's
 * capacity, while my implementation will simply increase the capacity as needed (see {@link #grow(int)}).
 * 
 * @author Liam Bloom 
 * @version 1.1.0 09/05/2020
 */

public class ArrayIntList {

    /**
     * Used for testing purposes only
     * 
     * @param args args
     */
    public static void main(String[] args) {
        ArrayIntList x = new ArrayIntList();
        x.add(1);
        x.add(2);
        x.add(3);
        x.add(1, 4);
        //x.remove(4);
        System.out.println(x);
    }

    /**
     * The default capacity of an {@code ArrayIntList}
     */
    public static final int DEFAULT_CAPACITY = 100;

    /**
     * Stores the elements
     */
    private int[] elementData;

    /**
     * Stores the current size of the list
     */
    private int size = 0;

    /**
     * Creates a new {@code ArrayIntList} with the capacity {@value ArrayIntList#DEFAULT_CAPACITY}
     */
    public ArrayIntList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Creates a new {@code ArrayIntList} with the given capacity
     * 
     * @param capacity The capacity of the new list. Must be &gt;= 0.
     * @throws IllegalArgumentException if capacity is less than 0.
     */
    public ArrayIntList(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("Capacity must be greater than 0, received " + capacity);
        elementData = new int[capacity];
    }
    
    /**
     * Creates a new {@code ArrayIntList} from an already exiting one
     * 
     * @param list A pre-existing {@code ArrayIntList}
     */
    public ArrayIntList(ArrayIntList list) {
        this(list.toArray());
    }

    /**
     * Creates a new {@code ArrayIntList} from a {@code List}
     * 
     * @param list The {@code List} that the {@code ArrayIntList} should be created from
     */
    public ArrayIntList(List<Integer> list) {
        this(list.toArray(new Integer[list.size()]));
    }

    /**
     * Creates a new {@code ArrayIntList} from an array
     * 
     * @param list An array of integers
     */
    public ArrayIntList(int[] list) {
        this(list.length);
        elementData = list;
        size = list.length;
    }

    /**
     * Creates a new {@code ArrayIntList} from an array
     * 
     * @param list An array of integers
     */
    public ArrayIntList(Integer[] list) {
        this(Parser.toPrimitive(list));
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
     * Adds a value to the end of the list
     * 
     * @param value the value to be added
     */
    public void add(int value) {
        addAll(size, value);
    }

    /**
     * Add a value to the list
     * 
     * @param index the index at which the value should be added
     * @param value the value to be added
     * @throws IndexOutOfBoundsException if index is not between {@code 0} and {@link #size()} (inclusive)
     */
    public void add(int index, int value) {
        addAll(index, value);
    }

    /**
     * Adds every element from a {@code ArrayIntList} of integers to the end of the list
     * 
     * @param o The {@code ArrayIntList} of elements to add
     */
    public void addAll(ArrayIntList o) {
        addAll(size, o.elementData);
    }

    /**
     * Adds every element from an {@code ArrayIntList} of integers to the list at a specific index
     * 
     * @param index The index at which to add the elements
     * @param o     The {@code ArrayIntList} of elements to add
     */
    public void addAll(int index, ArrayIntList o) {
        addAll(index, o.elementData);
    }

    /**
     * Adds every element from a {@code List} of integers to the end of the list
     * 
     * @param o The {@code List} of elements to add
     */
    public void addAll(List<Integer> o) {
        addAll(size, Parser.toPrimitive(o.toArray(new Integer[o.size()])));
    }

    /**
     * Adds every element from a {@code List} of integers to the list at a specific index
     * 
     * @param index The index at which to add the elements
     * @param o The {@code List} of elements to add
     */
    public void addAll(int index, List<Integer> o) {
        addAll(index, Parser.toPrimitive(o.toArray(new Integer[o.size()])));
    }

    /**
     * Add value(s) to the list
     * 
     * @param index the index at which the value should be added
     * @param values the value(s) to be added
     * @throws IndexOutOfBoundsException if index is not between {@code 0} and {@link #size()} (inclusive)
     */
    // There is no multiAdd without index because then there would be ambiguity as to which method you wanted to call
    public void addAll(int index, int... values) {
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
        size--;
    }
    /**
     * Gets a value from the ArrayIntList
     * 
     * @param index the index to get the value
     * @return the value at the index
     * @throws IndexOutOfBoundsException if index is not between {@code 0} and {@link #size()} (exclusive)
     */
    public int get(int index) {
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
    public void set(int index, int value) {
        indexBoundChecker(index, false);
        elementData[index] = value;
    }

    /**
     * Replaces an element in the list with another element
     * 
     * @param oldValue The value to be replaced
     * @param newValue The value to replace it with
     */
    public void replaceAll(int oldValue, int newValue) {
        int i;
        while ((i = indexOf(oldValue)) != -1) set(i, newValue);
    }

    /**
     * Returns the index of the given value
     * 
     * @param value the value that will be searched for
     * @return The index of the given value. {@code -1} if the value is not found
     */
    public int indexOf(int value) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == value) return i;
        }
        return -1;
    }

    /**
     * Returns the smallest value in the list
     * 
     * @return the smallest value in the list
     * @throws IllegalStateException if the list is empty
     */
    public int min() {
        if (size == 0) throw new IllegalStateException("There is not minimum value to an empty list");
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            min = Math.min(min, elementData[size]);
        }
        return min;
    }

    /**
     * Returns the largest value in the list
     * 
     * @return the largest value in the list
     * @throws IllegalStateException if the list is empty
     */
    public int max() {
        if (size == 0) throw new IllegalStateException("There is not maximum value to an empty list");
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            max = Math.max(max, elementData[size]);
        }
        return max;
    }

    /**
     * Checks if the list contains a value
     * 
     * @param value The value to check for.
     * @return {@code true} if the value is in the list, {@code false} if it's not.
     */
    public boolean contains(int value) {
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
    public int[] toArray() {
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
     * Grows the list, increasing the capacity if needed
     * 
     * @param d How much to grow the list by
     * @throws IllegalArgumentException if d is less than {@code 0}
     */
    private void grow(int d) {
        if (d < 0) throw new IllegalArgumentException("Cannot shrink " + getClass().getSimpleName() + " capacity");
        else if (d == 0) return;
        else {
            size += d;
            if (size > elementData.length) {
                int[] temp = elementData;
                elementData = new int[size];
                System.arraycopy(temp, 0, elementData, 0, temp.length);
            }
        }
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
