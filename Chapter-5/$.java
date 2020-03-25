public class $ {
  public static int min (int ...nums) {
    int min = nums[0];
    for (int i = 1; i < nums.length; i++) {
      min = Math.min(min, nums[i]);
    }
    return min;
  }
  public static double min (double ...nums) {
    double min = nums[0];
    for (int i = 1; i < nums.length; i++) {
      min = Math.min(min, nums[i]);
    }
    return min;
  }
  public static int max (int ...nums) {
    int max = nums[0];
    for (int i = 1; i < nums.length; i++) {
      max = Math.max(max, nums[i]);
    }
    return max;
  }
  public static double max (double ...nums) {
    double max = nums[0];
    for (int i = 1; i < nums.length; i++) {
      max = Math.max(max, nums[i]);
    }
    return max;
  }
  public static boolean between (int min, int max, int c) {
    return min < c && c < max;
  }
  public static double logBase (int base, int val) {
    return Math.log(val) / Math.log(base);
  }
  public static double log2 (int val) {
    return logBase(2, val);
  }
}