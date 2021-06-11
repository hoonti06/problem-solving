package programmers.p77886_110_옮기기;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class Solution {
    public String[] solution(String[] s) {
        int len = s.length;
        String[] answer = new String[len];
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            char[] str = s[i].toCharArray();
            Deque<Integer> stack = new ArrayDeque<>();
            int cnt1 = 0;
            int cnt110 = 0;
            for (int j = 0; j < str.length; j++) {
                if (str[j] == '1') {
                    cnt1++;
                } else {
                    if (cnt1 < 2) {
                        for (; cnt1 > 0; cnt1--) list.add('1');
                        list.add('0');
                    }
                    else {
                        cnt110++;
                        cnt1 -= 2;
                    }
                }
            }
            for (; cnt1 > 0; cnt1--) list.add('1');

            for (int j = 0; j < list.size(); j++) {
                if (str[j] == '1') {
                    if (++cnt1 == 3 && cnt110 > 0) {
                        list.add(j-2, '0');
                        list.add(j-2, '1');
                        list.add(j-2, '1');
                        cnt110--;
                    }
                } else {
                    cnt1 = 0;
                }
            }
            while (cnt110 > 0) {
                cnt110--;
                list.add('1');
                list.add('1');
                list.add('0');
            }
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < list.size(); j++)
                sb.append(list.get(j));

            answer[i] = sb.toString();
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution(new String[]{"1110","100111100","0111111010"});

    }
}
