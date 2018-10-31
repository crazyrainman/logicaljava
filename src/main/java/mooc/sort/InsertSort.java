package mooc.sort;

import java.util.Arrays;

public class InsertSort {
    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for(int i = 1; i < arr.length; i++) {
            int e = arr[i];
            int j;
            for(j = i; j > 0; j--) {
                if (arr[j - 1] > e) {
                    arr[j] = arr[j - 1];
                } else { // arr[j - 1] <= e
                    break;
                }
            }
            arr[j] = e;
        }
    }
    static int[] genRandom(int max, int maxLen) {
        int len = (int)(Math.random() * maxLen) + 1;
        int[] arr = new int[len];
        for(int i = 0; i < len; i++) {
            arr[i] = (int)(Math.random() * max);
        }
        return arr;
    }
    static int[] copyOfArr(int[] arr) {
        int[] copy = new int[arr.length];
        for(int i = 0; i < arr.length; i++) {
            copy[i] = arr[i];
        }
        return copy;
    }
    public static void main(String[] args) {
        int[] arr = genRandom(200, 20);
        int[] cp = copyOfArr(arr);
        sort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        Arrays.sort(cp);
        for (int i : cp) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}