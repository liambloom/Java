package io.github.liambloom.softwareEngineering;
import java.util.Random;

public final class Globals {
    public static class Math {
        public static final double Infinity = 1.0 / 0.0;
        public static int min (int ...nums) {
            int min = nums[0];
            for (int i = 1; i < nums.length; i++) {
                min = java.lang.Math.min(min, nums[i]);
            }
            return min;
        }
        public static double min (double ...nums) {
            double min = nums[0];
            for (int i = 1; i < nums.length; i++) {
                min = java.lang.Math.min(min, nums[i]);
            }
            return min;
        }
        public static int max (int ...nums) {
            int max = nums[0];
            for (int i = 1; i < nums.length; i++) {
                max = java.lang.Math.max(max, nums[i]);
            }
            return max;
        }
        public static double max (double ...nums) {
            double max = nums[0];
            for (int i = 1; i < nums.length; i++) {
                max = java.lang.Math.max(max, nums[i]);
            }
            return max;
        }

        public static int gcd(int a, int b) {
            return b == 0 ? a : gcd(b, a % b);
        }

        public static int lcm(int a, int b) {
            return a * (b / gcd(a, b));
        }

        public static void main(String[] args) {
            Random r = new Random();

            for (int i = 0; i < 1000; i++) {
                int[] ns = new int[2];
                for (int j = 0; j < 2; j++)
                    while ((ns[j] = r.nextInt(100) - 50) == 0);

                final int lcm = lcm(ns[0], ns[1]);

                System.out.printf("%d. %d, %d...", i, ns[0], ns[1]);
                if (lcm % ns[0] == 0 && lcm % ns[1] == 0)
                    System.out.println("\u001b[32mok\u001b[0m");
                else {
                    System.out.println("\u001b[31mfail\u001b[0m");
                    System.exit(1);
                }
            }
        }
    }
    public static String repeat (String str, int repetitions) {
        return io.github.liambloom.softwareEngineering.chapter4.Exercises.exercise2(str, repetitions);
    }
    public static String repeat (char c, int repetitions) {
        return repeat("" + c, repetitions);
    }
}
