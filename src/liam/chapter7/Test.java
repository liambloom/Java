package liam.chapter7;

public class Test {
    public static void main (String[] args) {
        int[] ints = {1, 2};
        incrementAll(ints);
        System.out.println(Main.arrayToString(ints));
    }
    public static void incrementAll (int[] ints) {
        for (int i = 0; i < ints.length; i++) ints[i]++;
    }
}
