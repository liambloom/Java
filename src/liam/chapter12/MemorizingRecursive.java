package liam.chapter12;

import java.util.function.Function;
import java.util.function.BiFunction;
import java.util.HashMap;
import java.util.Set;
import java.util.Arrays;

// At some point I would like to add support for some form of ArrayMap, to allow exercise20 to use this
public class MemorizingRecursive<K, V> implements Function<K, V> {
    private final HashMap<K, V> memory;
    private final BiFunction<MemorizingRecursive<K, V>, K, V> f;

    public MemorizingRecursive(BiFunction<MemorizingRecursive<K, V>, K, V> f) {
        this.f = f;
        this.memory = new HashMap<>();
    }
    
    public MemorizingRecursive(BiFunction<MemorizingRecursive<K, V>, K, V> f, HashMap<K, V> initial) {
        this.f = f;
        this.memory = initial;
    }

    public V apply(K i) {
        if (!i.getClass().isPrimitive()) {
            final Set<K> keys = memory.keySet();
            for (K e : keys) {
                if (i.getClass().isArray() ? Arrays.equals((Object[]) i, (Object[]) e) : i.equals(e)) {
                    return memory.get(e);
                }
            }
        }
        else if (memory.containsKey(i)) return memory.get(i);
        final V r = f.apply(this, i);
        memory.put(i, r);
        return r;
    }
}