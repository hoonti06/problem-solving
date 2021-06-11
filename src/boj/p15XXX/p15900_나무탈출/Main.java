package boj.p15XXX.p15900_나무탈출;

import java.util.*;
import java.io.*;

public class Main {
    static int N;
//    static class Node {
//        int to,
//
//    }

    static boolean bfs() {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(1);
        visited[1] = true;
        int cnt = 0;
        int res = 0;
        while (!q.isEmpty()) {
            for (int qs = 0, qSize = q.size(); qs < qSize; qs++) {
                int from = q.poll();

                boolean leaf = true;
                for (int i = 0; i < edge[from].size(); i++) {
                    int to = edge[from].get(i);
                    if (visited[to]) continue;

                    visited[to] = true;
                    leaf = false;
                    q.offer(to);
                }
                if (leaf) {
                    res += cnt;
                }
            }
            cnt++;
        }
        return (res % 2) == 1;
    }
    static boolean[] visited;
    static int[] cnt;
    static List<Integer>[] edge;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        visited = new boolean[N+1];
        edge = new ArrayList[N+1];
        for (int i = 0; i <= N; i++)
            edge[i] = new ArrayList<>();

        for (int i = 0; i < N-1; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edge[a].add(b);
            edge[b].add(a);
        }
        System.out.println(bfs()? "Yes" : "No");
    }
}
