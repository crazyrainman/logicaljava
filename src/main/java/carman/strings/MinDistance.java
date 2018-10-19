package carman.strings;

import java.util.HashMap;
import java.util.Map.Entry;

public class MinDistance {
    public int minDistance(String[] strs, String str1, String str2) {
        if (str1 == null || str2 == null) {
            return -1;
        }
        if (str1.equals("str2")) {
            return 0;
        }
        int last1 = -1;
        int last2 = -1;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < strs.length; i++) {
            if (strs[i].equals(str1)) {
                min = Math.min(min, last2 == -1 ? min : i - last2);
                last1 = i;
            }
            if (strs[i].equals(str2)) {
                min = Math.min(min, last1 == -1 ? min : i - last1);
                last2 = i;
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }    
}
class Record {
    private HashMap<String, HashMap<String, Integer>> record;
    public Record(String[] strArr) {
        record = new HashMap<>();
        HashMap<String, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < strArr.length; i++) {
            String curStr = strArr[i];
            update(indexMap, curStr, i);
            indexMap.put(curStr, i);
        }
    }
    // str is curStr in for loop
    private void update(HashMap<String, Integer> indexMap, String str, int i) {
        if (!record.containsKey(str)) {
            record.put(str, new HashMap<>());
        }
        HashMap<String, Integer> strMap = record.get(str);
        // lastEntry 最后一次出现的key和下标
        for(Entry<String, Integer> lastEntry : indexMap.entrySet()) {
            String key = lastEntry.getKey();
            int index = lastEntry.getValue();
            if (!key.equals(str)) { // 已发现的所有和curStr不同的
                HashMap<String, Integer> lastMap = record.get(key);
                int curMin = i - index; // 计算一个当前距离
                if (strMap.containsKey(key)) {
                    int preMin = strMap.get(key);
                    if (curMin < preMin) { // 如果更小
                        strMap.put(key, curMin); // 双向记录
                        lastMap.put(str, curMin); 
                    }
                } else {
                    strMap.put(key, curMin);
                    lastMap.put(str, curMin);
                }
            }
        }
    }
    public int minDistance2(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return -1;
        }
        if (str1.equals(str2)) {
            return 0;
        }
        if (record.containsKey(str1) && record.get(str1).containsKey(str2)) {
            return record.get(str1).get(str2);
        }
        return -1;
    }
}