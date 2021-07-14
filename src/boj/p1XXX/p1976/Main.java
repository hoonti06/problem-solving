package boj.p1XXX.p1976;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] root;

    static int find(int x) {
        return x == root[x]? x : find(root[x]);
    }

    static void union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);
        if (xRoot < yRoot)
            root[yRoot] = xRoot;
        else
            root[xRoot] = yRoot;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        M = Integer.parseInt(in.readLine());
        root = new int[N];
        for (int i = 0; i < N; i++)
            root[i] = i;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            for (int j = 0; j < N; j++) {
                if (Integer.parseInt(st.nextToken()) == 1)
                    union(i, j);
            }
        }

        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        int prev = -1;
        boolean res = true;
        if (M != 0) prev = Integer.parseInt(st.nextToken()) - 1;
        for (int i = 1; i < M; i++) {
            int cur = Integer.parseInt(st.nextToken()) - 1;
            if (prev == cur) continue;
            if (find(prev) != find(cur)) {
                res = false;
                break;
            }
            prev = cur;
        }
        System.out.println(res? "YES" : "NO");
    }
}
