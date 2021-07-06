package boj.p10XXX.p10870;

import java.io.*;
import java.util.Arrays;

public class Main {
    static int[] memo;

    static int go(int idx) {
        if (idx == 0) return 0;
        if (idx <= 2) return 1;

        if (memo[idx] != -1) return memo[idx];

        return memo[idx] = go(idx-2) + go(idx-1);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        memo =  new int[N+1];
        Arrays.fill(memo, -1);

        System.out.println(go(N));
    }
}