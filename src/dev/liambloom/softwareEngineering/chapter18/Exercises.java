package dev.liambloom.softwareEngineering.chapter18;

import java.util.*;

import dev.liambloom.tests.book.bjp.Exercise;

// Contains exercises 8-12
public class Exercises {
    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.addAll(List.of(7, 8, 10, 45));
        stutter(pq);
        //System.out.println(isConsecutive(pq));
        System.out.println(pq);
    }

    @Exercise(8)
    public static <T extends Comparable<T>> void descending(T[] a) {
        PriorityQueue<T> pq = new PriorityQueue<>();
        for (T e : a)
            pq.add(e);
        for (int i = 0; !pq.isEmpty(); i++)
            a[i] = pq.remove();
    }

    @Exercise(9)
    public static <T> T kthSmallest(PriorityQueue<T> pq, int k) {
        if (k <= 0 || k > pq.size())
            throw new IllegalArgumentException();
        Stack<T> aux = new Stack<>();
        while (k --> 0)
            aux.add(pq.remove());
        T r = aux.peek();
        while (!aux.isEmpty())
            pq.add(aux.pop());
        return r;
    }

    @Exercise(10)
    public static boolean isConsecutive(Queue<Integer> q) {
        if (q.isEmpty())
            return true;
        final Queue<Integer> aux = new LinkedList<>();
        boolean r = true;
        Integer prev = q.remove();
        aux.add(prev);
        while (!q.isEmpty()) {
            Integer next = q.remove();
            if (r) {
                if (next - 1 != prev)
                    r = false; // I could break here if q were a PriorityQueue
                prev = next;
            }
            aux.add(next);
        }
        while (!aux.isEmpty())
            q.add(aux.remove());
        return r;
    }

    @Exercise(11)
    public static <T> void removeDuplicates(PriorityQueue<T> pq) {
        final Queue<T> aux = new LinkedList<>();
        T prev = pq.remove();
        aux.add(prev);
        while (!pq.isEmpty()) {
            final T next = pq.remove();
            if (!next.equals(prev)) {
                prev = next;
                aux.add(next);
            }
        }
        while (!aux.isEmpty())
            pq.add(aux.remove());
    }

    @Exercise(12)
    public static void stutter(Queue<Integer> pq) {
        Queue<Integer> aux = new LinkedList<>();
        while (!pq.isEmpty())
            aux.add(pq.remove());
        while (!aux.isEmpty()) {
            pq.add(aux.peek());
            pq.add(aux.remove());
        }
    }
}
