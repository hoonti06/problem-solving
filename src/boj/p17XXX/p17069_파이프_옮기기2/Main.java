package boj.p17XXX.p17069_파이프_옮기기2;

import java.io.*;
import java.util.*;

public class Main {
    static final int WALL = 1;
    static int N;
    static int[][] board;

    static final int VERTICAL = 0;
    static final int HORIZONTAL = 1;
    static final int DIAGONAL = 2;


    static class Node {
        int r, c, dir;
        Node (int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }

    // memo[i][j][k] : (i, j)를 k의 방향으로 방문했을 때의 경우의 수
    static long[][][] memo;
    static long go(int r, int c, int s) {
        if (r < 1 || r > N || c < 1 || c > N)
            return 0;
        if (board[r][c] == WALL) return 0;

        if (memo[r][c][s] != -1) return memo[r][c][s];
        memo[r][c][s] = 0;

        if (s == VERTICAL) {
            if (r-1 >= 1 && board[r-1][c] != WALL) {
                memo[r][c][s] += go(r-1, c, VERTICAL);
                memo[r][c][s] += go(r-1, c, DIAGONAL);
            }
        } else if (s == HORIZONTAL) {
            if (c-1 >= 1 && board[r][c-1] != WALL) {
                memo[r][c][s] += go(r, c-1, HORIZONTAL);
                memo[r][c][s] += go(r, c-1, DIAGONAL);
            }
        } else {
            if (r-1 >=1 && c-1 >=1 && board[r-1][c] != WALL && board[r][c-1] != WALL) {
                memo[r][c][s] += go(r-1, c-1, VERTICAL);
                memo[r][c][s] += go(r-1, c-1, HORIZONTAL);
                memo[r][c][s] += go(r-1, c-1, DIAGONAL);
            }
        }
        return memo[r][c][s];
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        board = new int[N+1][N+1];
        memo = new long[N+1][N+1][3];
        for (long[][] d2 : memo)
            for (long[] d1 : d2) Arrays.fill(d1, -1);

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            for (int j = 1; j <= N; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }

        memo[1][2][HORIZONTAL] = 1;

//        System.out.println(bfs());

        long res = 0;
        for (int s = 0; s < 3; s++) res += go(N, N, s);
        System.out.println(res);
    }
}


