package swea.p5656_벽돌_깨기;

import java.io.*;
import java.util.*;

public class Solution {
    static int N, M, X, res;
    static int[][] board;
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = {  0,-1, 0, 1 };

    static void copy(int[][] src, int[][] dest) {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                dest[i][j] = src[i][j];
    }

    static int countRemain() {
        int cnt = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if (board[i][j] != 0) cnt++;

        return cnt;
    }

    static void go(int cnt) {
        if (cnt >= X) {
            res = Math.min(res, countRemain());
            return;
        }

        int[][] backupBoard = new int[N][M];
        copy(board, backupBoard);

        for (int i = 0; i < M; i++) {
            drop(i);

            go(cnt+1);
            copy(backupBoard, board);
        }
    }

    static void drop(int sc) {
        int sr = 0;
        while (sr < N && board[sr][sc] == 0) sr++;
        if (sr >= N) return;

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(sr); q.offer(sc);

        while (!q.isEmpty()) {
            int r = q.poll();
            int c = q.poll();

            int range = board[r][c];
            board[r][c] = 0;
            for (int i = 0; i < 4; i++) {
                int nr = r, nc = c;
                for (int j = 1; j < range; j++) {
                    nr += dx[i];
                    nc += dy[i];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= M)
                        break;
                    if (board[nr][nc] == 0) continue;

                    q.offer(nr); q.offer(nc);
                }
            }
        }

        for (int j = 0; j < M; j++) {
            Queue<Integer> tmpq = new ArrayDeque<>();
            for (int i = N-1; i >= 0; i--) {
                if (board[i][j] == 0) continue;
                tmpq.offer(board[i][j]);
                board[i][j] = 0;
            }
            int i = N-1;
            while (!tmpq.isEmpty()) board[i--][j] = tmpq.poll();
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int testcase = Integer.parseInt(in.readLine());
        for (int tc = 1; tc <= testcase; tc++) {
            res = Integer.MAX_VALUE;
            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            X = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());

            board = new int[N][M];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(in.readLine(), " ");
                for (int j = 0; j < M; j++)
                    board[i][j] = Integer.parseInt(st.nextToken());
            }
            go(0);
            System.out.println("#" + tc + " " + res);
        }
    }
}

