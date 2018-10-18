package carman.strings;
public class StrToNum {
    public boolean isValid(char[] chas) {
        // A**
        if (chas[0] != '-' && (chas[0] < '0' || chas[0] > '9')) {
            return false;
        }
        // -, -0
        if (chas[0] == '-' && (chas.length == 1 || chas[1] == '0')) {
            return false;
        }
        // 0**
        if (chas[0] == '0' && chas.length > 1) {
            return false;
        }
        // **A**
        for (int i = 1; i < chas.length; i++) {
            if (chas[i] < '0' || chas[i] > '9') {
                return false;
            }
        }
        return true;
    }
    public int convert(String str) {
        if (str == null || str.equals("")) {
            return 0;
        }
        char[] chas = str.toCharArray();
        if (!isValid(chas)) {
            return 0;
        }
        boolean posi = chas[0] == '-' ? false : true; // 正数或负数
        int minq = Integer.MIN_VALUE / 10;
        int minr = Integer.MIN_VALUE % 10;
        int res = 0;
        int cur = 0;
        for (int i = posi ? 0 : 1; i < chas.length; i++) {
            cur = '0' - chas[i];
            // res *10 加 cur之前做判断
            if ((res < minq) || (res == minq && cur < minr)) {
                return 0;
            }
            res = res * 10 + cur;
        }
        if (posi && res == Integer.MIN_VALUE) {
            return 0;
        }
        return posi ? -res : res;
    }
}