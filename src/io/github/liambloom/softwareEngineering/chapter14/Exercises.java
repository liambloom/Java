package io.github.liambloom.softwareEngineering.chapter14;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;

import io.github.liambloom.tests.Tester;
import io.github.liambloom.tests.book.bjp3.*;

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
                });
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

    @Exercise(2)
    public static void stutter(final Stack<Integer> stack) {
        final Queue<Integer> aux = new LinkedList<>();

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
}
