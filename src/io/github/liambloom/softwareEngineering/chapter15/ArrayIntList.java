package io.github.liambloom.softwareEngineering.chapter15;

// Page 924

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

    private int[] elementData;
    private int size = 0;

    /**
     * Returns a new ArrayIntList with the capacity {@value ArrayIntList#DEFAULT_CAPACITY}
     */
    public ArrayIntList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Returns a new ArrayIntList with the given capacity
     * 
     * @param capacity The capacity of the new list
     */
    public ArrayIntList(int capacity) {
        elementData = new int[capacity];
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
        add(size, value);
    }

    /**
     * Add a value to the list
     * 
     * @param index the index at which the value should be added
     * @param value the value to be added
     * @throws IndexOutOfBoundsException if index is not between {@code 0} and {@link ArrayIntList#size()}
     */
    public void add(int index, int value) {
        inclusiveIndexBoundChecker(index);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    /**
     * Removes the value at the given index
     * 
     * @param index the index of the value to be removed
     * @throws IndexOutOfBoundsException if index is not between {@code 0} and {@link ArrayIntList#size()}
     */
    public void remove(int index) {
        indexBoundChecker(index);
        System.arraycopy(elementData, index + 1, elementData, index, size - index);
        size--;
    }
    /**
     * Gets a value from the ArrayIntList.
     * 
     * @param index the index to get the value.
     * @return the value at the index.
     * @throws IndexOutOfBoundsException if index is not between {@code 0} and {@link ArrayIntList#size()}
     */
    public int get(int index) {
        indexBoundChecker(index);
        return elementData[index];
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
     * @returns the smallest value in the list
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
     * @returns the largest value in the list
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

    private void indexBoundChecker(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException(index);
        }
    }
    
    private void inclusiveIndexBoundChecker(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException(index);
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
