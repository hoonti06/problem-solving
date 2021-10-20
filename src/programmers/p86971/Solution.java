package programmers.p86971;

import java.util.Arrays;

public class Solution {
    static boolean[][] edges;
    static boolean[] visited;
    static int N;
    public int solution(int n, int[][] wires) {

        N = n+1;
        int M = wires.length;
        edges = new boolean[N][N];
        visited = new boolean[N];

        for (int i = 0; i < M; i++) {
            int a = wires[i][0];
            int b = wires[i][1];
            edges[a][b] = true;
            edges[b][a] = true;
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < M; i++) {
            Arrays.fill(visited, false);
            int a = wires[i][0];
            int b = wires[i][1];
            edges[a][b] = false;
            edges[b][a] = false;

            int aCnt = dfs(a, 0);
            int bCnt = dfs(b, 0);
            res = Math.min(res, Math.abs(aCnt - bCnt));

            edges[a][b] = true;
            edges[b][a] = true;
        }
        System.out.println(res);
        return res;
    }

    public int dfs(int cur, int cnt) {
        if (visited[cur]) {
            return 0;
        }
        visited[cur] = true;

        int sum = 1;
        for (int i = 0; i < N; i++) {
            if (!edges[cur][i]) {
                continue;
            }

            sum += dfs(i, cnt+1);
        }

        visited[cur] = false;
        return sum;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution(9, new int[][]{{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}});
        s.solution(4, new int[][]{{1,2},{2,3},{3,4}});
        s.solution(7, new int[][]{{1,2},{2,7},{3,7},{3,4},{4,5},{6,7}});
    }
}
