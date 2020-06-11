package liam.chapter9.incrementers;

public class SequentialIncrementer implements Incrementable {
    private int value = 0;

    public int getValue () {
        return value;
    }
    public void increment () {
        value++;
    }
}
