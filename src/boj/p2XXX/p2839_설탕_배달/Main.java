package boj.p2XXX.p2839_설탕_배달;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int N;
    static int[] dp = new int[5010];

    static int solution() {
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[3] = 1;
        dp[5] = 1;

        for (int i = 6; i <= N; i++) {
            if (dp[i - 3] != Integer.MAX_VALUE)
                dp[i] = Math.min(dp[i], dp[i - 3] + 1);
            if (dp[i - 5] != Integer.MAX_VALUE)
                dp[i] = Math.min(dp[i], dp[i - 5] + 1);
        }
        return dp[N] == Integer.MAX_VALUE ? -1 : dp[N];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        System.out.println(solution());
    }
}
