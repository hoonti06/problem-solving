package boj.p2XXX.p2252;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<Integer>[] nodes;
    static int[] indegrees;

    static void solution() {
        StringBuilder sb = new StringBuilder();
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (indegrees[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int from = q.poll();
            sb.append(from).append(" ");
            for (int to : nodes[from]) {
                if (--indegrees[to] == 0) {
                    q.offer(to);
                }
            }
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nodes = new ArrayList[N + 1];
        indegrees = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            nodes[from].add(to);
            indegrees[to]++;
        }
        solution();
    }
}
