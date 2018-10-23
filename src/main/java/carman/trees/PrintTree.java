package carman.trees;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class PrintTree {
    public void printTree(Node head) {
        System.out.println("Binary Tree: ");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        String preStr = "2!3!#!#!4!#!#!";
        Node head = new SerialDeSerial().reconByPreString(preStr);
        new PrintTree().printTree(head);
        System.out.println(new SerialDeSerial().serialByPre(head));
    }
    void printByLevel(Node head) {
        if (head == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        int level = 1;
        Node last = head; // head 队头 
        Node nLast = null; 
        queue.offer(head); //  {1}
        System.out.print("Level " + (level++) + " : ");
        while(!queue.isEmpty()) {
            head = queue.poll(); 
            System.out.print(head.value + " ");
            if (head.left != null) {
                queue.offer(head.left); 
                nLast = head.left;
            }
            if (head.right != null) {
                queue.offer(head.right);
                nLast = head.right;
            } // 
            if (head == last && !queue.isEmpty()) { 
                System.out.print("\nLevel " + (level++) + " : ");
                last = nLast;
            }
        }
        System.out.println();
    }

    void printByZigZig(Node head) {
        if (head == null) {
            return;
        }
        
        Deque<Node> dq = new LinkedList<>();
        int level = 1; 
        boolean lr = true;
        Node last = head; 
        Node nLast = null; 
        dq.offerFirst(head); 

        printLevelAndOrientation(level++, lr);
        while (!dq.isEmpty()) {
            if (lr) { 
                head = dq.pollFirst();  
                if (head.left != null) {
                    nLast = nLast == null ? head.left : nLast;
                    dq.offerLast(head.left);
                }
                if (head.right != null) {
                    nLast = nLast == null ? head.right : nLast;
                    dq.offerLast(head.right);
                }
            } else {
                head = dq.pollLast();
                if (head.right != null) {
                    nLast = nLast == null ? head.right : nLast;
                    dq.offerFirst(head.right);
                }
                if (head.left != null) {
                    nLast = nLast == null ? head.left : nLast;
                    dq.offerFirst(head.left);
                }
            }
            System.out.print(head.value + " ");
            if (head == last && !dq.isEmpty()) {
                lr = !lr;
                last = nLast;
                System.out.println();
                printLevelAndOrientation(level++, lr);
            }
        }
        System.out.println();
    }

    void printLevelAndOrientation(int level, boolean lr) {
        System.out.print("Level " + level + " from ");
        System.out.print(lr ? "left to right: " : "right to left: ");
    }
}
