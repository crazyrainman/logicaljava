package carman.arrays;

public class ShortestUnorder {
    public int getMinLength(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int min = arr[arr.length -1];
        int noMinIndex = -1;
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > min) {
                noMinIndex = 1;
            } else {
                min = Math.min(min, arr[i]);
            }
        }
        if (noMinIndex == -1) {
            return 0;
        }
        int max = arr[0];
        int noMaxIndex = -1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < max) {
                noMaxIndex = i;
            } else {
                max = Math.max(max, arr[i]);
            }
        }
        return noMaxIndex - noMinIndex + 1;
    }
}