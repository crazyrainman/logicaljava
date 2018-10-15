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
}