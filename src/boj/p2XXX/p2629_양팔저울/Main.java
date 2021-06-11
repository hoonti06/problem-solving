package boj.p2XXX.p2629_양팔저울;

import java.io.*;
import java.util.*;

public class Main {
    static final int OFFSET = 15010;
    static int N, M, weightSum;
    static int[] weight;
    static boolean[][] dp = new boolean[31][OFFSET * 2];

    // DP
    static void solution() {
        dp[0][OFFSET] = true;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= OFFSET + weightSum; j++) {
                dp[i][j] |= dp[i-1][j];

                if (j - weight[i] >= 0)
                    dp[i][j] |= dp[i-1][j - weight[i]];

                if (j + weight[i] <= OFFSET + weightSum)
                    dp[i][j] |= dp[i-1][j + weight[i]];
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        weight = new int[N+1];
        weightSum = 0;
        for (int i = N; i >= 1; i--) {
            weight[i] = Integer.parseInt(st.nextToken());
            weightSum += weight[i];
        }

        solution();

        M = Integer.parseInt(in.readLine());
        st = new StringTokenizer(in.readLine(), " ");
        for (int i = 0; i < M; i++) {
            int input = Integer.parseInt(st.nextToken());
            String res = "N ";
            if (input <= weightSum) {
                for (int j = 1; j <= N; j++) {
                    if (dp[j][OFFSET + input]) {
                        res = "Y ";
                        break;
                    }
                }
            }
            System.out.print(res);
        }
    }
}

