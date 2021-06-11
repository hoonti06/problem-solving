package boj.p17XXX.p17836_공주님;

import java.io.*;
import java.util.*;

public class Main {
    static final int EMPTY = 0;
    static final int WALL = 1;
    static final int SWORD = 2;
    static int N, M, T;
    static int[][] board;
    static boolean[][] visited;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = { 0,-1, 0, 1};

    static int bfs() {
        int res = Integer.MAX_VALUE;
        int er = N-1, ec = M-1;

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(0);
        q.offer(0);
        visited[0][0] = true;

        int cnt = 0;
        while (!q.isEmpty() && cnt < T) {
            for (int qs = 0, qSize = q.size()/2; qs < qSize; qs++) {
                int r = q.poll();
                int c = q.poll();
                if (r == er && c == ec) {
                    res = Math.min(res, cnt);
                    return res;
                }
                if (board[r][c] == SWORD) {
                    res = Math.min(res, cnt + (er - r) + (ec - c));
                    continue;
                }

                for (int i = 0; i < 4; i++) {
                    int nr = r + dx[i];
                    int nc = c + dy[i];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= M)
                        continue;
                    if (visited[nr][nc] || board[nr][nc] == WALL)
                        continue;

                    visited[nr][nc] = true;
                    q.offer(nr); q.offer(nc);
                }
            }
            cnt++;
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int res = bfs();
        if (res == Integer.MAX_VALUE || res > T)
            System.out.println("Fail");
        else
            System.out.println(res);
    }
}
