package swea.p4340_파이프;

import java.io.*;
import java.util.*;

public class Solution {
    static int N;
    static int[][] board;
    static int res;
    static int[][][] memo;
    static boolean[][] visited;

    // 상우하좌
    static int[] dx = {-1, 0, 1,  0};
    static int[] dy = { 0, 1, 0, -1};

    static void bfs(int r, int c, int dir) {

    }

    static int go(int r, int c, int dir, int sum) {
        if (r < 0 || r >= N || c < 0 || c >= N) {
            if (r == N-1 && c == N && dir == 1) {
//                res = Math.min(res, sum);
                return 0;
            }
            return 5000;
        }

        if (visited[r][c]) return 5000;

        if (memo[r][c][dir] != -1)
            return memo[r][c][dir];

        visited[r][c] = true;
        int min = 5000;
//        memo[r][c][dir] = Integer.MAX_VALUE;
        if (board[r][c] <= 2) { // straight
            int ret = go(r + dx[dir], c + dy[dir], dir, sum + 1);
            min = Math.min(min, ret + 1);
        } else {
            for (int i = 0, j = 1; i < 2; i++, j +=2) {
                int nextDir = (dir+j) % 4;
                int ret = go(r + dx[nextDir], c + dy[nextDir], nextDir, sum + 1);
                min = Math.min(min, ret + 1);
            }
        }

        visited[r][c] = false;
        memo[r][c][dir] = min;
        return memo[r][c][dir];
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(in.readLine());
        for (int tc = 1; tc <= testcase; tc++) {
            res = Integer.MAX_VALUE;
            N = Integer.parseInt(in.readLine());
            board = new int[N][N];
            memo = new int[N][N][4];
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    Arrays.fill(memo[i][j], -1);
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            go(0, 0, 1, 0);
            res = Integer.MAX_VALUE;
            for (int i = 0; i < 4; i++) {
                if (memo[0][0][i] != -1)
                    res = Math.min(res, memo[0][0][i]);
            }
            System.out.println("#" + tc + " " + res);
//            break;
        }
    }
}
