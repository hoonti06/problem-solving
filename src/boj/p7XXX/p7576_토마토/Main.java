package boj.p7XXX.p7576_토마토;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] board;
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = {  0,-1, 0, 1 };

    static Queue<Integer> q = new ArrayDeque<>();

    static boolean isFinish() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++)
                if (board[i][j] == 0) return false;
        }
        return true;
    }

    static int bfs() {
        int cnt = 0;
        while (!q.isEmpty()) {
            if (isFinish()) return cnt;
            for (int qs = 0, qSize = q.size() / 2; qs < qSize; qs++) {
                int r = q.poll();
                int c = q.poll();

                for (int i = 0; i < 4; i++) {
                    int nr = r + dx[i];
                    int nc = c + dy[i];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= M
                                || Math.abs(board[nr][nc]) == 1)
                        continue;

                    board[nr][nc] = 1;
                    q.offer(nr); q.offer(nc);
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
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) { q.offer(i); q.offer(j); }
            }
        }
        System.out.println(bfs());
    }
}



