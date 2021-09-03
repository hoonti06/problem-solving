package boj.p11XXX.p11779;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<Node>[] edges;
    static int[] dists;
    static int[] prev;

    static class Node {
        int to;
        int dist;
        Node (int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }

    public static void dijkstra(int start, int end) {
        PriorityQueue<Node> q = new PriorityQueue<>(Comparator.comparingInt(value -> value.dist));
        q.offer(new Node(start, 0));
        dists[start] = 0;

        while (!q.isEmpty()) {
            Node cur = q.poll();
            int from = cur.to;
            int fromDist = cur.dist;

            if (from == end) {
                break;
            }

            if (fromDist < dists[from]) {
                continue;
            }

            for (Node node : edges[from]) {
                int to = node.to;
                int toDist = node.dist;

                if (dists[to] > toDist + fromDist) {
                    dists[to] = toDist + fromDist;
                    prev[to] = from;
                    q.offer(new Node(to, dists[to]));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        M = Integer.parseInt(in.readLine());
        prev = new int[N+1];
        edges = new List[N+1];
        for (int i = 0; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }

        dists = new int[N+1];
        Arrays.fill(dists, Integer.MAX_VALUE);

        for (int i = 0; i < M; i++) {
            String[] split = in.readLine().split(" ");
            int from = Integer.parseInt(split[0]);
            int to = Integer.parseInt(split[1]);
            int dist = Integer.parseInt(split[2]);

            edges[from].add(new Node(to, dist));
        }

        String[] split = in.readLine().split(" ");
        int start = Integer.parseInt(split[0]);
        int end = Integer.parseInt(split[1]);

        dijkstra(start, end);

        Deque<Integer> stack = new ArrayDeque<>();
        int cur = end;
        while (cur != 0) {
            stack.push(cur);
            cur = prev[cur];
        }

        StringBuilder sb = new StringBuilder();
        sb.append(dists[end]).append("\n");
        sb.append(stack.size()).append("\n");
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }
        System.out.println(sb);
    }
}
