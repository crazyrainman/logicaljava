package carman.strings;
public class Adjust {
    public void replace(char[] chas) {
        if (chas == null || chas.length == 0) {
            return;
        }
        int num = 0; 
        int len = 0;
        for (len = 0; len < chas.length && chas[len] != 0; len++) {
            if (chas[len] == ' ') {
                num++;
            }
        }
        int j = len + num * 2 - 1; // 调整后需要的长度
        for (int i = len - 1; i > -1; i--) {
            if (chas[i] != ' ') {
                chas[j--] = chas[i];
            } else {
                chas[j--] = '0';
                chas[j--] = '2';
                chas[j--] = '%';
            }
        }
    }
}