package carman.trees;

public class MaxDistance {
    public int maxDistance(Node head) {
        int[] record = new int[1];
        return posOrder(head, record);
    }
    public int posOrder(Node head, int[] record) {
        if (head == null) {
            record[0] = 0;
            return 0;
        }
        int lMax = posOrder(head.left, record);
        int maxfromLeft = record[0];
        int rMax = posOrder(head.right, record);
        int maxfromRight = record[0];
        int curNodeMax = maxfromLeft + maxfromRight + 1;
        record[0] = Math.max(maxfromLeft, maxfromRight) + 1;
        return Math.max(Math.max(lMax, rMax), curNodeMax);
    }
}