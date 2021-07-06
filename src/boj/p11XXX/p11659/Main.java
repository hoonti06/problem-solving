package boj.p11XXX.p11659;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] input, sum;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        input = new int[N+1];
        sum = new int[N+1];
        st = new StringTokenizer(in.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
            sum[i] = sum[i-1] + input[i];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            sb.append(sum[end] - sum[start-1]).append("\n");
        }
        System.out.print(sb);
    }
}
