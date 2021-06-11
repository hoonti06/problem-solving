package boj.p12XXX.p12026_BOJ거리;

import java.io.*;
import java.util.*;

public class Main {
    static final int MAX_VAL = Integer.MAX_VALUE / 2;
    static int N;
    static String input;

    static int[] dp;
    static HashMap<Character, Integer> map = new HashMap<>();
    static {
        map.put('B', 0); map.put('O', 1); map.put('J', 2);
    }

    static int go(int idx, int num) {
        if (idx == 0) return 0;
        if (dp[idx] < MAX_VAL) return dp[idx];

        for (int i = 0; i < idx; i++) {
            int prev = (num + 2) % 3;
            if (map.get(input.charAt(i)) != prev) continue;
            dp[idx] = Math.min(dp[idx], go(i, prev) + (idx-i) * (idx-i));
        }
        return dp[idx];
    }

    static void solution() {
        dp[0] = 0;

        for (int i = 1; i < N; i++) {
            int cur = map.get(input.charAt(i));
            for (int j = 0; j < i; j++) {
                int prev = map.get(input.charAt(j));
                if ((cur+2) % 3 == prev)
                    dp[i] = Math.min(dp[i], dp[j] + (i-j) * (i-j));
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        input = in.readLine();
        dp = new int[N];
        Arrays.fill(dp, MAX_VAL);

//        solution();
        go(N-1, map.get(input.charAt(N-1)));
        System.out.println(dp[N-1] >= MAX_VAL? -1 : dp[N-1]);
    }
}

