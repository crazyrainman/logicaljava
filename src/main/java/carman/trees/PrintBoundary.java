package carman.trees;

public class PrintBoundary {
    public static void print1(Node head) {
        if (head == null) {
            return;
        }
        int height = getHeight(head, 0);
        Node[][] edgeMap = new Node[height][2];
        setEdgeMap(head, 0, edgeMap);
        //print left boundary
        for (int i = 0; i != edgeMap.length; i++) {
            System.out.print(edgeMap[i][0].value + " ");
        }
        //print leafs which is not left nor right boundary
        printLeafNotInMap(head, 0, edgeMap);
        //print right boundary, which is not in left boundary
        for(int i = edgeMap.length - 1; i != -1; i--) {
            if (edgeMap[i][0] != edgeMap[i][1]) {
                System.out.print(edgeMap[i][1].value + " ");
            }
        }
        System.out.println();
    }
    public static int getHeight(Node h, int l) {
        if (h == null) {
            return l;
        }
        return Math.max(getHeight(h.left, l + 1), getHeight(h.right, l + 1));
    }

    public static void setEdgeMap(Node h, int l, Node[][] edgeMap) {
        if (h == null) {
            return;
        }
        edgeMap[l][0] = edgeMap[l][0] == null ? h : edgeMap[l][0];
        edgeMap[l][1] = h;
        setEdgeMap(h.left, l + 1, edgeMap);
        setEdgeMap(h.right, l + 1, edgeMap);
    }
    public static void printLeafNotInMap(Node h, int l, Node[][] m) {
        if (h == null) {
            return;
        }
        if (h.left == null && h.right == null && h != m[l][0] && h != m[l][1]) {
            System.out.print(h.value + " ");
        }
        printLeafNotInMap(h.left, l + 1, m);
        printLeafNotInMap(h.right, l + 1, m);
    }

    public static void printEdge2(Node head) {
        if (head == null) {
            return;
        }
        System.out.print(head.value + " ");
        if (head.left != null && head.right != null) {
            printLeftEdge(head.left, true);
            printRightEdge(head.right, true);
        } else {
            printEdge2(head.left != null ? head.left : head.right);
        }
        System.out.println();
    }
    public static void printLeftEdge(Node h, boolean print) {
        if (h == null) {
            return;
        }
        if (print || (h.left == null && h.right == null)) { // 先序
            System.out.print(h.value + " ");
        }
        printLeftEdge(h.left, print);
        printLeftEdge(h.right, print && h.left == null ? true: false); // left is first 
    }
    public static void printRightEdge(Node h, boolean print) {
        if (h == null) {
            return;
        }
        printRightEdge(h.left,  print && h.right == null ? true : false); // right is first
        printRightEdge(h.right, print);
        if (print || (h.left == null && h.right == null)) { // 后序
            System.out.print(h.value + " ");
        }
    }
}