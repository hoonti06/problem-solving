package boj.p2XXX.p2629_양팔저울;

import java.io.*;
import java.util.*;

public class Main_recur {
    static final int OFFSET = 15010;
    static int N, M, weightSum;
    static int[] weight;
    static int[][] dp = new int[31][OFFSET * 2];

    static int go(int cnt, int num) {
//        if (cnt >= N) return false;
//        if (num < 0 || num > OFFSET + weightSum) return false;
//        if (cnt == 0 && num == 0) return true;
//        if (cnt < 0) return false;
//
////        dp[cnt][num] |= d
//        if (dp[cnt][num]) return dp[cnt][num];
//
//        dp[cnt][num] |= go(cnt - 1, num - weight[cnt]);
//        dp[cnt][num] |= go(cnt - 1, num + weight[cnt]);
//        dp[cnt][num] |= go(cnt - 1, num);
        return dp[cnt][num];
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

//        for (int i = 0; i < 31; i++) Arrays.fill(dp[i], )

        N = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        weight = new int[N+1];
        weightSum = 0;
        for (int i = N; i >= 1; i--) {
            weight[i] = Integer.parseInt(st.nextToken());
            weightSum += weight[i];
        }

        go(N-1, OFFSET + weightSum);

        M = Integer.parseInt(in.readLine());
        st = new StringTokenizer(in.readLine(), " ");
        for (int i = 0; i < M; i++) {
            int input = Integer.parseInt(st.nextToken());
            String res = "N ";
            if (input <= weightSum) {
                for (int j = 1; j <= N; j++) {
                    if (dp[j][OFFSET + input] == 1) {
                        res = "Y ";
                        break;
                    }
                }
            }
            System.out.print(res);
        }
    }
}

