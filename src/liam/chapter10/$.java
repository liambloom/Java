package liam.chapter10;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.function.*;

public class $ {
    public static <T, R> void iterateFunction (ListIterator<T> iterator, Consumer<Function<T, R>> function, BiFunction<T, Integer, R> callback) {
        function.accept(t -> {
            final R r = callback.apply(t, iterator.nextIndex());
            iterator.next();
            return r;
        });
    }
    public static <T> void iterateConsumer (ListIterator<T> iterator, Consumer<Consumer<T>> consumer, BiConsumer<T, Integer> callback) {
        iterateFunction(iterator, t -> consumer.accept(t::apply), (t, i) -> {
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
    public static <T> void iteratePredicate (ListIterator<T> iterator, Consumer<Predicate<T>> function, BiFunction<T, Integer, Boolean> callback) {
        iterateFunction(iterator, t -> function.accept(t::apply), callback);
        /*
        This one liner is confusing, so here is a longer version that explains itself
        iterateFunction(list, t -> { // The second argument must consume a Function<T, R>. t is a Function<T, R>
            function.accept(u -> { // function consumes a Predicate. This lambda is a predicate. u is of type T
                return t.apply(u); // pass T u into Function<T, R> t and return type R, which is equal to Boolean
            });
        }, callback);
        */
    }
    public static <T> void forEachReverse (ArrayList<T> a, Consumer<T> callback) {
        forEachReverse(a, (e, _i) -> callback.accept(e));
    }
    public static <T> void forEachReverse (ArrayList<T> a, BiConsumer<T, Integer> callback) {
        for (int i = a.size() - 1; i >= 0; i--) {
            callback.accept(a.get(i), i);
        }
    }
}
