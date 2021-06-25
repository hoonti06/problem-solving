package boj.p9XXX.p9934;

import java.io.*;
import java.util.*;

public class Main {
    static int K, N;
    static int[] btree, input;
    static int cnt;
    static int[] depthCnts;

    static void inorder(int depth) {
        if (depth >= K) return;

        if (cnt < N) {
            inorder(depth+1);
        }

        int idx = (int) Math.pow(2, depth) - 1 + depthCnts[depth]++;
        btree[idx] = input[cnt++];

        if (cnt < N) {
            inorder(depth + 1);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(in.readLine());
        N = 1 << K;
        btree = new int[N];
        input = new int[N];
        depthCnts = new int[K];

        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        for (int i = 0; i < N; i++) {
            input[i++] = Integer.parseInt(st.nextToken());
        }

        inorder(0);

        StringBuilder sb = new StringBuilder();
        int curCnt = 0;
        for (int i = 0; i < K; i++) {
            int m = depthCnts[i];
            for (int j = 0; j < m; j++) {
                sb.append(btree[curCnt++]).append(" ");
            }
            sb.setLength(sb.length()-1);
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
