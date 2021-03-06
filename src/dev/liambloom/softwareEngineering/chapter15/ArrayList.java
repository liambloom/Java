package dev.liambloom.softwareEngineering.chapter15;

import dev.liambloom.softwareEngineering.chapter7.$;
import dev.liambloom.tests.book.bjp.*;
import dev.liambloom.tests.ListTests;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;

/**
 * This is a clone of {@code java.util.ArrayList<E>}.
 * 
 * @author Liam Bloom 
 */
@SuppressWarnings("unchecked")
public class ArrayList<E> implements List<E> {
    public static void main(String[] args) {
        ListTests tests = new ListTests(new ArrayList<>());
        for (int i = 0; i < 100; i++)
            tests.runTests();
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
    public ArrayList(final int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("Capacity must be greater than 0, received " + capacity);
        /* I could avoid the unchecked cast, by not initializing elementData until
            the first value was added to it, and then generating the array using
            java.lang.reflect.Array and <value>.class.arrayType(), but this would
            be difficult and unnecessary, since elementData is never exposed to the
            client anyway*/
        elementData = (E[]) new Object[capacity];
    }

    /**
     * Creates a new {@code ArrayList} from a {@code List}
     * 
     * @param list The {@code List} that the {@code ArrayList} should be created from
     */
    public ArrayList(final List<E> list) {
        this((E[]) list.toArray());
    }

    /**
     * Creates a new {@code ArrayList} from an array
     * 
     * @param list An array of type E
     */
    public ArrayList(final E[] list) {
        this(list.length);
        elementData = list;
        size = list.length;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return listIterator();
    }

    @Override
    @ProgrammingProject(2)
    public ListIterator<E> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<E> listIterator(final int start) {
        return subList(0, size).listIterator(start);
    }

    @Override
    @ProgrammingProject(3)
    public List<E> subList(int fromIndex, int toIndex) {
        return subList(fromIndex, toIndex, new ParentSize(this::size, s -> size = s, s -> {}));
    }

    private List<E> subList(int start, int toIndex, ParentSize parentSize) {
        indexBoundChecker(start, true);
        indexBoundChecker(toIndex, true);
        return new List<>() {
            private int end = toIndex;

            @Override
            public int size() {
                return end - start;
            }

            @Override
            public boolean isEmpty() {
                return size() == 0;
            }

            @Override
            public boolean contains(Object o) {
                for (int i = start; i < end; i++){
                    if (Objects.equals(elementData[i], o))
                        return true;
                }
                return false;
            }

            @Override
            public Iterator<E> iterator() {
                return listIterator();
            }

            @Override
            public Object[] toArray() {
                return toArray(new Object[0]);
            }

            @Override
            public <T> T[] toArray(T[] a) {
                if (a.length < size())
                    a = (T[]) Array.newInstance(a.getClass().getComponentType(), size());
                System.arraycopy(elementData, start, a, 0, size());
                return a;
            }

            @Override
            public boolean add(E e) {
                ArrayList.this.add(end++, e);
                parentSize.incrementNoRoot();
                return true;
            }

            @Override
            public boolean remove(Object o) {
                final int index = indexOf(o);
                if (index == -1)
                    return false;
                remove(index);
                return true;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                boolean r = false;
                for (Object e : c) {
                    if (remove(e))
                        r = true;
                }
                return r;
            }

            @Override
            public boolean addAll(Collection<? extends E> c) {
                return addAll(end - start, c);
            }

            @Override
            public boolean addAll(int index, Collection<? extends E> c) {
                indexBoundChecker(index, true);
                for (E e : c)
                    add(index++, e);
                return !c.isEmpty();
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                boolean r = false;
                ListIterator<E> iter = listIterator();
                while (iter.hasNext()) {
                    if (c.contains(iter.next())){
                        r = true;
                        iter.remove();
                    }
                }
                return r;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                boolean r = false;
                ListIterator<E> iter = listIterator();
                while (iter.hasNext()) {
                    if (!c.contains(iter.next())){
                        r = true;
                        iter.remove();
                    }
                }
                return r;
            }

            @Override
            public void clear() {
                System.arraycopy(elementData, end, elementData, start, size - end);
                for (size--; size >= end; size--)
                    elementData[size] = null;
                parentSize.subtract(size());
                end = start;
            }

            @Override
            public E get(int index) {
                indexBoundChecker(index, false);
                return ArrayList.this.get(index + start);
            }

            @Override
            public E set(int index, E element) {
                indexBoundChecker(index, false);
                return ArrayList.this.set(index + start, element);
            }

            @Override
            public void add(int index, E element) {
                indexBoundChecker(index, true);
                ArrayList.this.add(index + start, element);
                end++;
                parentSize.incrementNoRoot();
            }

            @Override
            public E remove(int index) {
                indexBoundChecker(index, false);
                end--;
                parentSize.decrementNoRoot();
                return ArrayList.this.remove(index + start);
            }

            @Override
            public int indexOf(Object o) {
                int i = ArrayList.this.indexOf(o);
                return i == -1 ? i : i + start;
            }

            @Override
            public int lastIndexOf(Object o) {
                int i = ArrayList.this.lastIndexOf(o);
                return i == -1 ? i : i + start;
            }

            @Override
            public ListIterator<E> listIterator() {
                return listIterator(0);
            }

            @Override
            public ListIterator<E> listIterator(int index) {
                indexBoundChecker(index, true);
                // Not the best implementation, but it works
                return new ListIterator<>() {
                    private int position = index;
                    private boolean removeOk = false;
                    private boolean lastWasBack = false;

                    // The iterator docs already explain what each of these do
                    @Override
                    public E next() {
                        if (!hasNext()) throw new NoSuchElementException();
                        removeOk = true;
                        E r = elementData[start + position++];
                        //System.out.println(r);
                        return r;
                    }

                    @Override
                    public boolean hasNext() {
                        return position < size();
                    }

                    @Override
                    public int nextIndex() {
                        return position;
                    }

                    @Override
                    public E previous() {
                        if (!hasPrevious())
                            throw new NoSuchElementException();
                        removeOk = true;
                        lastWasBack = true;
                        return elementData[start + --position];
                    }

                    @Override
                    public boolean hasPrevious() {
                        return position > 0;
                    }

                    @Override
                    public int previousIndex() {
                        return position - 1;
                    }

                    @Override
                    public void add(final E e) {
                        removeOk = false;
                        ArrayList.this.add(position++ + start, e);
                        end++;
                        parentSize.incrementNoRoot();
                    }

                    @Override
                    public void set(final E e) {
                        if (!removeOk)
                            throw new IllegalStateException();
                        ArrayList.this.set((lastWasBack ? position : position - 1) + start, e);
                    }

                    @Override
                    public void remove() {
                        if (!removeOk) throw new IllegalStateException();
                        ArrayList.this.remove((lastWasBack ? position : --position) + start);
                        removeOk = false;
                        end--;
                        parentSize.decrementNoRoot();
                    }
                };
            }

            @Override
            public List<E> subList(int fromIndex, int toIndex) {
                indexBoundChecker(fromIndex, false);
                indexBoundChecker(toIndex, true);
                return ArrayList.this.subList(fromIndex + start, toIndex + start, new ParentSize(this::size, s -> {
                    parentSize.add(s - size());
                    end = s + start;
                }, s -> {
                    parentSize.addNoRoot(s - size());
                    end = s + start;
                }));
            }

            @Override
            public boolean equals(Object other) {
                if (other instanceof List<?> o) {
                    if (size() != o.size())
                        return false;
                    Iterator<?> iter1 = o.iterator();
                    Iterator<?> iter2 = this.iterator();
                    while (iter1.hasNext()) {
                        if (!Objects.equals(iter1.next(), iter2.next()))
                            return false;
                    }
                    return true;
                }
                else
                    return false;
            }

            @Override
            public String toString() {
                StringBuilder builder = new StringBuilder().append('[');
                if (size() > 0) {
                    builder.append(elementData[start]);
                    for (int i = start + 1; i < end; i++) {
                        builder.append(", ").append(elementData[i]);
                    }
                }
                return builder.append(']').toString();
            }

            private void indexBoundChecker(int index, boolean inclusive) {
                if (index < 0 || index > size() + (inclusive ? 1 : 0))
                    throw new IndexOutOfBoundsException("Index " + index + " out of bounds for ArrayList of length " + size());
            }
        };
    }

    @Override
    public boolean add(final E value) {
        addAll(size, value);
        return true;
    }

    @Override
    public void add(final int index, final E value) {
        addAll(index, value);
    }

    @Override
    public boolean addAll(final Collection<? extends E> o) {
        addAll(size, (E[]) o.toArray());
        return !o.isEmpty();
    }

    @Override
    @ProgrammingProject(1)
    public boolean addAll(final int index, final Collection<? extends E> o) {
        addAll(index, (E[]) o.toArray());
        return !o.isEmpty();
    }

    /**
     * Add value(s) to the list
     * 
     * @param index the index at which the value should be added
     * @param values the value(s) to be added
     * @throws IndexOutOfBoundsException if index is not between {@code 0} and {@link #size()} (inclusive)
     */
    // There is no multiAdd without index because then there would be ambiguity as to which method you wanted to call
    public void addAll(final int index, final E... values) {
        indexBoundChecker(index, true);
        final int prevSize = size;
        grow(values.length);
        System.arraycopy(elementData, index, elementData, index + values.length, prevSize - index);
        System.arraycopy(values, 0, elementData, index, values.length);
    }

    @Override
    public E remove(final int index) {
        indexBoundChecker(index, false);
        E r = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index);
        elementData[--size] = null;
        return r;
    }

