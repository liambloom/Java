package chapter7;

public class Main {
    public static void main (String[] args) {
        System.out.println("bar");
    }
    public static String arrayToString (byte[] arr) {
        String str = "";
        for (byte i : arr) str += ", " + i;
        return "[" + str.substring(2) + "]";
    }
    public static String arrayToString (short[] arr) {
        String str = "";
        for (short i : arr) str += ", " + i;
        return "[" + str.substring(2) + "]";
    }
    public static String arrayToString (int[] arr) {
        String str = "";
        for (int i : arr) str += ", " + i;
        return "[" + str.substring(2) + "]";
    }
    public static String arrayToString (long[] arr) {
        String str = "";
        for (long i : arr) str += ", " + i;
        return "[" + str.substring(2) + "]";
    }
    public static String arrayToString (float[] arr) {
        String str = "";
        for (float i : arr) str += ", " + i;
        return "[" + str.substring(2) + "]";
    }
    public static String arrayToString (double[] arr) {
        String str = "";
        for (double i : arr) str += ", " + i;
        return "[" + str.substring(2) + "]";
    }
    public static String arrayToString (char[] arr) {
        String str = "";
        for (char i : arr) str += ", " + i;
        return "[" + str.substring(2) + "]";
    }
    public static String arrayToString (boolean[] arr) {
        String str = "";
        for (boolean i : arr) str += ", " + i;
        return "[" + str.substring(2) + "]";
    }
    public static <T> String arrayToString (T[] arr) { // Why can't generics be primitives :(
        String str = "";
        for (T i : arr) str += ", " + i;
        return "[" + str.substring(2) + "]";
    }
}