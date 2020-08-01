package liam.chapter12;

import java.util.function.Function;
import java.util.function.BiFunction;
import java.util.HashMap;

public class RecursiveWMemory<K, V> implements Function<K, V> {
    private final HashMap<K, V> memory;
    private final BiFunction<RecursiveWMemory<K, V>, K, V> f;

    public RecursiveWMemory(BiFunction<RecursiveWMemory<K, V>, K, V> f) {
        this.f = f;
        this.memory = new HashMap<>();
    }
    
    public RecursiveWMemory(BiFunction<RecursiveWMemory<K, V>, K, V> f, HashMap<K, V> initial) {
        this.f = f;
        this.memory = initial;
    }

    public V apply(K i) {
        if (memory.containsKey(i)) return memory.get(i);
        final V r = f.apply(this, i);
        memory.put(i, r);
        return r;
    }
}