    @Override
    @ProgrammingProject(1)
    public boolean remove(Object o) {
        final int index = indexOf(o);
        if (index == -1)
            return false;
        remove(index);
        return true;
    }

    /**
     * Removes the last element of the list, returning it
     * 
     * @return the removed element
     * @throws NoSuchElementException if the list's {@link #size()} is {@code 0}
     */
    @Exercise(11)
    public E removeLast() {
        if (size == 0) throw new NoSuchElementException();
        else {
            final E removed = get(size - 1);
            remove(size - 1);
            return removed;
        }
    }

    /**
     * Removes the first n elements of the list
     * 
     * @param n how many elements to remove
     * @throws IndexOutOfBoundsException if n is greater than the list's {@link #size()}
     */
    @Exercise(12)
    public void removeFront(final int n) {
        if (n > size) throw new IndexOutOfBoundsException(n);
        System.arraycopy(elementData, n, elementData, 0, size - n);
        for (int i = n; i < size; i++) elementData[i] = null;
        size -= n;
    }

    /**
     * Removes every occurrence of an element
     * @param e the element to remove
     */
    @Exercise(13)
    public void removeAll(final E e) {
        int i;
        while ((i = indexOf(e)) != -1) remove(i);
    }

    @Override
    @ProgrammingProject(1)
    public boolean removeAll(final Collection<?> c) {
        boolean r = false;
        for (Object e : c) {
            if (remove(e))
                r = true;
        }
        return r;
    }

