package programmers.p68644;

import java.util.*;

public class Solution {
    public int[] solution(int[] numbers) {
        boolean[] isPossible = new boolean[201];

        int n = numbers.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                isPossible[numbers[i] + numbers[j]] = true;
            }
        }

        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 201; i++) {
            if (isPossible[i]) {
                list.add(i);
            }
        }

        int m = list.size();
        int[] answer = new int[m];
        for (int i = 0; i < m; i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution(new int[]{2,1,3,4,1});
        s.solution(new int[]{5,0,2,7});
    }
}
