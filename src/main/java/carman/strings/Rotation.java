package carman.strings;

import carman.arrays.KMP;

public class Rotation {
    public boolean isRotation(String a, String b) {
        if (a == null || b == null || a.length() != b.length()) {
            return false;
        }
        String b2 = b + b;
        return getIndexOf(b2, a) != -1;
    }
    public int getIndexOf(String s, String m) {
        return new KMP().getIndexOf(s, m);
    }
}