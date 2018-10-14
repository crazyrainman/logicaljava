package carman.arrays;

public class MatrixRotation {
    public void rotate(int[][] matrix) {
        int tR = 0;
        int tC = 0;
        int dR = matrix.length - 1;
        int dC = matrix[0].length - 1;
        while(tR < dR) {
            rotateEdge(matrix, tR++, tC++, dR--, dC--);
        }
    }
    public void rotateEdge(int[][] m, int tR, int tC, int dR, int dC) {
        int times = dC - tC; // times is the total num of group
        int tmp = 0;
        for (int i = 0; i != times; i++) {
            tmp = m[tR][tC + i];
            // 4 moves
            m[tR][tC + i] = m[dR - i][tC]; // 1.
            m[dR - i][tC] = m[dR][dC - i]; // 2.
            m[dR][dC - i] = m[tR + i][dC]; // 3. 
            m[tR + i][dC] = tmp;
        }
    }
}