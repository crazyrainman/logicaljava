package carman.strings;
public class NewChars {
    public String pointNewchar(String s, int k) {
        if (s == null || s.equals("") || k < 0 || k >= s.length()) {
            return "";
        }
        char[] chas = s.toCharArray();
        int uNum = 0;
        for(int i = k - 1; i >= 0; i--) {
            if (!isUpper(chas[i])) {
                break;
            }
            uNum++;
        }
        // 奇数
        if ((uNum & 1) == 1) {
            return s.substring(k - 1, k + 1);
        }
        // 偶数， 是大写
        if (isUpper(chas[k])) {
            return s.substring(k, k + 2);
        }
        // 偶数，小写
        return String.valueOf(chas[k]);
    }
    boolean isUpper(char c) {
        return (c - 'A') >= 0 && (c - 'Z') <= 0;
    }
}