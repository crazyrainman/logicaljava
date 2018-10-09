package carman.arrays;

public class SmallSum {
    public int getSmallSum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return func(arr, 0, arr.length - 1);
    }
    public int func(int[] s, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = (l + r) / 2;
        return func(s, l, mid) + func(s, mid + 1, r) + merge(s, l, mid, r);
    }
    public int merge(int[] s, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int tempi = 0;
        int i = left;
        int j = mid + 1;
        int smallsum = 0;
        while(i <= mid && j <= right) {
            if (s[i] <= s[j]) {
                smallsum += s[i] * (right - j + 1);
                temp[tempi++] = s[i++];
            } else {
                temp[tempi++] = s[j++];
            }
        }
        // copy remainder j..righ or i..mid
        for (; (j < right + 1) || (i < mid + 1); j++, i++) {
            temp[tempi++] = i > mid ? s[j] : s[i];
        }
        for (int k = 0; k < temp.length; k++) {
            s[left++] = temp[k];
        }
        return smallsum;
    }
}