package programmers.p86052;

import java.util.*;

public class Solution {
    static int N, M;
    static boolean[][][] visited;
    static int[] dr = { 1,  0, -1,  0};
    static int[] dc = { 0, -1,  0,  1};
    static char[] change = new char[26];
    static {
        change['S'-'A'] = 0;
        change['L'-'A'] = 3;
        change['R'-'A'] = 1;
    }

    public int movePath(String[] grid, int r, int c, int dir) {
        int cnt = 0;
        while (!visited[r][c][dir]) {
            cnt++;
            visited[r][c][dir] = true;
            r = (r + dr[dir] + N) % N;
            c = (c + dc[dir] + M) % M;
            char ch = grid[r].charAt(c);
            dir = (dir + change[ch - 'A']) % 4;
        }
        return cnt;
    }
    public int[] solution(String[] grid) {
        N = grid.length;
        M = grid[0].length();
        List<Integer> list = new ArrayList<>();
        visited = new boolean[N][M][4];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                for (int d = 0; d < 4; d++) {
                    int ret = movePath(grid, r, c, d);
                    if (ret > 0) list.add(ret);
                }
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        Arrays.sort(res);
        return res;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(Arrays.toString(s.solution(new String[]{"SL", "LR"})));
        System.out.println(Arrays.toString(s.solution(new String[]{"S"})));
        System.out.println(Arrays.toString(s.solution(new String[]{"R", "R"})));
    }
}
