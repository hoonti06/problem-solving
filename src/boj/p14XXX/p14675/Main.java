package boj.p14XXX.p14675;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<Integer>[] edges;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        edges = new ArrayList[N+1];
        for (int i = 0; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 0; i < N-1; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edges[a].add(b);
            edges[b].add(a);
        }

        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(in.readLine());
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            int type = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            if (type == 1)
                sb.append(edges[num].size() > 1? "yes\n" : "no\n");
            else
                sb.append("yes\n");
        }
        System.out.print(sb);
    }
}
