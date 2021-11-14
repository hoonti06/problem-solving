package programmers.p86051;

import java.util.*;

public class Solution {
    public int solution(int[] numbers) {
        Set<Integer> set = new HashSet<>();
        for (int number : numbers) {
            set.add(number);
        }

        int answer = 0;
        for (int i = 0; i < 10; i++) {
            if (!set.contains(i)) {
                answer += i;
            }
        }
        return answer;
    }
}
