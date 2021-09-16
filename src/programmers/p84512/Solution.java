package programmers.p84512;

import java.util.*;

public class Solution {

    static List<String> list;
    static char[] alphabets = "AEIUO".toCharArray();

    public int solution(String word) {
        list = new ArrayList<>();
        go(0, "");
        Collections.sort(list);

        int n = list.size();
        for (int i = 0; i < n; i++) {
            if (list.get(i).equals(word)) {
                return i+1;
            }
        }
        return 0;
    }

    void go(int idx, String cur) {
        if (idx >= 5) {
            return;
        }
        for (int i = 0; i < 5; i++) {
            String next = cur + alphabets[i];
            list.add(next);
            go(idx+1, next);
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution("AAAAE"));
        System.out.println(s.solution("AAAE"));
        System.out.println(s.solution("I"));
        System.out.println(s.solution("EIO"));
    }
}
