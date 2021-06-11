package boj.p1XXX.p1717_집합의_표현;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] rank = new int[1_000_001];
    static int[] root = new int[1_000_001];

    static int find(int x) {
        if (root[x] == x) return x;
        return root[x] = find(root[x]);
    }

    static void union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);
        if (xRoot != yRoot) {
            if (rank[xRoot] < rank[yRoot]) {
                root[xRoot] = root[yRoot];
            }
            else if (rank[xRoot] > rank[yRoot]) {
                root[yRoot] = root[xRoot];
            }
            else {
                root[yRoot] = root[xRoot];
                rank[xRoot]++;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= N; i++)
            root[i] = i;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            int c = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if (c == 0)
                union(x, y);
            else
                System.out.println(find(x) == find(y)? "YES" : "NO");
        }
    }
}