    @Override
    @ProgrammingProject(1)
    public boolean retainAll(final Collection<?> c) {
        boolean r = false;
        Iterator<E> iter = iterator();
        while (iter.hasNext()) {
            if (!c.contains(iter.next())){
                iter.remove();
                r = true;
            }
        }
        return r;
    }

    @Override
    public E get(final int index) {
        indexBoundChecker(index, false);
        return elementData[index];
    }

    @Override
    public E set(final int index, final E value) {
        indexBoundChecker(index, false);
        E r = elementData[index];
        elementData[index] = value;
        return r;
    }

    /**
     * Fills the list with a particular value. <strong>Warning:</strong>
     * this does not clone newValue. If there are multiple matches, they will all
     * be replaced by pointers to <em>the same object</em>, not copies of it.
     * 
     * @param value the value to fill the list with
     */
    @Exercise(6)
    public void fill(final E value) {
        for (int i = 0; i < size; i++) elementData[i] = value;
    }

    /**
     * Replaces an element in the list with another element.
     * <strong>Warning:</strong> this does not clone newValue. If there are
     * multiple matches, they will all be replaced by pointers to <em>the same
     * object</em>, not copies of it.
     * 
     * @param oldValue The value to be replaced
     * @param newValue The value to replace it with
     */
    @Exercise(3)
    public void replaceAll(final E oldValue, final E newValue) {
        int i;
        while ((i = indexOf(oldValue)) != -1) set(i, newValue);
    }

