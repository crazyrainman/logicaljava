package carman.arrays;

import java.util.Comparator;

public class MyHeap<K> {
    private Node<K> head; // 堆头节点
    private Node<K> last; // 堆尾节点
    private long size; // 当前堆的大小
    private Comparator<K> comp;  // 比较器，大根堆或小根堆
    public MyHeap(Comparator<K> compare) {
        head = null;
        last = null;
        size = 0;
        comp = compare; // 基于比较器决定是大根堆还是小根堆
    }
    public K getHead() {
        return head == null ? null : head.value;
    }
    public long getSize() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0 ? true : false;
    }
    // 添加一个新节点到堆中
    public void add(K value) {
        Node<K> newNode = new Node<K>(value);
        if (size == 0) {
            head = newNode;
            last = newNode;
            size++;
            return;
        }
        Node<K> node = last;
        Node<K> parent = node.parent;
        // 找到正确的位置并插入到新节点
        while(parent != null && node != parent.left) {
            node = parent;
            parent = node.parent;
        }
        Node<K> nodeToAdd = null;
        if (parent == null) {
            nodeToAdd = mostLeft(head);
            nodeToAdd.left = newNode;
            newNode.parent = nodeToAdd;
        } else if (parent.right == null) {
            parent.right = newNode;
            newNode.parent = parent;
        } else {
            nodeToAdd = mostLeft(parent.right);
            nodeToAdd.left = newNode;
            newNode.parent = nodeToAdd;
        }
        last = newNode;
        // 建堆过程及其调整
        headInsertModify();
        size++;
    }
    public K popHead() {
        if (size == 0) {
            return null;
        }
        Node<K> res = head;
        if (size == 1) {
            head = null;
            last = null;
            size--;
            return res.value;
        }
        Node<K> oldLast = popLastAndSetPreviousLast();
        // 如果弹出堆尾节点后， 堆的大小等于1的处理
        if (size == 1) {
            head = oldLast;
            last = oldLast;
            return res.value;
        }
        // 如果弹出堆尾节点后， 堆的大小大于1的处理
        Node<K> headLeft = res.left;
        Node<K> headRight = res.right;
        oldLast.left = headLeft;
        if (headLeft != null) {
            headLeft.parent = oldLast;
        }
        oldLast.right = headRight;
        if (headRight != null) {
            headRight.parent = oldLast;
        }
        res.left = null;
        res.right = null;
        head = oldLast;
        // 堆heapify过程
        heapify(oldLast);
        return res.value;
    }
    // 找到以node为头的子树中， 最左的节点
    private Node<K> mostLeft(Node<K> node) {
        while(node.left != null) {
            node = node.left;
        }
        return node;
    }
    // 找到以node为头的子树中， 最右的节点
    private Node<K> mostRight(Node<K> node) {
        while(node.right != null) {
            node = node.right;
        }
        return node;
    }
    // 建堆及其调整的过程
    private void headInsertModify() {
        Node<K> node = last;
        Node<K> parent = node.parent;
        if (parent != null && comp.compare(node.value, parent.value) < 0) {
            last = parent;
        }
        while(parent != null && comp.compare(node.value, parent.value) < 0) {
            swapClosedTwoNodes(node, parent);
            parent = node.parent;
        }
        if (head.parent != null) {
            head = head.parent;
        }
    }
    private void heapify(Node<K> node) {
        Node<K> left = node.left;
        Node<K> right = node.right;
        Node<K> most = node;
        while(left != null) {
            if (left != null && comp.compare(left.value, most.value) < 0) {
                most = left;
            }
            if (right != null && comp.compare(right.value, most.value) < 0) {
                most = right;
            }
            if (most != node) {
                swapClosedTwoNodes(most, node);
            } else {
                break;
            }
            left = node.left;
            right = node.right;
            most = node;
        }
        if (node.parent == last) {
            last = node;
        }
        while(node.parent != null) {
            node = node.parent;
        }
        head = node;
    }
    // 交换相邻的两个节点
    private void swapClosedTwoNodes(Node<K> node, Node<K> parent) {
        
    }
}