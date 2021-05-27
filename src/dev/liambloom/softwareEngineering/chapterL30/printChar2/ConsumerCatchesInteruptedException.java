package dev.liambloom.softwareEngineering.chapterL30.printChar2;

import java.util.function.Consumer;

@FunctionalInterface
public interface ConsumerCatchesInteruptedException<T> extends Consumer<T> {
    @Override
    default void accept(T t) {
        try {
            acceptThrowing(t);
        }
        catch(InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    void acceptThrowing(T t) throws InterruptedException;
}
