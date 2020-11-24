package io.github.liambloom.softwareEngineering.chapter16;

import java.util.Collection;
import java.util.List;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Comparator;
import java.lang.reflect.Array;
import java.util.NoSuchElementException;

public abstract class AbstractLinkedList<E, N extends AbstractLinkedList<E, N>.Node> implements List<E> {
    N head = null;
    N tail = null;
    int size = 0;

    protected abstract class Node {
        E data;
        N next = null;

        public Node(final E data) {
            this.data = data;
        }
    }

    protected abstract class AbstractListIterator implements ListIterator<E> {
        N prev = null;
        N next = head;
        N lastReturned = null;
        int index = 0;
        boolean modOk = false;

        public E next() {
            if (next == null)
                throw new NoSuchElementException();
            else {
                index++;
                prev = next;
                next = next.next;
                modOk = true;
                lastReturned = prev;
                return prev.data;
            }
        }

        public boolean hasNext() {
            return next != null;
        }

        public int nextIndex() {
            return index;
        }

        public boolean hasPrevious() {
            return prev != null;
        }

        public int previousIndex() {
            return index - 1;
        }

        public void set(E e) {
            if (!modOk)
                throw new IllegalStateException();
            lastReturned.data = e;
        }
    }

    public boolean add(final E e) {
        listIterator(size).add(e);
        return true;
    }

    public void add(final int index, final E e) {
        listIterator(index).add(e);
    }

    public boolean addAll(Collection<? extends E> c) {
        ListIterator<E> iter = listIterator(size);
        for (E e : c)
            iter.add(e);
        return c.size() != 0;
    }

    public boolean addAll(final int index, final Collection<? extends E> c) {
        if (c.size() == 0)
            return false;
        ListIterator<E> iter = listIterator(index);
        for (E e : c)
            iter.add(e);
        return true;
    }

    @SuppressWarnings("unchecked")
    public void addSorted(final E data) {
        if (data instanceof Comparable)
            addSorted(data, (Comparator<E>) Comparator.naturalOrder());
        else
            throw new ClassCastException();
    }

    public void addSorted(final E data, final Comparator<E> comparator) {
        final ListIterator<E> iter = listIterator();
        while (iter.hasNext() && comparator.compare(iter.next(), data) < 0);
        iter.previous();
        iter.add(data);
    }

    @SuppressWarnings("unused")
    private void AddInOrder(final N node) {
        addSorted(node.data);
    }

    public int size() {
        return size;
    }

    public Iterator<E> iterator() {
        return listIterator();
    }

    protected N getNode(final int i) {
        if (i == 0)
            return head;
        else if (i + 1 == size)
            return tail;
        else if (i >= size || i < 0)
            return null;
        else {
            N e = head;
            for (int j = 0; j < i; j++)
                e = e.next;
            return e;
        }
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean contains(Object o) {
        for (E e : this) {
            if (e.equals(o))
                return true;
        }
        return false;
    }

    public boolean containsAll(Collection<?> c) {
        for (Object e : c) {
            if (!contains(e))
                return false;
        }
        return true;
    }

    public boolean equals(final Object other) {
        if (other instanceof List) {
            List<?> o = (List<?>) other;
            if (size == o.size()) {
                Iterator<E> iterThis = iterator();
                Iterator<?> iterOther = o.iterator();
                while (iterThis.hasNext()) {
                    if (!iterThis.next().equals(iterOther.next()))
                        return false;
                }
                return true;
            }
            else 
                return false;
        }
        else
            return false;
    }

    public E get(int i) {
        return getNode(i).data;
    }

    public int hashCode() {
        int hashCode = 1;
        for (E e : this)
            hashCode = 31 * hashCode + (e == null ? 0 : e.hashCode());
        return hashCode;
    }

    public int indexOf(Object o) {
        Iterator<E> iter = iterator();
        for (int i = 0; iter.hasNext(); i++) {
            if (iter.next().equals(o))
                return i;
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        final ListIterator<E> iter = listIterator(size);
        for (int i = size - 1; iter.hasPrevious(); i--) {
            if (iter.previous().equals(o))
                return i;
        }
        return -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public ListIterator<E> listIterator() {
        return listIterator(0);
    }

    public E set(int index, E element) {
        N n = getNode(index);
        E prev = n.data;
        n.data = element;
        return prev;
    }

    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        Iterator<E> iter = iterator();
        for (int i = 0; i < index; i++)
            iter.next();
        E r = iter.next();
        iter.remove();
        return r;
    }

    public boolean remove(Object o) {
        Iterator<E> iter = iterator();
        while (iter.hasNext()) {
            if (o == null ? iter.next() == null : o.equals(iter.next())) {
                iter.remove();
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unused")
    private void removeNode(N node) {
        Iterator<E> iter = iterator();
        while (iter.hasNext()) {
            if (node.data == null ? iter.next() == null : node.data.equals(iter.next()))
                iter.remove();
        }
    }

    public boolean removeAll(final Collection<?> c) {
        boolean changed = false;
        Iterator<E> iter = iterator();
        while (iter.hasNext()) {
            if (c.contains(iter.next())) {
                iter.remove();
                changed = true;
            }
        }
        return changed;
    }

    public boolean retainAll(final Collection<?> c) {
        boolean changed = false;
        Iterator<E> iter = iterator();
        while (iter.hasNext()) {
            if (!c.contains(iter.next())) {
                iter.remove();
                changed = true;
            }
        }
        return changed;
    }

    public List<E> subList(int start, final int end) {
        if (start < 0 || start > end || end > size)
            throw new IndexOutOfBoundsException();
        List<E> newList = new LinkedList<>();
        for (Node e = getNode(start); start < end; start++) {
            newList.add(e.data);
            e = e.next;
        }
        return newList;
    }

    public Object[] toArray() {
        return toArray(new Object[0]);
    }

    // A large portion of this method is taken from the original java code
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            a = (T[]) Array.newInstance(a.getClass().getComponentType(), size);
        N n = head;
        Object[] r = a;
        for (int i = 0; i < size; n = n.next)
            r[i++] = n.data;
        for (int i = size; i < a.length; i++)
            r[i] = null;
        return a;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        if (size > 0) {
            Iterator<E> iter = iterator();
            builder.append(iter.next().toString());
            while (iter.hasNext())
                builder.append(", " + iter.next().toString());
        }
        builder.append(']');
        //System.out.println("String: " + builder.toString());
        return builder.toString();
    }
}
