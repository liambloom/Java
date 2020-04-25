package liam.chapter7;

import java.util.Arrays;

public class Test {
    public static void main (String[] args) {
        new Object[]{2, new Object[]{3, 4}, "hi"};
        System.out.println(new Integer[][]{{2}}[0].getClass() == Integer.class.arrayType());
        //int.class;
        //System.out.println(Ask.STRING_REGEX.matcher("\"foo\\\\\"").matches());
        //System.out.println(boolean.class.isPrimitive());
        //sortTest();
        //System.out.println(Arrays.toString(Arrays.copyOf(new int[]{1, 2}, 2)));
        /*int[] ints = {1, 2};
        incrementAll(ints);
        System.out.println(Arrays.toString(ints));*/
        //while (true) System.out.println();
    }
    public static <T> void classTest (Class<T> c) {
        new Integer(2).getClass();
        //System.out.println(T.)
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
