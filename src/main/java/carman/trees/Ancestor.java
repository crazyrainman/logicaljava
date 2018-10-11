package carman.trees;

import java.util.HashMap;
import java.util.HashSet;

public class Ancestor {
    public Node lowestAncestor(Node head, Node o1, Node o2) {
        if (head == null || head == o1 || head == o2) {
            return head;
        }
        Node left = lowestAncestor(head.left, o1, o2);
        Node right = lowestAncestor(head.right, o1, o2);
        if (left != null && right != null) {
            return head;
        }
        return left != null ? left : right;
    }
}

class Record1 {
    private HashMap<Node, Node> map;
    public Record1(Node head) {
        map = new HashMap<>();
        if (head != null) {
            map.put(head, null);
        }
        setMap(head);
    }
    private void setMap(Node head) {
        if (head == null) {
            return;
        }
        if (head.left != null) {
            map.put(head.left, head);
        }
        if (head.right != null) {
            map.put(head.right, head);
        }
        setMap(head.left);
        setMap(head.right);
    }
    public Node query(Node o1, Node o2) {
        HashSet<Node> path = new HashSet<>();
        while(map.containsKey(o1)) {
            path.add(o1);
            o1 = map.get(o1);
        }
        while(!path.contains(o2)) {
            o2 = map.get(o2);
        }
        return o2;
    }
}

class Record2 {
    private HashMap<Node, HashMap<Node, Node>> map;
    public Record2(Node head) {
        map = new HashMap<>();
        initMap(head);
        setMap(head);
    }
    private void initMap(Node head) {
        if (head == null) {
            return;
        }
        map.put(head, new HashMap<>());
        initMap(head.left);
        initMap(head.right);
    }
    private void setMap(Node head) {
        if (head == null) {
            return;
        }
        headRecord(head.left, head);
        headRecord(head.right, head);
        subRecord(head);
        setMap(head.left);
        setMap(head.right);
    }
    private void headRecord(Node n, Node h) {
        if (n == null) {
            return;
        }
        map.get(n).put(h, h);
        headRecord(n.left, h);
        headRecord(n.right, h);
    }
    private void subRecord(Node head) {
        if (head == null) {
            return;
        }
        preLeft(head.left, head.right, head);
        subRecord(head.left);
        subRecord(head.right);
    }
    // 先序遍历生成左树
    private void preLeft(Node l, Node r, Node h) {
        if (l == null) {
            return;
        }
        preRight(l, r, h);
        preLeft(l.left, r, h);
        preLeft(l.right, r, h);
    }
    //先序遍历生成右树
    private void preRight(Node l, Node r, Node h) {
        if (r == null) {
            return;
        }
        map.get(l).put(r, h);
        preRight(l, r.left, h);
        preRight(l, r.right, h);
    }
    public Node query(Node o1, Node o2) {
        if (o1 == o2) {
            return o1;
        }
        // o1 is left tree head
        if (map.containsKey(o1)) {
            return map.get(o1).get(o2);
        }
        // o2 is left tree head
        if (map.containsKey(o2)) {
            return map.get(o2).get(o1);
        }
        return null;
    }
} 