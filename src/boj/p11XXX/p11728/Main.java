package boj.p11XXX.p11728;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] a, b;

    static void solution() {
        int i = 0, j = 0;
        StringBuilder sb = new StringBuilder();
        while (i < N && j < M) {
            if (a[i] <= b[j]) {
                sb.append(a[i++]).append(" ");
            } else {
                sb.append(b[j++]).append(" ");
            }
        }
        while (i < N) {
            sb.append(a[i++]).append(" ");
        }
        while (j < M) {
            sb.append(b[j++]).append(" ");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        a = new int[N];
        M = Integer.parseInt(st.nextToken());
        b = new int[M];
        st = new StringTokenizer(in.readLine(), " ");
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(in.readLine(), " ");
        for (int i = 0; i < M; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        solution();
    }
}
