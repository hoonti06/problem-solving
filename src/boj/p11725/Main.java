package boj.p11725;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<Integer>[] list;
    static int[] parents;
    static void solution() {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(1);
        while (!q.isEmpty()) {
            for (int qs = 0, qSz = q.size(); qs < qSz; qs++) {
                int parent = q.poll();

                for (int i = 0; i < list[parent].size(); i++) {
                    int child = list[parent].get(i);
                    if (parents[child] != 0) continue;
                    parents[child] = parent;

                    q.offer(child);
                }
            }
        }
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        list = new ArrayList[N+1];
        parents = new int[N+1];
        for (int i = 0; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < N-1; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
        }
        solution();
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= N; i++) {
            sb.append(parents[i]).append("\n");
        }
        System.out.println(sb);
    }
}
