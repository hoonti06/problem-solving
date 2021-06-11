package boj.p2XXX.p2228_구간_나누기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int MIN = Integer.MIN_VALUE / 2;
    static int N, M;
    static int[][] dp; // dp[i][j] : 1에서 i번째까지 j 구간으로 나누었을 때의 최대합
    static boolean[][] visited;
    static int[] sum; // sum[i] : 1에서 i번째 까지의 합
    static int go(int n, int m) {
        if (m <= 0) return 0;
        if (n <= 0) return MIN;

        if (visited[n][m]) return dp[n][m];
        visited[n][m] = true;

        dp[n][m] = go(n-1, m);
        for (int k = 1; k <= n; k++)
            dp[n][m] = Math.max(dp[n][m],
                        go(k-2, m-1) + sum[n] - sum[k-1]);

        return dp[n][m];
    }

    static int solution() {
        for (int j = 1; j <= M; j++) dp[0][j] = MIN;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                dp[i][j] = dp[i - 1][j];
                for (int k = 1; k <= i; k++) {
                    if (k >= 2)
                        dp[i][j] = Math.max(dp[i][j], dp[k-2][j-1] + sum[i] - sum[k-1]);
                    else if (k == 1 && j == 1)
                        dp[i][j] = Math.max(dp[i][j], sum[i]);
                }
            }
        }
        return dp[N][M];
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N+1][M+1];
        dp = new int[N+1][M+1];
//        for (int i = 0; i <= N; i++)
//            Arrays.fill(dp[i], MIN);
        sum = new int[N+1];
        for (int i = 1; i <= N; i++)
            sum[i] = sum[i-1] + Integer.parseInt(in.readLine());

//        System.out.println(go(N, M));
        System.out.println(solution());
    }
}
