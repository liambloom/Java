package liam;

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
    }
    public static String repeat (String str, int repititions) {
        return liam.chapter4.Exercises.exercise2(str, repititions);
    }
}
