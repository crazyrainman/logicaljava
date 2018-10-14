package carman.others;

public class WaterPool {
    // [1..max]
    public int rand(int max) {
        return (int)(Math.random() * max) + 1;
    }

    public int[] getKNumsRand(int k, int max) {
        if (max < 1 || k < 1) { // max == 0 || k == 0
            return null;
        }
        int[] res = new int[Math.min(k, max)]; // 生成一个较小的数组
        // [1, k] -> res
        for (int i = 0; i < res.length; i++) {
            res[i] = i + 1;
        }
        // i : [k+1, max] -> rand(i) <= k -> res[rand(k) - 1] = i
        for (int i = k + 1; i < max + 1; i++) {
            if (rand(i) <= k) {
                res[rand(k) - 1] = i;
            }
        }
        return res;
    }
}