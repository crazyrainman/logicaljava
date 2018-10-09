package carman.arrays;

public class UpMedian {
    public int getUpMedian(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null || arr1.length != arr2.length) {
            throw new RuntimeException("Your arr is invalid!");
        }
        int start1 = 0;
        int end1 = arr1.length - 1;
        int start2 = 0;
        int end2 = arr2.length - 1;
        int mid1 = 0;
        int mid2 = 0;
        int offset = 0;
        while(start1 < end1) {
            mid1 = (start1 + end1) / 2;
            mid2 = (start2 + end2) / 2;
            // 元素个数为奇数， 则offset为0， 元素个数为偶数， 则offset为1
            offset = ((end1 - start1 + 1) & 1) ^ 1;
            if (arr1[mid1] > arr2[mid2]) {
                end1 = mid1;
                start2 = mid2 + offset;
            } else if (arr1[mid1] < arr2[mid2]) {
                start1 = mid1 + offset;
                end2 = mid2;
            } else {
                return arr1[mid1];
            }
        }
        return Math.min(arr1[start1], arr2[start2]);
    }
    // a1[s1, e1] , a2[s2, e2]
    public int getUpMedian(int[] a1, int s1, int e1, int[] a2, int s2, int e2) {
        int mid1 = 0;
        int mid2 = 0;
        int offset = 0;
        while (s1 < e1) {
            mid1 = (s1 + e1) / 2;
            mid2 = (s2 + e2) / 2;
            offset = ((e1 - s1 + 1) & 1) ^ 1;
            if (a1[mid1] > a2[mid2]) {
                e1 = mid1;
                s2 = mid2 + offset;
            } else if (a1[mid1] < a2[mid2]) {
                s1 = mid1 + offset;
                e2 = mid2;
            } else {
                return a1[mid1];
            }
        }
        return Math.min(a1[s1], a2[s2]);
    }

    public int findKthNum(int[] arr1, int[] arr2, int kth) {
        if (arr1 == null || arr2 == null) {
            throw new RuntimeException("Your arr is invalid");
        }
        if (kth < 1 || kth > arr1.length + arr2.length) {
            throw new RuntimeException("K is invalid!");
        }
        int[] longs = arr1.length >= arr2.length ? arr1 : arr2;
        int[] shorts = arr1.length < arr2.length ? arr1 : arr2;
        int l = longs.length;
        int s = shorts.length;
        if (kth <= s) { // s[0..kth - 1], l[0..kth-1] 
            return getUpMedian(shorts, 0, kth - 1, longs, 0, kth - 1);
        }
        if (kth > l) {
            // kth - l - 1 + 1 + l - 1 + 1 = kth
            if (shorts[kth - l -1] >= longs[l - 1]) {
                return shorts[kth - l - 1];
            }
            // kth - s - 1 + s - l + 1 = kth
            if (longs[kth - s - 1] >= shorts[s - 1]) {
                return longs[kth - s - 1];
            }
            // s[kth - 1, s -1], l[kth -s, l - 1]
            return getUpMedian(shorts, kth - l, s - 1, longs, kth - s, l - 1);
        }
        // s.len < kth <= l.len
        if (longs[kth - s - 1] >= shorts[s - 1]) {
            return longs[kth - s - 1];
        }
        return getUpMedian(shorts, 0, s - 1, longs, kth - s, kth - 1);
    }
}