package boj.p21278;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] dp;

    static void solution() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (dp[i][k] == Integer.MAX_VALUE || dp[k][j] == Integer.MAX_VALUE) {
                        continue;
                    }
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }

        int minI = -1;
        int minJ = -1;
        int minSum = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            for (int j = i+1; j <= N; j++) {
                int sum = 0;
                for (int k = 1; k <= N; k++) {
                    if (i == k || j == k) continue;
                    sum += Math.min(dp[i][k], dp[j][k]);
                }
                if (minSum > sum) {
                    minSum = sum;
                    minI = i;
                    minJ = j;
                }
            }
        }
        System.out.println(minI + " " + minJ + " " + minSum * 2);

    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        dp = new int[101][101];
        for (int i = 0; i < 101; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            dp[i][i] = 0;
        }
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            dp[from][to] = 1;
            dp[to][from] = 1;
        }

        solution();
    }
}
