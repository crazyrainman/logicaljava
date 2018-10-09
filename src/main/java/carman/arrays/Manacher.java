package carman.arrays;
public class Manacher {
    public char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = (i % 2) == 1 ? '#' : charArr[index++];
        }
        return res;
    }
    public int maxLcpsLength(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] charArr = manacherString(str);
        int[] pArr = new int[charArr.length]; //回文半径
        int index = -1; // 最右边界中心
        int pR= -1; //当前最右
        int max = Integer.MIN_VALUE;
        for (int i = 0; i != charArr.length; i++) {
            pArr[i] = pR > i ? Math.min(pArr[2 * index - i], pR - i) : 1;
            while(i + pArr[i] < charArr.length && i - pArr[i] > -1) {
                if (charArr[i + pArr[i]] == charArr[i - pArr[i]]) { //扩右边界
                    pArr[i] ++;
                } else {
                    break;
                }
            }
            if (i + pArr[i] > pR) { // 更新右边界和中心
                pR = i + pArr[i];
                index = i;
            }
            max = Math.max(max, pArr[i]);
        }
        return max - 1;
    }
}