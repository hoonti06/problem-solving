package programmers.p49191;


public class Solution {

    public int solution(int n, int[][] results) {
        boolean[][] nodes = new boolean[n][n];
        boolean[][] reverseNodes = new boolean[n][n];
        for (int i = 0; i < results.length; i++) {
            int from = results[i][0] - 1;
            int to = results[i][1] - 1;
            nodes[from][to] = true;
            reverseNodes[to][from] = true;
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (nodes[i][k] && nodes[k][j]) {
                        nodes[i][j] = true;
                    }
                    if (reverseNodes[i][k] && reverseNodes[k][j]) {
                        reverseNodes[i][j] = true;
                    }
                }
            }
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (nodes[i][j] || reverseNodes[i][j]) {
                    cnt++;
                }
            }
            if (cnt == n-1) {
                res++;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(5, new int[][]{{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}}));
    }
}
