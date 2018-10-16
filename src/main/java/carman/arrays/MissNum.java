package carman.arrays;
public class MissNum {
    public int missNum(int[] arr) {
        int l = 0;
        int r = arr.length;
        while(l < r) {
            if (arr[l] == l + 1) {
                l++;
            } else if (arr[l] <= l || arr[l] > r || arr[arr[l] - 1] == arr[l]) {
                arr[l] = arr[--r];
            } else {
                swap(arr, l, arr[l] - 1);
            }
        }
        return l + 1;
    }
    void swap(int[] arr, int i, int j) {
        int tmp = arr[j];
        arr[j] = arr[i];
        arr[i] = tmp;
    }
    public int missNum2(int[] arr) {
        int l = 0;
        int r = arr.length; // [1, l + 1) , [l+1, r]
        int i = 0, j = arr.length;
        while(i < j) {
            if (arr[i] == l + 1) { // 恰好是需要的l + 1
                l++; i++;
            }
            else if (arr[i] <= l) { // in [1, l+1), 重复值 [1, r] -> [1, r-1]
                arr[i] = arr[--j]; --r;
            } else if (arr[i] > r) { // 完全脱离最大可能范围
                arr[i] = arr[--j]; --r;
            } else if (arr[i] == arr[arr[i] - 1]) { // 在[l+1, r]内，但是是重复值, r->r-1. j 缩小范围
                arr[i] = arr[--j]; --r;
            } else { // [l+1, r] 但不一定在正确位置上
                int tmp = arr[i];
                arr[i] = arr[arr[i] - 1];
                arr[arr[i] - 1] = tmp;
            }
        }
        return l + 1;
    }
}