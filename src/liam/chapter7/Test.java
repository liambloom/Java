package liam.chapter7;

import java.util.Arrays;

public class Test {
    public static void main (String[] args) {
        //sortTest();
        //System.out.println(Arrays.toString(Arrays.copyOf(new int[]{1, 2}, 2)));
        /*int[] ints = {1, 2};
        incrementAll(ints);
        System.out.println(Arrays.toString(ints));*/
        while (true) System.out.println();
    }
    public static void incrementAll (int[] ints) {
        for (int i : ints) i++;
    }
    public static void sortTest () {
        int[] arr = {1, 3, 2};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