    /**
     * Reverses the order of the list
     */
    @Exercise(4)
    public void reverse() {
        for (int i = 0; i < size / 2; i++) {
            final E temp = elementData[i];
            elementData[i] = elementData[size - i - 1];
            elementData[size - i - 1] = temp;
        }
    }

    /**
     * Mirrors the list by adding the elements, in reverse order,
     * to the end of the list.
     */
    @Exercise(15)
    public void mirror() {
        ensureCapacity(size * 2);
        for (int i = size - 1; i >= 0; i--) add(elementData[i]);
        size *= 2;
    }

    /**
     * Replaces every value with two of itself.
     * <strong>Warning:</strong> this does not clone the value. The value will be
     * replaced by two pointers to <em>the same object</em>, not copies of it.
     */
    @Exercise(16)
    public void stutter() {
        stretch(2);
    }

    /**
     * Replaces every value with n of itself. <strong>Warning:</strong>
     * this does not clone the value. The value will be replaced by n pointers
     * to <em>the same object</em>, not copies of it.
     * 
     * @param n how many times to repeat each value
     */
    @Exercise(17)
    public void stretch(final int n) {
        if (n <= 0) clear();
        else {
            ensureCapacity(size * n);
            for (int i = size - 1; i >= 0; i--) {
                for (int j = 0; j < n; j++) elementData[i * n + j] = elementData[i];
            }
            size *= n;
        }
    }

    /**
     * Moves the 1st element in the list to the end of the list.
     */
    @Exercise(20)
    public void rotate() {
        if (size == 0) return;
        final E temp = elementData[0];
        remove(0);
        add(temp);
    }

    /**
     * Switches the positions of each pair of values in the list.
     */
    @Exercise(21)
    public void switchPairs() {
        for (int i = 0; i < size / 2; i++) {
            final E temp = elementData[i * 2];
            elementData[i * 2] = elementData[i * 2 + 1];
            elementData[i * 2 + 1] = temp;
        }
    }

    /**
     * Appends a copy of the list to the end of the list.
     * <strong>Warning:</strong> this does not clone the values. The value will be
     * replaced by n pointers to <em>the same object</em>, not copies of it.
     */
    @Exercise(18)
    public void doubleList() {
        ensureCapacity(size * 2);
        System.arraycopy(elementData, 0, elementData, size, size);
        size *= 2;
    }

