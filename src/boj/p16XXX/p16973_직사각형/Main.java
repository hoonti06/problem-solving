package boj.p16XXX.p16973_직사각형;

import java.io.*;
import java.util.*;

public class Main {
    static final int WALL = 1;
    static int N, M, H, W;
    static int sr, sc, fr, fc;
    static int[] dx = { -1, 0, 1, 0};
    static int[] dy = {  0,-1, 0, 1};
    static int[][] board;
    static boolean[][] visited;

    static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r; this.c = c;
        }
    }

    static boolean possible(int r, int c) {
        if (r < 0 || r > N-H || c < 0 || c > M-W)
            return false;
        if (visited[r][c]) return false;
        visited[r][c] = true;

        for (int i = r; i < r + H; i++)
            for (int j = c; j < c + W; j++)
                if (board[i][j] == 1) return false;

        return true;
    }

    static int bfs() {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(sr, sc));
        visited[sr][sc] = true;

        int cnt = 0;
        while (!q.isEmpty()) {
            for (int qs = 0, qSize = q.size(); qs < qSize; qs++) {
                Point cur = q.poll();
                if (cur.r == fr && cur.c == fc)
                    return cnt;

                for (int i = 0; i < 4; i++) {
                    int nr = cur.r + dx[i];
                    int nc = cur.c + dy[i];
                    if (!possible(nr, nc)) continue;

                    q.offer(new Point(nr, nc));
                }
            }
            cnt++;
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            for (int j = 0; j < M; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(in.readLine(), " ");
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        sr = Integer.parseInt(st.nextToken())-1;
        sc = Integer.parseInt(st.nextToken())-1;
        fr = Integer.parseInt(st.nextToken())-1;
        fc = Integer.parseInt(st.nextToken())-1;

        System.out.println(bfs());
    }
}
