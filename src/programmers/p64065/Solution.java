package programmers.p64065;

import java.util.*;

public class Solution {
    public int[] solution(String s) {
        String[] split = s.split("},\\{");
        split[0] = split[0].substring(2);
        int n = split.length;
        split[n-1] = split[n-1].substring(0, split[n-1].length()-2);
        Arrays.sort(split, Comparator.comparingInt(String::length));

        Set<String> set = new LinkedHashSet<>();
        for (String str : split) {
            for (String element : str.split(",")) {
                if (!set.contains(element)) {
                    set.add(element);
                    break;
                }
            }
        }

        int i = 0;
        int[] res = new int[set.size()];
        for (String element : set) {
            res[i++] = Integer.parseInt(element);
        }

        return res;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
//        System.out.println(Arrays.toString(s.solution("{{2},{2,1},{2,1,3},{2,1,3,4}}")));
        System.out.println(Arrays.toString(s.solution("{{1,2,3},{2,1},{1,2,4,3},{2}}")));
        System.out.println(Arrays.toString(s.solution("{{20,111},{111}}")));
        System.out.println(Arrays.toString(s.solution("{{123}}")));
        System.out.println(Arrays.toString(s.solution("{{4,2,3},{3},{2,3,4,1},{2,3}}")));
    }
}
