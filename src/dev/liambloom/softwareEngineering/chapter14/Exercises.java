package dev.liambloom.softwareEngineering.chapter14;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;

import dev.liambloom.tests.Tester;
import dev.liambloom.tests.book.bjp.*;

// Do 7
public class Exercises {
    @SuppressWarnings("unchecked")
    public static void main(final String[] args) {
        final Tester tester = new Tester(Tester.Policy.RunUntilFailure);
        tester
                .testAssert(() -> {
                    final Stack<Integer> stack = stackOf(3, -5, 1, 2, -4);
                    splitStack(stack);
                    boolean negative = false;
                    while (!stack.isEmpty()) {
                        if (stack.pop() >= 0) {
                            if (negative)
                                return false;
                        }
                        else
                            negative = true;
                    }
                    return true;
                })
                .testAssert("Test 5", () -> {
                    final Stack<Integer> s1 = stackOf(1, 2, 3);
                    final Stack<Integer> s2 = stackOf(4, 2, 3);
                    return equal(s1, (Stack<Integer>) s1.clone()) && !equal(s1, s2);
                })
                .testAssert("Test 10", () -> {
                    final Stack<Integer> s1 = stackOf(5, 6, 7, 8, 9, 10);
                    final Stack<Integer> s2 = stackOf(7, 8, 9, 10, 12);
                    return isConsecutive(s1) && !isConsecutive(s2) && s1.equals(stackOf(5, 6, 7, 8, 9, 10)) && s2.equals(stackOf(7, 8, 9, 10, 12));
                })
                .testAssert("Test 15", () -> {
                    final Stack<Integer> s1 = stackOf(20, 20, 17, 11, 8, 8, 3, 2);
                    final Stack<Integer> s2 = stackOf(20, 20, 17, 11, 8, 3, 8, 2);
                    return isSorted(s1) && !isSorted(s2) && s1.equals(stackOf(20, 20, 17, 11, 8, 8, 3, 2)) && s2.equals(stackOf(20, 20, 17, 11, 8, 3, 8, 2));
                })
                .test("Test 16", () -> {
                        final Stack<Integer> stack = stackOf(10, 53, 19, 24);
                        mirror(stack);
                        return stack;
                    }, stackOf(10, 53, 19, 24, 24, 19, 53, 10))
                .test("Test 18", () -> {
                        final Queue<Integer> q = queueOf(10, 50 ,19, 54, 30, 67);
                        mirrorHalves(q);
                        return q;
                    },
                    queueOf(10, 50, 19, 19, 50, 10, 54, 30, 67, 67, 30, 54))
                .test("Test 20", () -> {
                    final var q = queueOf(2, 8, -5, 19, 7, 3, 24, 42);
                    interleave(q);
                    return q;
                }, queueOf(2, 7, 8, 3, -5, 24, 19, 42));
        tester.close();
    }

    @SafeVarargs
    private static <E> Stack<E> stackOf(final E... elements) {
        final Stack<E> stack = new Stack<>();
        for (final E e : elements)
            stack.push(e);
        return stack;
    }

    @SafeVarargs
    private static <E> Queue<E> queueOf(final E... elements) {
        final Queue<E> queue = new LinkedList<>();
        Collections.addAll(queue, elements);
        return queue;
    }

    @Exercise(1)
    public static void splitStack(final Stack<Integer> stack) {
        final Queue<Integer> aux = new LinkedList<>();
        int lastNegative = 0;
        for (int i = 0; !stack.isEmpty(); i++) {
            final Integer e = stack.pop();
            if (e < 0)
                lastNegative = i;
            aux.add(e);
        }
        for (int i = 0; i < lastNegative; i++) {
            final Integer e = aux.remove();
            if (e < 0)
                stack.push(e);
            else
                aux.add(e);
        }
        while (!aux.isEmpty())
            stack.push(aux.remove());
    }

    @Exercise(5)
    public static boolean equal(final Stack<Integer> s1, final Stack<Integer> s2) {
        if (s1.size() != s2.size())
            return false;
        boolean equal = true;
        final Stack<Integer> aux = new Stack<>();
        while (equal && !s1.isEmpty()) {
            final Integer n1 = s1.pop();
            final Integer n2 = s2.pop();
            if (!n1.equals(n2))
                equal = false;
            aux.push(n1);
            aux.push(n2);
        }
        while (!aux.isEmpty()) {
            s2.push(aux.pop());
            s1.push(aux.pop());
        }
        return equal;
    }

    @Exercise(10)
    public static boolean isConsecutive(final Stack<Integer> stack) {
        if (stack.isEmpty())
            return true;
        final Queue<Integer> aux = new LinkedList<>();
        while (!stack.isEmpty())
            aux.add(stack.pop());
        while (!aux.isEmpty())
            stack.push(aux.remove());
        boolean r = true;
        Integer prev = stack.pop();
        aux.add(prev);
        while (!stack.isEmpty()) {
            final Integer next = stack.pop();
            aux.add(next);
            if (prev != next - 1)
                r = false;
            prev = next;
        }
        while (!aux.isEmpty())
            stack.push(aux.remove());
        return r;

    }

    @Exercise(15)
    public static boolean isSorted(final Stack<Integer> s) {
        if (s.isEmpty())
            return true;
        final Stack<Integer> aux = new Stack<>();
        Integer prev = s.pop();
        aux.push(prev);
        boolean r = true;
        while (!s.isEmpty()) {
            final Integer next = s.pop();
            if (prev > next) {
                r = false;
                s.push(next);
                break;
            }
            else {
                aux.push(next);
                prev = next;
            }
        }
        while (!aux.isEmpty())
            s.push(aux.pop());
        return r;
    }

    @Exercise(16)
    public static void mirror(final Stack<Integer> s) {
        final Queue<Integer> aux = new LinkedList<>();
        final int size = s.size();
        while (!s.isEmpty())
            aux.add(s.pop());
        for (int i = 0; i < size; i++) {
            final Integer e = aux.remove();
            s.push(e);
            aux.add(e);
        }
        while (!s.isEmpty())
            aux.add(s.pop());
        for (int i = 0; i < size; i++)
            aux.add(aux.remove());
        while (!aux.isEmpty())
            s.push(aux.remove());
    }

    @Exercise(18)
    public static void mirrorHalves(final Queue<Integer> q) {
        if (q.size() % 2 != 0)
            throw new IllegalArgumentException();
        final int halfSize = q.size() / 2;
        mirrorHalf(q, halfSize);
        mirrorHalf(q, halfSize);
    }

    private static void mirrorHalf(final Queue<Integer> q, final int halfSize) {
        final Stack<Integer> aux = new Stack<>();
        for (int i = 0; i < halfSize; i++) {
            final Integer next = q.remove();
            q.add(next);
            aux.add(next);
        }
        while (!aux.isEmpty())
            q.add(aux.pop());
    }

    @Exercise(20)
    public static void interleave(final Queue<Integer> q) {
        if (q.size() % 2 != 0)
            throw new IllegalArgumentException();
        final Stack<Integer> aux = new Stack<>();
        final int size = q.size() / 2;
        for (int i = 0; i < size; i++)
            aux.push(q.remove());
        while (!aux.isEmpty())
            q.add(aux.pop());
        for (int i = 0; i < size; i++)
            q.add(q.remove());
        for (int i = 0; i < size; i++)
            aux.push(q.remove());
        for (int i = 0; i < size; i++) {
            q.add(aux.pop());
            q.add(q.remove());
        }
    }
}
