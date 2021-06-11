package boj.p1XXX.p1463_1로_만들기;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] dp = new int[N+1];
        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i-1];
            if (i % 3 == 0)
                dp[i] = Math.min(dp[i], dp[i / 3]);
            if (i % 2 == 0)
                dp[i] = Math.min(dp[i], dp[i / 2]);

            dp[i]++;
        }
        System.out.println(dp[N]);
    }
}
