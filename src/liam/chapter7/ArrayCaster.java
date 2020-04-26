package liam.chapter7;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.regex.Pattern;

public final class ArrayCaster {
    public static <T> T[][] cast (Object[] from, Class<T[]> to) {
        if (to.getComponentType().isArray()) {
            return castDeeper((Object[][]) from, (T[]) to);
        }
        else {
            T[][] n = (T[][]) new Object[from.length][];
            for (int i = 0; i < from.length; i++) n[i] = Arrays.copyOf((Object[]) from[i], ((Object[]) from[i]).length, to);
            return n;
        }
        //to.arrayType();
        //return new to();
    }
    public static <T> T[][][] castDeeper (Object[][] from, Class<T[][]> to) {
        Object[] o = new Object[from.length];
        for (int i = 0; i < from.length; i++) o[i] = cast((Object[]) from[i], (Class<T[]>) to.getComponentType());
        return (T[][][]) o;
    }
    /*private static <T> T innerCast (Object[] from, Class<T> to) {

        if (to.getComponentType().isArray()) {
            T[] arr = (T[]) new Object[from.length];
            for (int i = 0; i < from.length; i++) {
                arr[i] = (T) cast((Object[]) from[i], to.getComponentType());
            }
        }
        else
    }*/
    /*private static int depth (Class c) {
        int depth;
        final String name = c.getName();
        for (depth = 0; depth < name.length(); depth++) {
            if (name.charAt(depth) != '[') break;
        }
        return depth;
    }*/
}
