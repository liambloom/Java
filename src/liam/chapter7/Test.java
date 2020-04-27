package liam.chapter7;

import java.util.Arrays;
import java.util.regex.Pattern;

public class Test {
    public static Object def = new Object() {
      public final int foo = 2;
    };
    public static void main (String[] args) {
        //ArrayCaster.cast(new Object[]{}, Integer.valueOf(2));
        //System.out.println(int[][][].class.getComponentType().getSimpleName());
        //System.out.println(Arrays.deepToString(ArrayCaster.cast(new Object[][][]{{{1, 2}, {3, 4}}}, Integer[][].class)));
        /*Object[][] o = new Object[][]{{1, 2}, {3, 4}};
        Integer[][] n = new Integer[o.length][];
        for (int i = 0; i < o.length; i++) n[i] = Arrays.copyOf(o[i], o[i].length, Integer[].class);
        System.out.println(Arrays.deepToString(n));*/
        /*int bar = (int) Integer.valueOf(1);
        int foo = ArrayCaster.cast(Integer.valueOf(2), int.class);
        //int[] f = Arrays.copyOf(new Object[]{2, 3}, 2, int[].class);
        /*int depth = 0;
        final String name = int[][].class.getName();
        for (depth = 0; depth < name.length(); depth++) {
            if (name.charAt(depth) != '[') break;
        }
        System.out.println(depth);*/
        /*Integer[] x = ArrayCaster.cast(new Object[]{}, int[].class);
        System.out.println(1);
        ArrayCaster.cast(new Object[]{}, Integer.class);
        System.out.println(2);
        //System.out.println(Integer[].class.);
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
        Integer.valueOf(2).getClass();
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
