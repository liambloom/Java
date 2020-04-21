package liam.chapter7;

//import liam.chapter4.Ask;
import java.util.Arrays;

public class SelfChecks { // These were created to check my answers, but I did the problem before writing this.
    public static void main(String[] args) {
        //System.out.println(Arrays.toString(sc4(Ask.forInt("min"), Ask.forInt("max"))));
        sc14();
    }
    public static void sc3 () {
        int[] data = /*new int[]*/{2};
    }
    public static int[] sc4 (int min, int max) {
        int[] r = new int[max - min];
        for (int i = 0; i < r.length; i++) r[i] = i + min;
        return r;
    }
    public static void sc5 () {
        int[] data = new int[8];
        data[0] = 3;
        data[7] = -18;
        data[4] = 5;
        data[1] = data[0];

        int x = data[4];
        data[4] = 6;
        data[x] = data[0] * data[1];

        System.out.println(Arrays.toString(data));
    }
    public static void sc14 () {
        int[] list = {2, 18, 6, -4, 5, 1};
        for (int i = 0; i < list.length; i++) {
            list[i] = list[i] + (list[i] / list[0]);
        }
        System.out.println(Arrays.toString(list));
    }
}