    @Override
    public int indexOf(final Object value) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elementData[i], value)) return i;
        }
        return -1;
    }

    @Override
    @Exercise(1)
    @ProgrammingProject(1)
    public int lastIndexOf(final Object value) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(elementData[i], value)) return i;
        }
        return -1;
    }

    /**
     * Returns the index of the first occurrence of an ordered list of values.
     * 
     * @param l the list that will be searched for
     * @return the index of the last occurrence given value. {@code -1} if the value is not found
     */
    @Exercise(2)
    public int indexOfSubList(final ArrayList<E> l) {
        outer: for (int i = 0; i < size; i++) {
            for (int j = 0; j < l.size; j++) {
                if (!Objects.equals(l.get(j), get(i + j))) continue outer;
            }
            return i;
        }
        return -1;
    }
    
    /**
     * Returns the number of times a particular element occurs in the list.
     * I think this method should be named occurencesOf
     * 
     * @param e the element to check for
     * @return the amount of times the element 'e' occurs in the list
     */
    @Exercise(8)
    public int count(final E e) {
        int c = 0;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elementData[i], e)) c++;
        }
        return c;
    }

    /**
     * Self Check 8. Returns the smallest value in the list
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
            final E e = elementData[i];
            if (((Comparable<E>) min).compareTo(e) > 0) min = e;
        }
        return min;
    }

    /**
     * Self Check 8. Returns the largest value in the list
     * 
     * @return the largest value in the list
     * @throws IllegalStateException if the list is empty
     * @throws ClassCastException if {@code E} does not implement {@code Comparable<E>}
     */
    public E max() {
        if (size == 0) throw new IllegalStateException("There is not minimum value to an empty list");
        E max = elementData[0];
        if (size == 1) ((Comparable<E>) max).compareTo(max); // This checks to make sure E implements Comparable<E> even if size == 1
        for (int i = 1; i < size; i++) {
            final E e = elementData[i];
            if (((Comparable<E>) max).compareTo(e) < 0) max = e;
        }
        return max;
    }

    /**
     * Checks if the list is pairwise sorted. &ldquo;A list is
     * considered pairwise sorted if each successive pair of numbers is in
     * nondecreasing order&rdquo; (Stuart Reges and Marty Stepp, Building Java
     * programs: a back to basics approach&mdash;Third Edition, Page 948).
     * 
     * @return A boolean representing weather this list is pairwise sorted
     * @throws ClassCastException if {@code E} does not implement {@code Comparable<E>}
     */
    @Exercise(7)
    public boolean isPairwiseSorted() {
        for (int i = 0; i < size / 2 * 2; i += 2) {
            if (((Comparable<E>) elementData[i]).compareTo(elementData[i + 1]) > 0) return false;
        }
        return true;
    }

    /**
     * Self Check 19. Returns the sum of the elements.
     * 
     * @return the sum of every element in the list
     * @throws ClassCastException if any of the elements is a different type from
     *                            another or if they are not one of Byte, Short,
     *                            Integer, Long, Float, or Double
     */
    public E sum() {
        if (size == 0) return (E) Integer.valueOf(0);
        else return (E) $.castNumber($.sum(asDoubleArr()), (Class<? extends Number>) elementData[0].getClass());
    }

    /**
     * Self Check 20. Returns the average of the elements
     * 
     * @return the average of every element
     * @throws ClassCastException if any of the elements is a different type from
     *                            another or if they are not one of Byte, Short,
     *                            Integer, Long, Float, or Double
     */
    public E average() {
        if (size == 0) return (E) Integer.valueOf(0);
        else return (E) $.castNumber(((Number) sum()).doubleValue() / size, (Class<? extends Number>) elementData[0].getClass());
    }

    /**
     * Returns a new {@code ArrayList<E>} where the nth value of the new
     * list is the sum of elements 0 through n of this list
     * 
     * @return a list of the cumulative sums at every index of this list
     * @throws ClassCastException if any of the elements is a different type from
     *                            another or if they are not one of Byte, Short,
     *                            Integer, Long, Float, or Double
     */
    @Exercise(5)
    public ArrayList<E> runningTotal() {
        if (size == 0) return new ArrayList<>(0);
        final double[] arr = asDoubleArr();
        for (int i = 1; i < size; i++) arr[i] += arr[i - 1];
        final ArrayList<E> r = new ArrayList<>(size);
        final Class<? extends Number> e = (Class<? extends Number>) elementData[0].getClass();
        for (int i = 0; i < size; i++) r.add((E) $.castNumber(arr[i], e));
        return r;
    }

    /**
     * Replaces each consecutive pair of values with their sum. If
     * {@link #size()} is odd, the last element is ignored
     * 
     * @throws ClassCastException if any of the elements is a different type from
     *                            another or if they are not one of Byte, Short,
     *                            Integer, Long, Float, or Double
     */
    @Exercise(19)
    public void compress() {
        if (size == 0) return;
        final double[] arr = asDoubleArr();
        final Class<? extends Number> e = (Class<? extends Number>) elementData[0].getClass();
        for (int i = 0; i < size / 2; i++) elementData[i] = (E) $.castNumber(arr[i * 2] + arr[i * 2 + 1], e);
        if (size % 2 != 0) elementData[size / 2] = elementData[size - 1];
        size = (int) Math.ceil(size / 2.0);
    }

    /**
     * Returns the number of occurences of the mode of the list (the
     * mode being the element that appears most often)
     * 
     * @return the number of occurences of the mode of the list
     */
    @Exercise(9)
    public int maxCount() {
        final HashMap<E, Integer> map = new HashMap<>();
        for (int i = 0; i < size; i++) {
            final E e = elementData[i];
            map.put(e, map.getOrDefault(e, 0) + 1);
        }
        int max = 0;
        for (final int e: map.values()) {
            max = Math.max(max, e);
        }
        return max;
    }

    /**
     * Returns the length of the longest non-descending sequence of
     * consecutive comparable elements in the list
     * 
     * @return The length of the longest non-descending sequence
     * @throws ClassCastException if {@code E} does not implement {@code Comparable<E>}
     */
    @Exercise(10)
    public int longestSortedSequence() {
        int len = 0;
        int maxLen = 0;
        if (size >= 1) {
            // Why doesn't java have a void operator?
            ((Comparable<E>) elementData[0]).compareTo(elementData[0]);
            len++;
            maxLen++;
        }
        for (int i = 1; i < size; i++) {
            if (((Comparable<E>) elementData[i - 1]).compareTo(elementData[i]) <= 0) maxLen = Math.max(maxLen, ++len);
            else len = 1;
        }
        return maxLen;
    }

    @Override
    public boolean contains(final Object value) {
        return indexOf(value) >= 0;
    }

    /**
     * Checks if this list contains all values in the given list
     *
     * @param list The list of elements to check for
     * @return {@code true} if all values from {@code list} are in this list, {@code false} otherwise.
     */
    @ProgrammingProject(1)
    public boolean containsAll(final Collection<?> list) {
        for (Object e : list) {
            if (!contains(e))
                return false;
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        // Setting everything to null isn't required, but it allows the garbage collector to free up more space
        for (int i = 0; i < size; i++) elementData[i] = null;
        size = 0;
    }

    /*/**
     * Returns the list as an array
     * 
     * @return The list, but in the form of an array
     /
    public E[] toArray() {
        if (elementData.length == 0) return (E[]) new Object[0];
        Class<E> e = 
        elementData.getClass().sup
        return (E[]) Arrays.copyOfRange(elementData, 0, size);
    }*/

    /**
     * Prints the inversions of the list. &ldquo;An inversion is a pair
     * of numbers in which the first appears before the second in the list, but the
     * first is greater than the second.&rdquo; (Stuart Reges and Marty Stepp,
     * Building Java programs: a back to basics approach&mdash;Third Edition, Page
     * 949)
     * 
     * @throws ClassCastException if {@code E} does not implement {@code Comparable<E>}
     */
    @Exercise(14)
    public void printInversions() {
        printInversions(0);
    }

    /**
     * A recursive helper method for {@link #printInversions()}
     * 
     * @param startIndex the index to start inversions at
     * @throws ClassCastException if {@code E} does not implement {@code Comparable<E>}
     */
    private void printInversions(final int startIndex) {
        if (startIndex == size - 1) return;
        final Comparable<E> base = (Comparable<E>) elementData[startIndex];
        for (int i = startIndex + 1; i < size; i++) {
            if (base.compareTo(elementData[i]) > 0) System.out.printf("(%s, %s)%n", base.toString(), elementData[i].toString());
        }
        printInversions(startIndex + 1);
    }   

    /**
     * Throws an error if the index is out of bounds
     * 
     * @param index The index to check 
     * @param inclusive If false, it will throw an error if the index is equal to size
     * @throws IndexOutOfBoundsException if the index is not valid for the list's size
     */
    private void indexBoundChecker(final int index, final boolean inclusive) {
        if (index >= size + (inclusive ? 1 : 0) || index < 0) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for ArrayList of size " + size);
        }
    }

    /**
     * Grows the list, increasing the capacity if needed.
     * 
     * @param d How much to grow the list by
     * @throws IllegalArgumentException if d is less than {@code 0}
     */
    private void grow(final int d) {
        if (d < 0) throw new IllegalArgumentException("Cannot shrink " + getClass().getSimpleName() + " capacity");
        else ensureCapacity(size += d);
    }

    /**
     * Turns the list into a double[]. Note: T extends both {@code Number} and
     * {@code E}, but I could not add both as bounds
     * 
     * @param <T> the class of each element in the list
     * @return the list as a double[]
     * @throws ClassCastException if any of the elements is a different type from
     *                            another
     */
    private <T extends E> double[] asDoubleArr() {
        if (size == 0) return new double[0];
        final double[] prim = new double[size];
        final Class<T> t = (Class<T>) elementData[0].getClass();
        if (t.isInterface()) throw new IllegalStateException("This should be impossible"); // This isn't in the javadoc because it should be impossible
        if (!Number.class.isAssignableFrom(t)) throw new ClassCastException(t.getName() + " is not a Number");
        for (int i = 0; i < size; i++) {
            if (!elementData[i].getClass().equals(t)) throw new ClassCastException("Cannot add numbers of different types");
            prim[i] += ((Number) get(i)).doubleValue();
        }
        return prim;
    }

    /**
     * Ensures that the capacity is at least c. More precisely,
     * it sets the capacity to the larger of the c and
     * {@code 1.5 * <the current capacity>}.
     * 
     * @param capacity The capacity it must be
     */
    public void ensureCapacity(final int capacity) {
        if (capacity > elementData.length) elementData = Arrays.copyOf(elementData, (int) Math.max(capacity, 1.5 * elementData.length));
    }

    @Override
    @ProgrammingProject(1)
    public Object[] toArray() {
        return toArray(new Object[0]);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            a = (T[]) Array.newInstance(a.getClass().getComponentType(), size);
        System.arraycopy(elementData, 0, a, 0, size);
        return a;
    }

    @Override
    @ProgrammingProject(1)
    public boolean equals(Object other) {
        if (other instanceof List<?> o) {
            if (size() != o.size())
                return false;
            Iterator<?> iter1 = o.iterator();
            Iterator<?> iter2 = this.iterator();
            while (iter1.hasNext()) {
                if (!Objects.equals(iter1.next(), iter2.next()))
                    return false;
            }
            return true;
        }
        else
            return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder().append('[');
        if (size > 0) {
            builder.append(elementData[0]);
            for (int i = 1; i < size; i++) {
                builder.append(", ").append(elementData[i]);
            }
        }
        return builder.append(']').toString();
    }
}

class ParentSize {
    private final IntSupplier get;
    private final IntConsumer set;
    private final IntConsumer setNoRoot;

    public ParentSize(IntSupplier get, IntConsumer set, IntConsumer setNoRoot) {
        this.get = get;
        this.set = set;
        this.setNoRoot = setNoRoot;
    }

    public int get() {
        return get.getAsInt();
    }

    public void set(int value) {
        set.accept(value);
    }

    public void setNoRoot(int value) {
        setNoRoot.accept(value);
    }

    public void add(int value) {
        set(get() + value);
    }

    public void addNoRoot(int value) {
        setNoRoot(get() + value);
    }

    public void subtract(int value) {
        add(-value);
    }

    public void subtractNoRoot(int value) {
        addNoRoot(-value);
    }

    public void increment() {
        add(1);
    }

    public void incrementNoRoot() { addNoRoot(1); }

    public void decrement() {
        subtract(1);
    }

    public void decrementNoRoot() {
        subtractNoRoot(1);
    }
}
