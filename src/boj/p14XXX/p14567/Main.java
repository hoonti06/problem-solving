package boj.p14XXX.p14567;

import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[] indegree, depth;
    static List<Integer>[] list;

    static void solution() {
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
                depth[i] = 1;
            }
        }

        while (!q.isEmpty()) {
            int from = q.poll();

            for (int i = 0; i < list[from].size(); i++) {
                int to = list[from].get(i);

                if (--indegree[to] <= 0) {
                    q.offer(to);
                    depth[to] = Math.min(depth[to], depth[from] + 1);
                }
            }
        }
    }


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        indegree = new int[N+1];
        depth = new int[N+1];
        list = new ArrayList[N+1];
        for (int i = 0; i <= N; i++) {
            list[i] = new ArrayList<>();
            depth[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            list[from].add(to);
            indegree[to]++;
        }

        solution();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(depth[i]).append(" ");
        }
        System.out.println(sb);
    }
}
