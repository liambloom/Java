package dev.liambloom.softwareEngineering.chapter10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.function.*;

public class ArrayList$ <E> { // This does not extend ArrayList because it needs to be able to modify the original
    public final ArrayList<E> a;

    public ArrayList$ () {
        a = new ArrayList<>();
    }
    public ArrayList$ (ArrayList<E> a) {
        this.a = a;
    }

    public <R> void iterate (Consumer<Function<E, R>> function, BiFunction<E, Integer, R> callback) {
        final ListIterator<E> iterator = a.listIterator();
        function.accept(t -> {
            final R r = callback.apply(t, iterator.nextIndex());
            iterator.next();
            return r;
        });
    }
    public void iterate (Consumer<Consumer<E>> consumer, BiConsumer<E, Integer> callback) {
        iterate((Consumer<Function<E, Void>>) t -> consumer.accept(t::apply), (t, i) -> {
            callback.accept(t, i);
            return null;
        });
        /*
        This is confusing, so here is the longer version
        iterateFunction(list, t -> { // This second argument must consume a Function<T, R>. t is a Function<T, R>
            consumer.accept(u -> { // consumer accepts T, u is type T
                t.apply(u); // Function<T, R> t accepts T u and nothing is done with the return value
            });
        }, (t, i) -> { // The third argument must accept a T and and Integer, and return R
            callback.accept(t, i); // Runs the callback with T t and Integer i
            return null; // Returns something
        });
        */
    }
    public void iterate (Consumer<Predicate<E>> function, BiPredicate<E, Integer> callback) {
        iterate((Consumer<Function<E, Boolean>>) t -> function.accept(t::apply), callback::test);
        /*
        This one liner is confusing, so here is a longer version that explains itself
        iterateFunction(list, t -> { // The second argument must consume a Function<T, R>. t is a Function<T, R>
            function.accept(u -> { // function consumes a Predicate. This lambda is a predicate. u is of type T
                return t.apply(u); // pass T u into Function<T, R> t and return type R, which is equal to Boolean
            });
        }, callback);
        */
    }
    public void forEachReverse (Consumer<E> callback) {
        forEachReverse((e, _i) -> callback.accept(e));
    }
    public void forEachReverse (BiConsumer<E, Integer> callback) {
        for (int i = a.size() - 1; i >= 0; i--) {
            callback.accept(a.get(i), i);
        }
    }
    public void reverseSetsOf (int length) { // Exercises 2 and 13
        for (int i = 0; i < a.size() - (length - 1); i += length) {
            List<E> sub = a.subList(i, i + length);
            Collections.reverse(sub);
            for (int j = 0; j < sub.size() - 1; j++) {
                a.set(i + j, sub.get(j));
            }
        }
    }

    public String toString () {
        return a.toString();
    }

    @SuppressWarnings("unchecked")
    public ArrayList$<E> clone () {
        return new ArrayList$<>((ArrayList<E>) a.clone());
    }

    @SuppressWarnings("rawtypes")
    public boolean equals (Object o) {
        return o instanceof ArrayList$ && a.equals(((ArrayList$) o).a);
    }
}
