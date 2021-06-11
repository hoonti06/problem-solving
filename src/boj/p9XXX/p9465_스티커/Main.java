package boj.p9XXX.p9465_스티커;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] board;
    static int[][] dp;
    static int go(int r, int c) {
        if (r == 1) {
            dp[r][c] = board[r][c];
            return dp[r][c];
        } else if (r < 1) {
            return 0;
        }

        if (dp[r][c] != -1) return dp[r][c];

        dp[r][c] = 0;

        dp[r][c] = Math.max(dp[r][c], go(r-1, 1-c));
        dp[r][c] = Math.max(dp[r][c], go(r-2, c));
        dp[r][c] = Math.max(dp[r][c], go(r-2, 1-c));

        dp[r][c] += board[r][c];

        return dp[r][c];
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int testcase = Integer.parseInt(in.readLine());
        for (int tc = 0; tc < testcase; tc++) {
            N = Integer.parseInt(in.readLine());
            board = new int[N + 1][2];
            dp = new int[N + 1][2];
            for (int[] d : dp) Arrays.fill(d, -1);
            for (int i = 0; i < 2; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                for (int j = 1; j <= N; j++)
                    board[j][i] = Integer.parseInt(st.nextToken());
            }
            System.out.println(Math.max(go(N, 0), go(N, 1)));
        }
    }
}
