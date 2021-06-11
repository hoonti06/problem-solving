package boj.p1XXX.p1504_특별최단경로;

import java.io.*;
import java.util.*;

public class Main {
    // 경로 3개를 더해야 해서 MAX_VALUE에서 3으로 나눈 값을 최댓값으로 한다
    static final int MAX_DIST = Integer.MAX_VALUE / 3;
    static final int START = 0;
    static final int THROUGH = 1;
    static final int END = 2;
    static int N, M, V1, V2;
    static int[][] edge, dists;
    static class Node {
        int to, dist;
        Node(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }

    static void dijkstra(int start, int[] curDists) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.dist));
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node prev = pq.poll();
            int from = prev.to;
            int fromDist = prev.dist;
            if (curDists[from] < fromDist) continue;

            for (int to = 0; to <= N; to++) {
                if (edge[from][to] == MAX_DIST) continue;
                int toDist = edge[from][to] + fromDist;
                if (curDists[to] > toDist) {
                    curDists[to] = toDist;
                    pq.offer(new Node(to, toDist));
                }
            }
        }
    }

    static int solution() {
        dijkstra(1, dists[START]); // START -> {V1, V2}
        dijkstra(V1, dists[THROUGH]);   // V1 -> V2 (= V2 -> v1)
        dijkstra(N, dists[END]);        // END -> {V1, V2} (= {V1, V2} -> END)

        int res = 0;
        if (V1 == 1 && V2 == N)
            res = dists[START][N]; // START(V1) -> END(V2)
        else if (V1 == 1)
            res = dists[START][V2] + dists[END][V2]; // START(V1) -> V2 -> END
        else if (V2 == N)
            res = dists[START][V1] + dists[END][V1]; // START -> V1 -> END(V2)
        else {
            // START -> V1 -> V2 -> END
            int way1 = dists[START][V1] + dists[THROUGH][V2] + dists[END][V2];
            // START -> V2 -> V1 -> END
            int way2 = dists[START][V2] + dists[THROUGH][V2] + dists[END][V1];
            res = Math.min(way1, way2);
        }

        return res < MAX_DIST? res : -1;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edge = new int[N+1][N+1];
        dists = new int[3][N+1];
        for (int i = 0; i < 3; i++)
            Arrays.fill(dists[i], MAX_DIST);
        for (int i = 0; i <= N; i++)
            Arrays.fill(edge[i], MAX_DIST);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edge[a][b] = Math.min(edge[a][b], w);
            edge[b][a] = Math.min(edge[b][a], w);
        }
        st = new StringTokenizer(in.readLine());
        V1 = Integer.parseInt(st.nextToken());
        V2 = Integer.parseInt(st.nextToken());

        System.out.println(solution());
    }
}
