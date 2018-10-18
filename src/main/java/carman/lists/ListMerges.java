package carman.lists;

public class ListMerges {
    public void relocate(Node head) {
        if (head == null || head.next == null) {
            return;
        }
        Node mid = head;
        Node right = head.next; // step one
        // two in one step
        while (right.next != null && right.next.next != null) {
            mid = mid.next;
            right = right.next.next;
        }
        right = mid.next;
        mid.next = null;
        mergeLR(head, right);
    }

    public void mergeLR(Node left, Node right) {
        Node next = null;
        while(left.next != null) {
            // backup pointer
            next = right.next;
            // change points
            right.next = left.next;
            left.next = right;
            // forward
            left = right.next;
            right = next;
        }
        left.next = right;
    }
}