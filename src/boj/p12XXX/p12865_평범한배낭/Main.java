package boj.p12XXX.p12865_평범한배낭;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] weight, value;
    static int[][] dp;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        weight = new int[N+1];
        value = new int[N+1];
        dp = new int[2][K+1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            weight[i] = Integer.parseInt(st.nextToken());
            value[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                dp[i%2][j] = Math.max(dp[i%2][j], dp[1-i%2][j]);
                if (j - weight[i] >= 0)
                    dp[i%2][j] = Math.max(dp[i%2][j], dp[1-i%2][j-weight[i]] + value[i]);
            }
        }
        System.out.println(dp[N%2][K]);
    }
}
