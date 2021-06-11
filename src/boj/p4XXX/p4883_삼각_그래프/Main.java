package boj.p4XXX.p4883_삼각_그래프;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int MAX_N = 100_010;
    static final int LEFT = 0;
    static final int CENTER = 1;
    static final int RIGHT = 2;
    static long[][] dp = new long[MAX_N][3];
    static long[][] board = new long[MAX_N][3];

    static int N;

    static long solution() {
        dp[0][LEFT] = Integer.MAX_VALUE;
        dp[0][CENTER] = board[0][CENTER];
        dp[0][RIGHT] = board[0][CENTER] + board[0][RIGHT];

        for (int i = 1; i < N; i++) {
            dp[i][LEFT] = Math.min(dp[i-1][LEFT], dp[i-1][CENTER]) + board[i][LEFT];

            for (int j = 0; j < 3; j++)
                dp[i][CENTER] = Math.min(dp[i][CENTER], dp[i-1][j]);
            dp[i][CENTER] = Math.min(dp[i][CENTER], dp[i][LEFT]);
            dp[i][CENTER] += board[i][CENTER];

            dp[i][RIGHT] = Math.min(dp[i-1][CENTER], Math.min(dp[i-1][RIGHT], dp[i][CENTER])) + board[i][RIGHT];
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 3; j++)
                System.out.print(dp[i][j] + " ");
            System.out.println();
        }

        return dp[N-1][CENTER];
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int tc = 1;
        while (true) {
            for (int i = 0; i < MAX_N; i++)
                Arrays.fill(dp[i], Integer.MAX_VALUE);

            N = Integer.parseInt(in.readLine());
            if (N <= 0) break;
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine(), " ");
                for (int j = 0; j < 3; j++)
                    board[i][j] = Long.parseLong(st.nextToken());
            }

            System.out.println(tc + ". " + solution());
            tc++;
        }
    }
}
