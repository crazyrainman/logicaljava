package carman.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Cmp implements Comparator<String> {
    @Override
    public int compare(String l, String r) {
        return l.length() - r.length();
    }
}

public class JSorts {
    public static void main(String[] args) {
        String domains[] = {"Practice", "Geeks", "Code", "Quiz", "A"};
        Arrays.sort(domains, new Cmp());
        for(String s : domains) {
            System.out.println(s);
        }
        System.out.println("-----");
        List<String> colList = new ArrayList<>(Arrays.asList(domains));
        Collections.sort(colList, Collections.reverseOrder());
        for(String s : colList) {
            System.out.println(s);
        }
    }
}