package carman.lists;

public class AddList {
    // TODO 
    public Node reverseNode(Node head) {
        Node done = null;
        while(head != null) {
            Node next = head.next;
            head.next = done; // 翻转指针 , TODO: 链表指针问题
            done = head;
            head = next;
        }
        return done;
    }
}