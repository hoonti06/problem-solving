package swea.p1249_보급로;

import java.io.*;
import java.util.*;

public class Solution {
    static int N;
    static int[][] board, dists;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = { 0,-1, 0, 1};
    static class Node implements Comparable<Node> {
        int r, c, dist;
        Node(int r, int c, int dist) {
            this.r = r; this.c = c;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.dist, o.dist);
        }
    }
    static int solution() {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, board[0][0]));
        dists[0][0] = 0;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.r == N-1 && cur.c == N-1)
                return cur.dist;

            if (dists[cur.r][cur.c] < cur.dist)
                continue;

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dx[i];
                int nc = cur.c + dy[i];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N)
                    continue;

                int ndist = cur.dist + board[nr][nc];
                if (dists[nr][nc] == -1 || dists[nr][nc] > ndist) {
                    dists[nr][nc] = ndist;
                    pq.offer(new Node(nr, nc, ndist));
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(in.readLine());
        for (int tc = 1; tc <= testcase; tc++) {
            N = Integer.parseInt(in.readLine());
            board = new int[N][N];
            dists = new int[N][N];
            for (int i = 0; i < N; i++) {
                Arrays.fill(dists[i], -1);
                String line = in.readLine();
                for (int j = 0; j < N; j++)
                    board[i][j] = line.charAt(j) - '0';
            }
            System.out.println("#" + tc + " " + solution());
        }
    }
}
