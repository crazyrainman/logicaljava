package carman.arrays;

public class Node<K> {
    public K value;
    public Node<K> left;
    public Node<K> right;
    public Node<K> parent;
    public Node(K data) {
        value = data;
    }
}