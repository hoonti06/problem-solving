package boj.p2XXX.p2021_암호코드;

import java.util.*;
import java.io.*;

public class Main {
    static final int MOD = 1_000_000;
    static int[] input, dp;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        Scanner sc = new Scanner(System.in);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        char[] ch = sc.next().toCharArray();
        int N = ch.length;
        dp = new int[N+1];
        dp[0] = 1;

        input = new int[N+1];
        for (int i = 1; i <= N; i++)
            input[i] = ch[i-1] - '0';

        for (int i = 1; i <= N; i++) {
            boolean flag1 = false;
            int num1 = input[i];
            if (num1 != 0) {
                flag1 = true;
                dp[i] += dp[i-1];
            }

            boolean flag2 = false;
            if (i >= 2) {
                int num2 = input[i-1] * 10 + input[i];
                if (10 <= num2 && num2 <= 26) {
                    flag2 = true;
                    dp[i] += dp[i-2];
                }
            }
            if (!flag1 && !flag2) break;
            dp[i] %= MOD;
        }
        System.out.println(dp[N]);
    }
}
