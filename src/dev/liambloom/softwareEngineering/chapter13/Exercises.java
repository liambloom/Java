package dev.liambloom.softwareEngineering.chapter13;

// 4, 5, 6, 7, 11, 18, 19
public class Exercises {
    public static void main(String[] args) {
        final Integer[] a = {8, 5, -9, 14, 0, -1, -7, 3};
        exercise19(a);
        System.out.println(java.util.Arrays.toString(a));
    }
    
    /*
     * 4. O(n), there is one loop and each element is only looped over once
     * 5. O(n), there is one loop, and while it doesn't go over every element
     *      in the list, as n -> infinity, the 2 matters less and less, and
     *      n/2 -> n
     * 6. O(n), Same reason as #4
     * 7. O(n), Same reason an #4
     * 11.  {8, 5, -9, 14, 0, -1, -7, 3}
     *      {8, 5, -9, 14}, {0, -1, -7, 3}
     *      {8, 5}, {-9, 14}, {0, -1}, {-7, 3}
     *      {8}, {5}, {-9}, {14}, {0}, {-1}, {-7}, {3}
     *      {5, 8}, {-9, 14}, {-1, 0}, {-7, 3}
     *      {-9, 5, 8, 14}, {-7, -1, 0, 3}
     *      {-9, -7, -1, 0, 3, 5, 8, 14}
     * 
     *      {15, 56, 24, 5, 39, -4, 27, 10}
     *      {15, 56, 24, 5}, {39, -4, 27, 10}
     *      {15, 56}, {24, 5}, {39, -4}, {27, 10}
     *      {15}, {56}, {24}, {5}, {39}, {-4}, {27}, {10}
     *      {15, 56}, {5, 24}, {-4, 39}, {10, 27}
     *      {5, 15, 24, 56}, {-4, 10, 27, 39}
     *      {-4, 5, 10, 15, 24, 27, 39, 56}
     */ 

    /*
     * This will not be faster than normal selection sort, it will be the same
     * efficiency. It will still be slower than merge sort, and will have a 
     * complexity of O(n^2).
     */
    public static <T extends Comparable<T>> void exercise18(final T[] a) {
        for (int i = a.length - 1; i >= 0; i--) {
            int max = i;
            for (int j = 0; j < i; j++) {
                if (a[j].compareTo(a[max]) > 0)
                    max = j;
            }
            final T temp = a[max];
            a[max] = a[i];
            a[i] = temp;
        }
    }

    /*
     * This will be faster than normal selection sort, in fact, twice as fast,
     * but it will still be slower than merge sort and still have a complexity
     * of O(n^2).
     */
    public static <T extends Comparable<T>> void exercise19(final T[] a) {
        for (int i = 0; i < a.length / 2; i++) {
            int min = i;
            int max = i;
            for (int j = i; j < a.length - i; j++) {
                if (a[j].compareTo(a[min]) < 0)
                    min = j;
                else if (a[j].compareTo(a[max]) > 0)
                    max = j;
            }
            if (min != i) {
                final T minTemp = a[min];
                a[min] = a[i];
                a[i] = minTemp;
            }
            if (max == i)
                max = min;
            final T maxTemp = a[max];
            a[max] = a[a.length - i - 1];
            a[a.length - i - 1] = maxTemp;
        }
    }
}
