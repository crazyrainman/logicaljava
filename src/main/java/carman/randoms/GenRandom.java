package carman.randoms;

public class GenRandom {
    public int rand1To5() {
        return (int) (Math.random() * 5) + 1;
    }
    public int rand1to7() {
        int num = 0;
        do {
            num = (rand1To5() - 1) * 5 + rand1To5() - 1;
        } while(num > 20); // 0 .. 20, 21 nums
        return num % 7 + 1;
    }
    public int rand1ToM(int m) {
        return (int)(Math.random() * m) + 1;
    }
    public int rand01p() {
        double p = 0.83;
        return Math.random() < p ? 0 : 1;
    }
}