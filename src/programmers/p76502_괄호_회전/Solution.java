package programmers.p76502_괄호_회전;

import java.util.*;

public class Solution {
    static Map<Character, Integer> map = new HashMap<>();
    static Deque<Integer> stack = new ArrayDeque<>();
    static {
        map.put('(', 0); map.put(')', 3);
        map.put('[', 1); map.put(']', 4);
        map.put('{', 2); map.put('}', 5);
    }
    public int solution(String s) {
        List<Character> list = new ArrayList<>();

        int N = s.length();
        for (int i = 0; i < N; i++) list.add(s.charAt(i));

        int res = 0;
        for (int i = 0; i < N; i++) {
            int[] cnts = new int[3];
            for (int j = 0; j < N; j++) {
                int num = map.get(list.get(j));
                if (num < 3) {
                    cnts[num]++;
                    stack.push(num);
                }
                else {
                    if (--cnts[num - 3] < 0) break;
                    if (stack.peek() != num-3) break;
                    stack.pop();
                }
            }
            if (cnts[0] == 0 && cnts[1] == 0 && cnts[2] == 0)
                res++;
            list.add(list.remove(0));
        }
        System.out.println(res);
        return res;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution("[](){}");
        s.solution("}]()[{");
        s.solution("[)(]");
        s.solution("}}}");
    }
}
