package liam.chapter9.incrementers;

import java.util.Random;

public class RandomIncrementer implements Incrementable {
    private Random r = new Random();
    private int value = r.nextInt();

    public int getValue () {
        return value;
    }
    public void increment () {
        value = r.nextInt();
    }
}
