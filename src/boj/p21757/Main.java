package boj.p21757;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long[] sum;
    static long[] dp = new long[4];

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());

        sum = new long[N+1];
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            int input = Integer.parseInt(st.nextToken());
            sum[i] = sum[i-1] + input;
        }

        if(sum[N] % 4 != 0) {
            System.out.println(0);
            return;
        }

        long subSum = sum[N] / 4;
        dp[0] = 1;
        for (int i = 1; i < N; i++) { // 1에서 N-1 까지
            long curSum = sum[i];
            if (curSum == subSum * 3) {
               dp[3] += dp[2];
            }
            if (curSum == subSum * 2) {
                dp[2] += dp[1];
            }
            if (curSum == subSum) {
                dp[1] += dp[0];
            }
        }

        System.out.println(dp[3]);
    }
}
