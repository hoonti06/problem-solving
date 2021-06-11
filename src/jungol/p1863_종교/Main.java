package jungol.p1863_종교;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] root, rank;
    static int find(int x) {
        if (root[x] < 0) return x;
        return root[x] = find(root[x]);
    }

    static void union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);

        if (xRoot == yRoot) return;

        if (rank[xRoot] <= rank[yRoot]) {
            root[yRoot] += root[xRoot];
            root[xRoot] = yRoot;

            if (rank[xRoot] == rank[yRoot]) rank[yRoot]++;
        }
        else {
            root[xRoot] += root[yRoot];
            root[yRoot] = xRoot;
        }
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        rank = new int[N+1];
        root = new int[N+1];
        for (int i = 1; i <= N; i++)
            root[i] = -1;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            union(x, y);
        }

        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            System.out.println(i + " : " + root[i]);
            if (root[i] < 0) cnt++;
        }


        System.out.println(cnt);
    }
}
