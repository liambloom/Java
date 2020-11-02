package io.github.liambloom.softwareEngineering.chapter13;

public class BucketSort {
    public static void bucketSort(final int[] a) {
        final int[][] buckets = new int[10][a.length];
        boolean sorted = false;
        for (int i = 1; !sorted; i *= 10) {
            sorted = true;
            for (int j = 0; j < a.length; j++) {
                final int[] bucket = buckets[a[j] / i % 10];
                if (a[j] / i != 0)
                    sorted = false;
                int k;
                for (k = 0; bucket[k] != 0; k++);
                bucket[k] = a[j];
            }
            int l = 0;
            for (int j = 0; j < 10 /* buckets.length */; j++) {
                for (int k = 0; k < a.length && buckets[j][k] != 0; k++) {
                    a[l++] = buckets[j][k];
                    buckets[j][k] = 0;
                }
            }
        }
    }
}
