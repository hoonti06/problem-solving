package boj.p10XXX.p10422_괄호;

import java.io.*;
import java.util.*;

public class Main {
    static final int MOD = 1_000_000_007;
    static int N;
    static long[] dp = new long[5001];
    static {
//        Arrays.fill(dp, -1);
    }

    static long go(int n) {
        if (n == 0) return 1;
        if (dp[n] > 0) return dp[n];

//        dp[n] = 0;
        for (int i = 2; i <= n; i+=2) {
            dp[n] += go(i - 2) * go(n - i);
            dp[n] %= MOD;
        }
        return dp[n];
    }

    static int solution() {
        N = 5000;
        dp[0] = 1;
        for (int i = 2; i <= N; i++) {
            for (int j = 2; j <= i; j++) {
                dp[i] += dp[j-2] * dp[i - j];
                dp[i] %= MOD;
            }
        }
        return 0;
    }


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        go(5000);
        solution();
        int testcase = Integer.parseInt(in.readLine());
        for (int tc = 0; tc < testcase; tc++) {
            N = Integer.parseInt(in.readLine());
            System.out.println(N % 2 == 1? 0 : dp[N]);
        }
    }
}
