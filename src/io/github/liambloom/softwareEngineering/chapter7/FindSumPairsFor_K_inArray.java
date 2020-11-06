package io.github.liambloom.softwareEngineering.chapter7;

public class FindSumPairsFor_K_inArray {
    public static void main(String[] args) {
        int[] a = {9, 1, 3, 22, 76, 15, 23, 7, 10, 8, 15, 4, 20, 5, 12, 2, 18};
        findSumPairsFor_K_inArray(16, a);
        findSumPairsFor_K_inArray(30, a);
        findSumPairsFor_K_inArray(50, a);
    }

    public static void findSumPairsFor_K_inArray(int k, int[] a) {
        System.out.printf("K = %-8dPairs = ", k);
        int pairCount = 0;
        outer: for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < i; j++) {
                if (a[i] == a[j]) {
                    System.out.println(a[i] + " == " + a[j]);
                    continue outer;
                }
            }
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] + a[j] == k) {
                    if (pairCount++ > 0)
                        System.out.print(", ");
                    System.out.printf("(%d, %d)", a[i], a[j]);
                }
            }
        }
        if (pairCount == 0)
            System.out.print("None");
        System.out.println();
    }
}
