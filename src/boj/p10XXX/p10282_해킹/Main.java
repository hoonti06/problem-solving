package boj.p10XXX.p10282_해킹;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int MAX_N = 10_010;
    static int N, D, C;
//    static List<Node>[] edge = new ArrayList[MAX_N];
    static Node[] edges;
    static int[] dists;
    static class Node implements Comparable<Node> {
        int to, dist;
        Node next;
        Node(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }

        Node(int to, int dist, Node next) {
            this.to = to;
            this.dist = dist;
            this.next = next;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.dist, o.dist);
        }
    }
    static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(C, 0));
        dists[C] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int from = cur.to;
            int fromDist = cur.dist;
            if (fromDist > dists[from]) continue;

            for (Node tmp = edges[from]; tmp != null; tmp = tmp.next) {
                int to = tmp.to;
                int toDist = tmp.dist + fromDist;
                if (dists[to] == -1 || dists[to] > toDist) {
                    dists[to] = toDist;
                    pq.offer(new Node(to, toDist));
                }
            }
        }
    }


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testcase = Integer.parseInt(in.readLine());
        for (int tc = 0; tc < testcase; tc++) {
            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            edges = new Node[N+1];
            dists = new int[N+1]; Arrays.fill(dists, -1);
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(in.readLine(), " ");
                int to = Integer.parseInt(st.nextToken());
                int from = Integer.parseInt(st.nextToken());
                int dist = Integer.parseInt(st.nextToken());
                edges[from] = new Node(to, dist, edges[from]);
            }

            dijkstra();

            int res = 0, cnt = 0;
            for (int i = 1; i <= N; i++) {
                if (dists[i] != -1) cnt++;
                res = Math.max(res, dists[i]);
            }
            sb.append(cnt).append(" ").append(res).append("\n");
        }
        System.out.print(sb);
    }
}
