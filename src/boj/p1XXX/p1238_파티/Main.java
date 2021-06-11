package boj.p1XXX.p1238_파티;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int MAX_N = 1010;
    static int N, M, X;
    static class Node implements Comparable<Node> {
        int to, dist;
        Node(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.dist, o.dist);
        }
    }
    static List<Node>[] outdegreeEdge = new ArrayList[MAX_N];
    static List<Node>[] indegreeEdge = new ArrayList[MAX_N];
    static int[] goDists = new int[MAX_N];
    static int[] comebackDists = new int[MAX_N];
    static {
        for (int i = 0; i < MAX_N; i++) {
            outdegreeEdge[i] = new ArrayList<>();
            indegreeEdge[i] = new ArrayList<>();
        }
    }

    static void dijkstra(List<Node>[] edge, int[] dists) {
        Arrays.fill(dists, -1);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(X, 0));
        dists[X] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int from = cur.to;
            int fromDist = cur.dist;

            if (fromDist > dists[from])
                continue;

            for (int i = 0; i < edge[from].size(); i++) {
                Node next = edge[from].get(i);
                int to = next.to;
                int toDist = next.dist + fromDist;
                if (dists[to] == -1 || dists[to] > toDist) {
                    dists[to] = toDist;
                    pq.offer(new Node(to, toDist));
                }
            }
        }
    }

    static void solution() {
        dijkstra(outdegreeEdge, goDists);
        dijkstra(indegreeEdge, comebackDists);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            outdegreeEdge[from].add(new Node(to, dist));
            indegreeEdge[to].add(new Node(from, dist));
        }

        solution();

        int res = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++)
            res = Math.max(res, goDists[i] + comebackDists[i]);

        System.out.println(res);
    }
}
