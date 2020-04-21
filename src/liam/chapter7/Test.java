package liam.chapter7;

import java.util.Arrays;

public class Test {
    public static void main (String[] args) {
        int[] ints = {1, 2};
        incrementAll(ints);
        System.out.println(Arrays.toString(ints));
    }
    public static void incrementAll (int[] ints) {
        for (int i = 0; i < ints.length; i++) ints[i]++;
    }

}