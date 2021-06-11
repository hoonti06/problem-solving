package boj.p1XXX.p1167_트리의_지름;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<Node>[] edge;
    static int maxVertex, maxDist;
    static boolean[] visited;
    static class Node {
        int to, dist;
        Node(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }
    static void dfs(int from, int sum) {
        if (maxDist < sum) {
            maxDist = sum;
            maxVertex = from;
        }
        for (int i = 0; i < edge[from].size(); i++) {
            int to = edge[from].get(i).to;
            int dist = edge[from].get(i).dist;

            if (visited[to]) continue;
            visited[to] = true;
            dfs(to, sum + dist);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        edge = new ArrayList[N+1];
        visited = new boolean[N+1];
        for (int i = 0; i <= N; i++)
            edge[i] = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String[] split = in.readLine().split(" ");
            int from = Integer.parseInt(split[0]);
            int len = split.length-1;
            for (int j = 1; j < len; j+=2) {
                int to = Integer.parseInt(split[j]);
                int dist = Integer.parseInt(split[j+1]);
                edge[from].add(new Node(to, dist));
            }
        }
        Arrays.fill(visited, false);
        visited[1] = true;
        dfs(1, 0);

        Arrays.fill(visited, false);
        visited[maxVertex] = true;
        dfs(maxVertex, 0);

        System.out.println(maxDist);
    }
}
