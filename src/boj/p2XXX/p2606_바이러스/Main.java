package boj.p2XXX.p2606_바이러스;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] root;

    static void init() {
        for (int i = 1; i <= N; i++)
            root[i] = -1;
    }

    static int find(int x) {
        if (root[x] < 0) return x;
        return root[x] = find(root[x]);
    }

    static void union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);
        if (xRoot == yRoot) return;

        root[xRoot] += root[yRoot];
        root[yRoot] = xRoot;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        root = new int[N+1];
        init();

        int M = Integer.parseInt(in.readLine());
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
//            union(Math.min(x, y), Math.max(x, y));
            union(x, y);
        }
        for (int i = 1; i <= N; i++)
            System.out.println(i + " : " + root[i]);
        System.out.println(-root[find(1)] - 1);
    }
}
