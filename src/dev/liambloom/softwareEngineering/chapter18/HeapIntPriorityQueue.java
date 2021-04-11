package dev.liambloom.softwareEngineering.chapter18;

import java.util.Arrays;
import java.util.NoSuchElementException;

import dev.liambloom.tests.book.bjp3.Exercise;

// I wasn't sure of the difference between 1 & 2, so I just chose 2

// Exercises 13-15
public class HeapIntPriorityQueue {
    private int[] elementData;
    private int size;

    // Constructs an empty queue.
    public HeapIntPriorityQueue() {
        elementData = new int[10];
        size = 0;
    }

    // Adds the given element to this queue.
    public void add(int value) {
        // resize if necessary
        if (size + 1 >= elementData.length) {
            elementData = Arrays.copyOf(elementData, elementData.length * 2);
        }

        // insert as new rightmost leaf
        elementData[size + 1] = value;

        // "bubble up" toward root as necessary to fix ordering
        int index = size + 1;
        while (hasParent(index)) {
            int parent = parent(index);
            if (elementData[index] < elementData[parent]) {
                swap(elementData, index, parent(index));
                index = parent(index);
            } else {
                break;  // proper place found; terminate the loop
            }
        }

        size++;
    }

    // Returns true if there are no elements in this queue.
    public boolean isEmpty() {
        return size == 0;
    }

    // Returns the minimum value in the queue without modifying the queue.
    // If the queue is empty, throws a NoSuchElementException.
    public int peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return elementData[1];
    }

    // Removes and returns the minimum value in the queue.
    // If the queue is empty, throws a NoSuchElementException.
    public int remove() {
        int result = peek();

        // move rightmost leaf to become new root
        swap(elementData, 1, size);
        size--;

        // "bubble down" root as necessary to fix ordering
        bubbleDown(1);
        return result;
    }

    // Swaps the element at the given index as necessary to fix ordering.
    private void bubbleDown(int index) {
        while (hasLeftChild(index)) {
            int left = leftChild(index);
            int right = rightChild(index);
            int child = left;
            if (hasRightChild(index) &&
                    elementData[right] < elementData[left]) {
                child = right;
            }

            if (elementData[index] > elementData[child]) {
                swap(elementData, index, child);
                index = child;
            } else {
                break;  // proper place found; terminate the loop
            }
        }
    }

    // Returns the number of elements in the queue.
    public int size() {
        return size;
    }

    // Returns the internal heap array used to implement the queue.
    // Not generally considered good practice, but provided to
    // illustrate heap sort.
    /*public int[] toArray() {
        return elementData;
    }*/

    @Exercise(13)
    // You already did this for us, but here's a better implementation
    public int[] toArray() {
        int[] a = new int[size];
        System.arraycopy(elementData, 1, a, 0, size);
        return a;
    }

    @Exercise(14)
    // You did this one for us. Why?
    // Returns a string representation of this queue, such as "[10, 20, 30]";
    // The elements are not guaranteed to be listed in sorted order.
    public String toString() {
        String result = "[";
        if (!isEmpty()) {
            result += elementData[1];
            for (int i = 2; i <= size; i++) {
                result += ", " + elementData[i];
            }
        }
        return result + "]";
    }

    @Exercise(15)
    public void merge(HeapIntPriorityQueue o) {
        for (int i = 1; i <= o.size; i++)
            add(o.elementData[i]);
    }

    // helpers for navigating indexes up/down the tree
    private int parent(int index) {
        return index / 2;
    }

    private int leftChild(int index) {
        return index * 2;
    }

    private int rightChild(int index) {
        return index * 2 + 1;
    }

    private boolean hasParent(int index) {
        return index > 1;
    }

    private boolean hasLeftChild(int index) {
        return leftChild(index) <= size;
    }

    private boolean hasRightChild(int index) {
        return rightChild(index) <= size;
    }

    private void swap(int[] a, int index1, int index2) {
        int temp = a[index1];
        a[index1] = a[index2];
        a[index2] = temp;
    }


    public static void heapSort(int[] a) {
        HeapIntPriorityQueue pq = new HeapIntPriorityQueue();
        pq.elementData = a;
        pq.size = a.length - 1;

        System.out.println("start : " + Arrays.toString(a));

        for (int i = a.length / 2; i >= 0; i--) {
            pq.bubbleDown(i);
            System.out.println("bubble: " + Arrays.toString(a));
        }
        while (!pq.isEmpty()) {
            pq.remove();
            System.out.println("remove: " + Arrays.toString(a));
        }
    }
}

