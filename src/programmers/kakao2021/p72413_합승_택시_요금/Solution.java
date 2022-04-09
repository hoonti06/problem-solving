package programmers.kakao2021.p72413_합승_택시_요금;

import java.util.Arrays;

public class Solution {
    static final int MAX_VAL = 20_000_010;
    static int[][] dists;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        dists = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dists[i], MAX_VAL);
        }

        for (int[] cur : fares) {
            int from = cur[0];
            int to = cur[1];
            int dist = cur[2];

            dists[from][to] = dist;
            dists[to][from] = dist;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dists[i][k] == MAX_VAL || dists[k][j] == MAX_VAL)
                        continue;

                    dists[i][j] = Math.min(dists[i][j], dists[i][k] + dists[k][j]);
                }
            }
        }


        int answer = MAX_VAL;
        for (int i = 1; i <= n; i++) dists[i][i] = 0;
        for (int i = 1; i <= n; i++)
            answer = Math.min(answer, dists[s][i] + dists[i][a] + dists[i][b]);

        return answer;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution(6, 4, 6, 2, new int[][]{{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}});

        s.solution(7, 3, 4, 1, new int[][]{{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}});

        s.solution(6, 4, 5, 6, new int[][]{{2, 6, 6}, {6, 3, 7}, {4, 6, 7}, {6, 5, 11}, {2, 5, 12}, {5, 3, 20}, {2, 4, 8}, {4, 3, 9}});
    }
}
