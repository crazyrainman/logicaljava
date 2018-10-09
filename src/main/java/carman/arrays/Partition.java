package carman.arrays;
public class Partition {
    void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    public void leftUnique(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // [0..u], [u+1, len)
        int u = 0;
        int i = 1;
        while(i != arr.length) {
            if (arr[i++] != arr[u]) {
                swap(arr, ++u, i - 1);
            }
        }
    }
    public void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // [0..left), [left , index), [right...len - 1]
        int left = 0;
        int index = 0;
        int right = arr.length;
        while(index < right) {
            if (arr[index] == 0) {
                swap(arr, left++, index++);
            } else if (arr[index] == 2) {
                swap(arr, index, --right);
            } else {
                index++;
            }
        }
    }
    public static void main(String[] args) {
        int[] arr = new int[]{2, 1, 0, 1, 2, 2};
        new Partition().sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}