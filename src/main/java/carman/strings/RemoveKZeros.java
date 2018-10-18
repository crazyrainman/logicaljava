package carman.strings;
public class RemoveKZeros {
    public String removeKZeros(String str, int k) {
        if (str == null || k < 1) {
            return str;
        }
        char[] chas = str.toCharArray();
        int count = 0, start = -1;
        for (int i = 0; i != chas.length; i++) {
            if (chas[i] == '0') {
                count++;
                start = start == -1 ? i : start;
            } else {
                if (count == k) {
                    while (count-- != 0)
                        chas[start++] = 0;
                }
                count = 0;
                start = -1;
            }
        }
        if (count == k) {
            while (count-- != 0)
                chas[start++] = 0;
        }
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < chas.length; i++) {
            if (chas[i] != 0) {
                res.append(chas[i]);
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String str = "A0000B000";
        System.out.println(new RemoveKZeros().removeKZeros(str, 3));
    }
}