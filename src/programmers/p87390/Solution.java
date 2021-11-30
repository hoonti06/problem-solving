package programmers.p87390;

import java.util.Arrays;

public class Solution {
    public int[] solution(int n, long left, long right) {
        int diff = (int)(right - left);
        int[] answer = new int[diff+1];
        int idx = 0;
        for (long i = left; i <= right; i++, idx++) {
            int r = (int) (i / n);
            int c = (int) (i % n);
            if (c <= r) {
                answer[idx] = r + 1;
            } else {
                answer[idx] = c + 1;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(Arrays.toString(s.solution(3, 2, 5)));
        System.out.println(Arrays.toString(s.solution(4, 7, 14)));
    }
}
