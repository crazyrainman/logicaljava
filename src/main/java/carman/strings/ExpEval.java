package carman.strings;

import java.util.Deque;
import java.util.LinkedList;

public class ExpEval {
    public int getValue(String exp) {
        return value(exp.toCharArray(), 0)[0];
    }
    public int[] value(char[] chars, int i) {
        Deque<String> deq = new LinkedList<>();
        int pre = 0;
        int[] sub = null;
        // ) exp 计算完成 
        while(i < chars.length && chars[i] != ')') {
            // 收集数字
            if (chars[i] >= '0' && chars[i] <= '9') {
                pre = pre * 10 + chars[i++] - '0';
            }  else if (chars[i] == '+' || chars[i] == '-' || chars[i] == '*' || chars[i] == '/') {// else if (chars[i] != '(') 
            // + - * /
                addNum(deq, pre); // 收集的数字进队列
                deq.addLast(String.valueOf(chars[i++])); // + - * /, i -> i+1
                pre = 0; // 清空以前的收集
            } else { // '('
                sub = value(chars, i + 1);
                pre = sub[0];
                i = sub[1] + 1;
            }
        }
        addNum(deq, pre);
        return new int[] {getNum(deq), i};
    }
    // 数字进队列, 能做乘法就做乘法
    public void addNum(Deque<String> deq, int num) {
        if (!deq.isEmpty()) {
            int cur = 0;
            String top = deq.pollLast();
            if (top.equals("+") || top.equals("-")) {
                deq.addLast(top);
            } else {
                cur = Integer.valueOf(deq.pollLast());
                // * or /
                num = top.equals("*") ? (cur * num) : (cur / num);
            }
        }
        deq.addLast(String.valueOf(num));
    }
    public int getNum(Deque<String> deq) {
        int res = 0;
        boolean add = true;
        String cur = null;
        int num = 0;
        while(!deq.isEmpty()) {
            cur = deq.pollFirst();
            if (cur.equals("+")) {
                add = true;
            } else if (cur.equals("-")) {
                add = false;
            } else {
                num = Integer.valueOf(cur);
                res += add ? num : (-num);
            }
        }
        return res;
    }
}