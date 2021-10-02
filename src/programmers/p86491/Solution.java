package programmers.p86491;

public class Solution {
    public int solution(int[][] sizes) {
        int N = sizes.length;

        int totalMax = 0;
        int totalMin = 0;
        for (int i = 0; i < N; i++) {
            int max = Math.max(sizes[i][0], sizes[i][1]);
            int min = Math.min(sizes[i][0], sizes[i][1]);

            totalMax = Math.max(totalMax, max);
            totalMin = Math.max(totalMin, min);
        }
        return totalMax * totalMin;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new int[][]{{60, 50}, {30, 70}, {60, 30}, {80, 40}}));
    }
}
