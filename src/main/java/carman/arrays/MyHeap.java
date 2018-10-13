package carman.arrays;

import java.util.Comparator;

public class MyHeap<K> {
    private Node<K> head;
    private Node<K> last;
    private long size;
    private Comparator<K> comp;
    public MyHeap(Comparator<K> compare) {
        head = null;
        last = null;
        size = 0;
        comp = compare;
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
    public void add(K value) {
        Node<K> newNode = new Node<K>(value);
        if (size == 0) {
            head = newNode;
            last = newNode;
            size++;
            return;
        }
        // 一个边
        Node<K> node = last;
        Node<K> parent = node.parent; // 无论左右，找到父亲
        // 沿着右子树往上
        while(parent != null && node != parent.left) {
            node = parent;
            parent = node.parent;
        }
        Node<K> nodeToAdd = null;
        if (parent == null) { // 满树，加到下一层
            nodeToAdd = mostLeft(head); // 根的左边界最下一个
            nodeToAdd.left = newNode;
            newNode.parent = nodeToAdd;
        } else if (parent.right == null) { // 父节点的右孩子是空
            parent.right = newNode;
            newNode.parent = parent;
        } else { // 右子树最左
            nodeToAdd = mostLeft(parent.right);
            nodeToAdd.left = newNode;
            newNode.parent = nodeToAdd;
        }
        last = newNode;
        heapInsertModify();
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
        if (size == 1) {
            head = oldLast;
            last = oldLast;
            return res.value;
        }
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
        res.right = null;
        res.left = null;
        head = oldLast;
        heapify(oldLast);
        return res.value;
    }

    private Node<K> mostLeft(Node<K> node) {
        while(node.left != null) {
            node = node.left;
        }
        return node;
    }
    private Node<K> mostRight(Node<K> node) {
        while(node.right != null) {
            node = node.right;
        }
        return node;
    }
    private void heapInsertModify() {
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
    private void swapClosedTwoNodes(Node<K> node, Node<K> parent) {
        if (node == null || parent == null) {
            return;
        }
        Node<K> parentParent = parent.parent;
        Node<K> parentLeft = parent.left;
        Node<K> nodeLeft = node.left;
        Node<K> nodeRight = node.right;
        node.parent = parentParent;
        if (parentParent != null) {
            if (parent == parentParent.left) {
                parentParent.left = node;
            } else {
                parentParent.right = node;
            }
        }
        parent.parent = node;
        if (nodeLeft != null) {
            nodeLeft.parent = parent;
        }
        if (nodeRight != null) {
            nodeRight.parent = parent;
        }
        if(node == parent.left) { // TODO : continue
            
        }
    }
    public Node<K> popLastAndSetPreviousLast() {
        return null; // TODO: 
    }
    public void heapify(Node<K> node) {
        // TODO
    }
}