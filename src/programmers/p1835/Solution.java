package programmers.p1835;

import java.io.*;
import java.util.*;

public class Solution {
    static int res;
    static final char[] input = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    static Map<Character, Integer> order;
    static List<Condition> list;
    static class Condition {
        char a, b, op;
        int diff;
        public Condition(char a, char b, char op, int diff) {
            this.a = a;
            this.b = b;
            this.op = op;
            this.diff = diff;
        }
    }

    public int solution(int n, String[] data) {
        res = 0;
        order = new HashMap<>();
        list = new ArrayList<>();
        for (String element : data) {
            char a = element.charAt(0);
            char b = element.charAt(2);
            char op = element.charAt(3);
            int diff = element.charAt(4) - '0';
            Condition c = new Condition(a, b, op, diff);
            list.add(c);
        }

        go(0);
        return res;
    }

    public void go(int idx) {
        if (idx >= 8) {
            int n = list.size();
            int i = 0;
            for (; i < n; i++) {
                Condition c = list.get(i);
                int diff = Math.abs(order.get(c.a) - order.get(c.b)) - 1;
                if (c.op == '<') {
                    if (diff >= c.diff) {
                        break;
                    }
                } else if (c.op == '>') {
                    if (diff <= c.diff) {
                        break;
                    }
                } else {
                    if (diff != c.diff) {
                        break;
                    }
                }
            }
            if (i >= n) {
                res++;
            }
            return;
        }
        for (int i = 0; i < 8; i++) {
            if (order.containsKey(input[i])) {
                continue;
            }
            order.put(input[i], idx);
            go(idx+1);
            order.remove(input[i]);
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(2, new String[]{"N~F=0", "R~T>2"}));
        System.out.println(s.solution(2, new String[]{"M~C<2", "C~M>1"}));
    }
}
