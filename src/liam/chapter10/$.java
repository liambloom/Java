package liam.chapter10;

import java.util.ArrayList;
    public static <T> void forEachReverse (ArrayList<T> a, Consumer<T> callback) {
        forEachReverse(a, (e, _i) -> callback.accept(e));
    }
    public static <T> void forEachReverse (ArrayList<T> a, BiConsumer<T, Integer> callback) {
        for (int i = a.size() - 1; i >= 0; i--) {
            callback.accept(a.get(i), i);
        }
    }
}
