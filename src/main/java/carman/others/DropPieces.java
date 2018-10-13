package carman.others;

public class DropPieces {
    public int solution1(int nLevel, int kChess) {
        if (nLevel == 0 || kChess == 0) {
            return 0;
        }
        return process1(nLevel, kChess);
    }
    // [n, k] 0 .. n 层楼， k个棋子
    public int process1(int nLevel, int kChess) {
        if (nLevel == 0) {
            return 0;
        }
        if (kChess == 1) {
            return nLevel;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 1; i  < nLevel + 1; i++) {
            min = Math.min(min, 
            Math.max(
            process1(i - 1, kChess - 1), // i 层碎了
            process1(nLevel - i, kChess) // i 层没碎
            ));
        }
        return min + 1;
    }
    // [0..nLevel], [0..kChess]
    public int solution2(int nLevel, int kChess) {
        if (nLevel == 0 || kChess == 0) {
            return 0;
        }
        if (kChess == 1) {
            return nLevel;
        }
        int[][] dp = new int[nLevel + 1][kChess + 1]; // row[0][0:kChess] is zero
        for (int i = 1; i != dp.length; i++) {
            dp[i][1] = i;
        }
        // [i][j] i <- [1, nLevel] j <- [2, kChess]
        for (int i = 1; i < dp.length; i++) {
            for (int j = 2; j < dp[0].length; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = 1; k != i + 1; k++) { // [i][j] = min(max([k -1][j -1], [i - k][j]) k<-[1:i]
                    min = Math.min(min, Math.max(dp[k - 1][j - 1], dp[i - k][j]));
                }
                dp[i][j] = min + 1;
            }
        }
        return dp[nLevel][kChess];
    }
    // TODO : 四边形猜想
}